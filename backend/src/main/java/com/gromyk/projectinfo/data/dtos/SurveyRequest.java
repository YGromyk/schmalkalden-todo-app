package com.gromyk.projectinfo.data.dtos;

import java.util.List;

public class SurveyRequest {
    private String name;
    private List<QuestionRequest> questions;

    public SurveyRequest(String name, List<QuestionRequest> questions) {
        this.name = name;
        this.questions = questions;
    }

    public SurveyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}

