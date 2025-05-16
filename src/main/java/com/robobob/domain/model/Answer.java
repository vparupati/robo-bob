package com.robobob.domain.model;

public record Answer(String response) {
    public static Answer of(String response) {
        return new Answer(response.trim());
    }
}