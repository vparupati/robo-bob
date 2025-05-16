package com.robobob.domain.service;

import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;
import com.robobob.domain.model.QuestionType;
import com.robobob.domain.expression.ExpressionEvaluator;
import com.robobob.domain.repos.QuestionRepository;
import org.springframework.stereotype.Service;

import static com.robobob.domain.Constants.UNKNOWN_ANSWER;
/**
 * Service class for handling questions and providing answers.
 */
@Service
public class QuestionService implements IQuestionService {

    private final QuestionRepository repository;
    private final ExpressionEvaluator evaluator;

    /**
     * Constructs a new QuestionService with the given repository and evaluator.
     *
     * @param repository the repository for managing questions and answers
     * @param evaluator  the evaluator for processing arithmetic expressions
     */
    public QuestionService(QuestionRepository repository, ExpressionEvaluator evaluator) {
        this.repository = repository;
        this.evaluator = evaluator;
    }

    /**
     * Handles a given question by classifying it and providing an appropriate answer.
     *
     * @param question the question to handle
     * @return the answer to the question
     */
    @Override
    public Answer handle(Question question) {
        QuestionType type = classify(question);
        return switch (type) {
            case BASIC -> repository.findAnswerFor(question);
            case ARITHMETIC -> Answer.of(evaluator.evaluate(question.questionText()));
            case UNKNOWN -> Answer.of(UNKNOWN_ANSWER);
        };
    }

    /**
     * Classifies a question based on its text content.
     *
     * @param question the question to classify
     * @return the type of the question
     */
    private QuestionType classify(Question question) {
        String text = question.questionText();
        if (text.matches("[-+*/(). 0-9]+")) {
            return QuestionType.ARITHMETIC;
        }
        return QuestionType.BASIC;
    }
}