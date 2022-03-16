package com.gromyk.projectinfo.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;
    @ManyToOne(fetch = FetchType.LAZY)
    private Survey rootSurvey;

    @OneToMany(mappedBy = "rootQuestion")
    private List<AnswerOption> answers;

    public Question() {
    }

    public Question(String question, List<AnswerOption> answers) {
        this.question = question;
        this.answers = answers;
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

    public List<AnswerOption> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerOption> answers) {
        this.answers = answers;
    }

    public Survey getRootSurvey() {
        return rootSurvey;
    }

    public void setRootSurvey(Survey rootSurvey) {
        this.rootSurvey = rootSurvey;
    }
}
