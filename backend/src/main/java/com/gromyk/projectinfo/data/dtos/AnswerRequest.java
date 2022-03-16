package com.gromyk.projectinfo.data.dtos;

public class AnswerRequest {
    private String answer;
    private boolean isCorrect;

    public AnswerRequest(String question, boolean isCorrect) {
        this.answer = question;
        this.isCorrect = isCorrect;
    }

    public AnswerRequest() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
