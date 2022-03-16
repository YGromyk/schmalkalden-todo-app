package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findById(long id);
    @Query(value = "select * from users where email = ?1 limit 1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "select exists(SELECT users.email FROM users WHERE users.email like ?1 limit 1)", nativeQuery = true)
    Boolean checkWhetherExistsWithEmail(String email);


}
