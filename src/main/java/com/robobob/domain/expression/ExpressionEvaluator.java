package com.robobob.domain.expression;

/**
 * An interface for evaluating expressions.
 * Implementations of this interface should provide a method to evaluate a given expression string.
 */
public interface ExpressionEvaluator {
    String evaluate(String expression);
}