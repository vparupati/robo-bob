package com.robobob.domain.repos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuestionLoaderTest {

    private QuestionLoader questionLoader;

    @BeforeEach
    void setUp() {
        questionLoader = new QuestionLoader();
        questionLoader.setFilePath("test-questions.txt");
    }

    @Test
    void testLoadKnownQuestion() {
        assertEquals("RoboBob", questionLoader.load("what is your name"));
    }

    @Test
    void testLoadUnknownQuestion() {
        assertNull(questionLoader.load("who is your creator?"));
    }
}