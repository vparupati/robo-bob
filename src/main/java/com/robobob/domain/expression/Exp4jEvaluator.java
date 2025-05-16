package com.robobob.domain.expression;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.robobob.domain.Constants.UNKNOWN_ANSWER;

/**
 *  Evaluates mathematical expressions using the exp4j library.
 *  This class takes a string representation of a mathematical expression and uses the exp4j library
 *  to evaluate it, returning the result as a string.  If an error occurs during evaluation, it returns "unknown".
 */
@Component
public class Exp4jEvaluator implements ExpressionEvaluator {
    private static final Logger log = LoggerFactory.getLogger(Exp4jEvaluator.class);

    @Override
    public String evaluate(String expressionText) {
        try {
            Expression expression = new ExpressionBuilder(expressionText).build();
            return String.valueOf(expression.evaluate());
        } catch (Exception e) {
            log.debug("Error evaluating expression: {}", expressionText, e);
            return UNKNOWN_ANSWER;
        }
    }
}