package com.gromyk.projectinfo.data.repositories;

import com.gromyk.projectinfo.data.entities.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionsRepository extends CrudRepository<Question, Long> {
}
