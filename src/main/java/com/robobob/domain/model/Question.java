package com.robobob.domain.model;

public record Question(String questionText, QuestionType type) {
    public static Question of(String text) {
        return new Question(text.trim(), QuestionType.UNKNOWN);
    }
}
