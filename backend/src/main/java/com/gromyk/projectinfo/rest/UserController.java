package com.gromyk.projectinfo.rest;

import com.gromyk.projectinfo.data.Mapper;
import com.gromyk.projectinfo.data.dtos.UserDto;
import com.gromyk.projectinfo.data.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
public class UserController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(
            value = "/api/users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserDto> getUsers() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), true)
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/api/user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserDto getOwnProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String email = userDetails.getUsername();
        return Mapper.toUserDto(userRepository.findByEmail(email).get());
    }
}
