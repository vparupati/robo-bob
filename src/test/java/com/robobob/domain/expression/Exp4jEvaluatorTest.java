package com.robobob.domain.expression;

import org.junit.jupiter.api.Test;

import static com.robobob.domain.Constants.UNKNOWN_ANSWER;
import static org.junit.jupiter.api.Assertions.*;

class Exp4jEvaluatorTest {

    private final Exp4jEvaluator evaluator = new Exp4jEvaluator();

    @Test
    void testSimpleAddition() {
        assertEquals("5.0", evaluator.evaluate("2+3"));
    }

    @Test
    void testSubtraction() {
        assertEquals("1.0", evaluator.evaluate("5-4"));
    }

    @Test
    void testMultiplication() {
        assertEquals("6.0", evaluator.evaluate("2*3"));
    }

    @Test
    void testDivision() {
        assertEquals("2.0", evaluator.evaluate("6/3"));
    }

    @Test
    void testDecimalArithmetic() {
        assertEquals("10.5", evaluator.evaluate("2.5*4.2"));
    }

    @Test
    void testComplexExpression() {
        assertEquals("14.0", evaluator.evaluate("2 + 3 * 4"));
    }

    @Test
    void testExpressionWithParentheses() {
        assertEquals("20.0", evaluator.evaluate("(2 + 3) * 4"));
    }

    @Test
    void testInvalidExpression() {
        assertEquals(UNKNOWN_ANSWER,evaluator.evaluate("2+"));
    }

    @Test
    void testNullInput() {
        assertEquals(UNKNOWN_ANSWER,evaluator.evaluate(null));
    }

    @Test
    void testEmptyInput() {
        assertEquals(UNKNOWN_ANSWER,evaluator.evaluate(""));
    }
}