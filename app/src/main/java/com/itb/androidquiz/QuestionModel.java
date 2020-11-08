package com.itb.androidquiz;

public class QuestionModel {
    int questionText;
    boolean answer;

    public QuestionModel(int questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public int getQuestionText() {
        return questionText;
    }

    public boolean getAnswer() {
        return answer;
    }}
