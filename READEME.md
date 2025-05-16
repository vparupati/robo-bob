
# RoboBob CLI System

RoboBob is a command-line interface (CLI) application designed to handle user queries. Built with **Java**, the system provides a simple and efficient way to interact with predefined questions and answers and arithmetic expressions.

---

## 🚀 Features

- **Ask Questions**: Submit questions via the CLI and receive answer.
- **Ask Questions**: Submit arithmetic expressions via the CLI and receive answer.

---

## 🛠️ Tech Stack

- **Language**: Java
- **Build Tool**: Maven

---

## 📜 Assumptions

1. Questions must match exactly (case-insensitive, ignoring `?` punctuation).
2. No runtime addition of new questions.
3. Division by zero or malformed expressions return a standard response:
   `"I'm not sure how to answer that. Please try another question."`

---

## 🧩 Usage

1. Clone the repository.
2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   java -jar target/robobob-cli.jar
   ```
4. Follow the prompts to ask questions and view history.

---
