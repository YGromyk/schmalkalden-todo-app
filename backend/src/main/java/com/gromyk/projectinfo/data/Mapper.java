package com.gromyk.projectinfo.data;

import com.gromyk.projectinfo.data.dtos.*;
import com.gromyk.projectinfo.data.entities.AnswerOption;
import com.gromyk.projectinfo.data.entities.Question;
import com.gromyk.projectinfo.data.entities.Survey;
import com.gromyk.projectinfo.data.entities.User;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class Mapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBirthday(),
                user.getSex()
        );
    }


    public static User toUser(UserRegisterDto user, String encodedPassword) {
        return new User(user.getName(),
                user.getEmail(),
                encodedPassword,
                user.getBirthday(),
                user.getSex()
        );
    }


    public static Survey toSurvey(SurveyRequest surveyRequest, User user) {
        Survey survey = new Survey(surveyRequest.getName(), user, LocalDateTime.now());
        return survey;
    }

    public static Question toQuestion(QuestionRequest questionRequest) {
        return new Question(questionRequest.getQuestion(), questionRequest.getAnswers().stream().map(Mapper::toAnswer).collect(Collectors.toList()));
    }

    public static AnswerOption toAnswer(AnswerRequest answerRequest) {
        return new AnswerOption(answerRequest.getAnswer(), answerRequest.isCorrect());
    }


}
