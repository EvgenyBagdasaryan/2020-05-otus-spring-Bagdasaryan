package ru.otus.spring.domain;

public class Check {

    private final String question;
    private final String answer;

    public Check(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}
