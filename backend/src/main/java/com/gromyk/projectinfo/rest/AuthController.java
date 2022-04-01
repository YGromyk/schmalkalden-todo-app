package com.gromyk.projectinfo.rest;

import com.gromyk.projectinfo.data.Mapper;
import com.gromyk.projectinfo.data.dtos.*;
import com.gromyk.projectinfo.data.entities.RefreshToken;
import com.gromyk.projectinfo.data.entities.User;
import com.gromyk.projectinfo.data.repositories.UserRepository;
import com.gromyk.projectinfo.rest.advice.TokenRefreshException;
import com.gromyk.projectinfo.rest.jwt.JwtResponse;
import com.gromyk.projectinfo.rest.jwt.RefreshTokenService;
import com.gromyk.projectinfo.rest.jwt.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTManager jwtManager;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTManager jwtManager, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtManager = jwtManager;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @RequestMapping(
            value = "/signup",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserDto register(@RequestBody UserRegisterDto user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User userToRegister = Mapper.toUser(user, encodedPassword);
        if (!userRepository.checkWhetherExistsWithEmail(userToRegister.getEmail())) {
            return Mapper.toUserDto(userRepository.save(userToRegister));
        } else throw new EntityExistsException("User with email " + user.getEmail() + " exists!");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtManager.generateTokenFromUsername(userDetails.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                jwtManager.getExpirationDateFromToken(jwt)
        ));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtManager.generateTokenFromUsername(user.getEmail());
                    Long expirationDate = jwtManager.getExpirationDateFromToken(token);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken, expirationDate));
                })
                .orElseThrow(() ->
                        new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!")
                );
    }
}
