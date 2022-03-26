package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "select * from todos where owner_id = ?1", nativeQuery = true)
    List<Todo> findByOwner(Long id);
}
