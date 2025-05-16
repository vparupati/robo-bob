package com.robobob.domain.handler;

import com.robobob.domain.model.Answer;
import com.robobob.domain.model.Question;
import com.robobob.domain.service.QuestionService;
import org.springframework.stereotype.Component;

import java.util.Scanner;


/**
 * Handles user input from the console.
 */
@Component
public class ConsoleInputHandler implements QuestionInputHandler {

    private final QuestionService service;

    public ConsoleInputHandler(QuestionService service) {
        this.service = service;
    }

    /**
     * Starts the console input loop, prompting the user for questions and displaying answers.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("👋 Hello! I'm RoboBob. Ask me a question or give me a math expression:");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            Question q = Question.of(input);
            Answer a = service.handle(q);
            System.out.println(a.response());
        }
    }
}