package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.RoleType;
import com.gromyk.projectinfo.data.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(RoleType name);
}
