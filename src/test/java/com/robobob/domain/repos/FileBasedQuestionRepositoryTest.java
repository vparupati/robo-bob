package com.robobob.domain.repos;

import com.github.benmanes.caffeine.cache.Cache;
import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;
import com.robobob.domain.model.QuestionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileBasedQuestionRepositoryTest {

    @Mock
    private Cache<String, String> questionCache;

    @Mock
    private QuestionLoader questionLoader;

    @InjectMocks
    private FileBasedQuestionRepository repository;


    @Test
    void testFindKnownAnswer() {
        Question question = new Question("What is your name", QuestionType.BASIC);
        when(questionCache.get(eq("what is your name"), any())).thenReturn("RoboBob");

        Answer answer = repository.findAnswerFor(question);

        assertEquals("RoboBob", answer.response());
        verify(questionCache).get(eq("what is your name"), any());
    }

    @Test
    void testCaseInsensitiveQuestion() {
        Question question = new Question("what is your NAME", QuestionType.BASIC);
        when(questionCache.get(eq("what is your name"), any())).thenReturn("RoboBob");

        Answer answer = repository.findAnswerFor(question);

        assertEquals("RoboBob", answer.response());
        verify(questionCache).get(eq("what is your name"), any());
    }

    @Test
    void testUnknownQuestionReturnsDefaultAnswer() {
        Question question = new Question("Who is your creator?", QuestionType.BASIC);
        when(questionCache.get(eq("who is your creator?"), any())).thenReturn(null);

        Answer answer = repository.findAnswerFor(question);

        assertEquals("I'm not sure how to answer that. Please try another question.", answer.response());
        verify(questionCache).get(eq("who is your creator?"), any());
    }
}