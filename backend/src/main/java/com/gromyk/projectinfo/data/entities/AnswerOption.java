package com.gromyk.projectinfo.data.entities;

import javax.persistence.*;

@Entity
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;
    private boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    private Question rootQuestion;

    public AnswerOption() {
    }

    public AnswerOption(String question, boolean isCorrect) {
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Question getRootQuestion() {
        return rootQuestion;
    }

    public void setRootQuestion(Question rootQuestion) {
        this.rootQuestion = rootQuestion;
    }
}
