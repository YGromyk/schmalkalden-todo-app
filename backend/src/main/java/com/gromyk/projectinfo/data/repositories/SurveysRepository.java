package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SurveysRepository extends CrudRepository<Survey, Long> {
    List<Survey> findById(long id);
    @Query(value = "select * from surveys where owner_id = ?1", nativeQuery = true)
    List<Survey> findByOwner(long ownerId);
}
