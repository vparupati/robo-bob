package com.robobob.domain.repos;

import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;
/**
 * Repository interface for managing questions and their corresponding answers.
 */
public interface QuestionRepository {

    /**
     * Finds the answer associated with the given question.
     *
     * @param question the question for which the answer is to be found
     * @return the answer corresponding to the provided question
     */
    Answer findAnswerFor(Question question);
}