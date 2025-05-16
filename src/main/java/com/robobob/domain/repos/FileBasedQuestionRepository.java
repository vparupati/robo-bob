package com.robobob.domain.repos;

import com.github.benmanes.caffeine.cache.Cache;
import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.robobob.domain.Constants.UNKNOWN_ANSWER;

/**
 * A {@link QuestionRepository} that loads questions and answers from a file.
 */
@Component
public class FileBasedQuestionRepository implements QuestionRepository {

    private static final Logger logger = LoggerFactory.getLogger(FileBasedQuestionRepository.class);

    private final QuestionLoader questionLoader;

    private final Cache<String, String> questionCache;

    public FileBasedQuestionRepository(Cache<String, String> questionCache, QuestionLoader questionLoader) {
        this.questionCache = questionCache;
        this.questionLoader = questionLoader;
    }

    /**
     * Finds the answer for the given question.
     *
     * @param question the question to find the answer for
     * @return the answer for the given question
     */
    @Override
    public Answer findAnswerFor(Question question) {
        String normalized = question.questionText().trim().toLowerCase();
        String answer = questionCache.get(normalized, questionLoader::load);
        logger.debug("Question: '{}', Answer: '{}'", question.questionText(), answer);
        return Answer.of(answer != null ? answer : UNKNOWN_ANSWER);
    }
}