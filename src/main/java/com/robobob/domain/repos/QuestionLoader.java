package com.robobob.domain.repos;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;



/**
 * Loads questions and answers from a CSV file.
 */
@Component
public class QuestionLoader {

    private static final Logger log = LoggerFactory.getLogger(QuestionLoader.class);
    private String filePath;


    public final CSVFormat CSV_FORMAT = CSVFormat.Builder.create().setDelimiter('=')
            .setTrim(true)
            .setIgnoreEmptyLines(true)
            .setIgnoreSurroundingSpaces(true)
            .build();

    @Value("${robobob.questions.filepath:questions.txt}")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads an answer for a given query from the questions file.
     *
     * @param query The question to search for.
     * @return The answer to the question, or null if the question is not found or an error occurs.
     * @throws NullPointerException if the resource for the file path cannot be found.
     */
    public String load(String query) {
        Path path = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getPath());
        if (!Files.exists(path)) {
            log.error("Question file not found: {}", filePath);
            return null;
        }
        try (CSVParser parser = CSVParser.parse(path, StandardCharsets.UTF_8, CSV_FORMAT)) {
            for (CSVRecord record : parser) {
                if (record.size() >= 2 && query.replaceAll("\\?$", "").equals(record.get(0).toLowerCase().replaceAll("\\?$", ""))) {
                    return record.get(1);
                }
            }
        } catch (IOException e) {
            log.error("Error loading question", e);
        }
        return null;
    }
}
