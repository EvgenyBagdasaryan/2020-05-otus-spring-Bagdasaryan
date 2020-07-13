package ru.otus.springstudentcheck3.domain;

public class
Check {

    private final int numCheck;
    private final String question;
    private final String answer;

    public Check(int numCheck, String question, String answer) {
        this.numCheck = numCheck;
        this.question = question;
        this.answer = answer;
    }

    public int getNumCheck() {
        return numCheck;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}
