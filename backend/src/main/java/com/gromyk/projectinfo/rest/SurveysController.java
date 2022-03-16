package com.gromyk.projectinfo.rest;

import com.gromyk.projectinfo.data.Mapper;
import com.gromyk.projectinfo.data.dtos.AnswerRequest;
import com.gromyk.projectinfo.data.dtos.QuestionRequest;
import com.gromyk.projectinfo.data.dtos.SurveyRequest;
import com.gromyk.projectinfo.data.entities.Survey;
import com.gromyk.projectinfo.data.entities.User;
import com.gromyk.projectinfo.data.repositories.AnswerOptionRepository;
import com.gromyk.projectinfo.data.repositories.QuestionsRepository;
import com.gromyk.projectinfo.data.repositories.SurveysRepository;
import com.gromyk.projectinfo.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/surveys")
public class SurveysController {
    private final SurveysRepository surveysRepository;

    private final QuestionsRepository questionsRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final JWTManager jwtManager;
    private final UserRepository userRepository;

    @Autowired

    public SurveysController(SurveysRepository surveysRepository, QuestionsRepository questionsRepository, AnswerOptionRepository answerOptionRepository, JWTManager jwtManager, UserRepository userRepository) {
        this.surveysRepository = surveysRepository;
        this.questionsRepository = questionsRepository;
        this.answerOptionRepository = answerOptionRepository;
        this.jwtManager = jwtManager;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/new")
    String newSurvey(@RequestHeader HttpHeaders headers) {
        String token = Objects.requireNonNull(headers.getFirst("Authorization")).split(" ")[1];
        String username = jwtManager.getUserNameFromJwtToken(token);
        User user = userRepository.findByEmail(username).orElse(null);
        if (user != null) {
            SurveyRequest surveyRequest1 = createSurvey();

            Survey survey = surveysRepository.save(Mapper.toSurvey(surveyRequest1, user));
            survey.setQuestions(surveyRequest1.getQuestions().stream().map(questionRequest ->
                    questionsRepository.save(Mapper.toQuestion(questionRequest))
            ).collect(Collectors.toList()));

            return "Ok";
        }
        return "Bad";
//        Survey surveyToSave =
//        surveysRepository.save(createSurvey());
    }

    private SurveyRequest createSurvey() {
        SurveyRequest surveyRequest = new SurveyRequest();
        surveyRequest.setName("Math test");
        List<QuestionRequest> questionRequests = new ArrayList<>();

        questionRequests.add(createQuestion("2x2?", "4"));

        questionRequests.add(createQuestion("2-2?", "0"));
        questionRequests.add(createQuestion("2/2?", "1"));
        questionRequests.add(createQuestion("2+2?", "4"));

        surveyRequest.setQuestions(questionRequests);
        return surveyRequest;
    }

    private QuestionRequest createQuestion(String question, String rightAnswer) {
        QuestionRequest questionRequest1 = new QuestionRequest();
        questionRequest1.setQuestion(question);
        List<AnswerRequest> temp1 = new ArrayList<>();
        Collections.addAll(
                temp1,
                new AnswerRequest("5", false),
                new AnswerRequest(rightAnswer, true),
                new AnswerRequest("8", false),
                new AnswerRequest("2", false)
        );
        questionRequest1.setAnswers(temp1);
        return questionRequest1;
    }
}
