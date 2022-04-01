package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.RefreshToken;
import com.gromyk.projectinfo.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    int deleteByUserId(Long userId);
}
