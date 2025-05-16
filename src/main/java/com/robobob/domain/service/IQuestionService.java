package com.robobob.domain.service;

import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;

/**
 * Service interface for handling questions and providing answers.
 */
public interface IQuestionService {

    /**
     * Handles the given question and returns an appropriate answer.
     *
     * @param question the question to be handled
     * @return the answer to the question
     */
    Answer handle(Question question);
}