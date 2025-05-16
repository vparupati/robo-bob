package com.robobob.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
/**
 * Configuration class for setting up the question cache using Caffeine.
 */
@Configuration
public class QuestionCacheConfig {

    /**
     * The cache expiry time in minutes. Defaults to 60 minutes if not specified.
     */
    @Value("${robobob.questions.cache.expiry-minutes:60}")
    private int expiryMinutes;

    /**
     * The maximum size of the cache. Defaults to 1000 entries if not specified.
     */
    @Value("${robobob.questions.cache.max-size:1000}")
    private int maxSize;

    /**
     * Creates and configures a Caffeine cache for storing questions.
     *
     * @return a configured {@link Cache} instance with String keys and values.
     */
    @Bean
    public Cache<String, String> questionCache() {
        return Caffeine.newBuilder()
                .expireAfterAccess(expiryMinutes, TimeUnit.MINUTES)
                .maximumSize(maxSize)
                .build();
    }
}