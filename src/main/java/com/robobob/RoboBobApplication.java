package com.robobob;

import com.robobob.domain.handler.QuestionInputHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.robobob")
public class RoboBobApplication implements CommandLineRunner {

    private final QuestionInputHandler handler;

    public RoboBobApplication(QuestionInputHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoboBobApplication.class, args);
    }

    @Override
    public void run(String... args) {
        handler.run();
    }
}