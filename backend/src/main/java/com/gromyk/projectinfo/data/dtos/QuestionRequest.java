package com.gromyk.projectinfo.data.dtos;

import java.util.List;

public class QuestionRequest {
    private String question;
    private List<AnswerRequest> answers;

    public QuestionRequest(String question, List<AnswerRequest> answers) {
        this.question = question;
        this.answers = answers;
    }

    public QuestionRequest() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequest> answers) {
        this.answers = answers;
    }
}
