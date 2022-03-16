package com.gromyk.projectinfo.configs;

import com.gromyk.projectinfo.data.repositories.QuestionsRepository;
import com.gromyk.projectinfo.data.repositories.SurveysRepository;
import com.gromyk.projectinfo.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final SurveysRepository surveysRepository;

    @Autowired
    public CommandLineRunner(QuestionsRepository questionsRepository, UserRepository userRepository, SurveysRepository surveysRepository) {
        this.questionsRepository = questionsRepository;
        this.userRepository = userRepository;
        this.surveysRepository = surveysRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
