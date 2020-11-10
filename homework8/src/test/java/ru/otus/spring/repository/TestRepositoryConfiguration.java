package ru.otus.spring.repository;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"ru.otus.spring.repository"})
public class TestRepositoryConfiguration {
}
