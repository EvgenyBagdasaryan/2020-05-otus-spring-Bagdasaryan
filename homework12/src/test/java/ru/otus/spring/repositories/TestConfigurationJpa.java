package ru.otus.spring.repositories;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"ru.otus.spring.repositories"})
public class TestConfigurationJpa {
}
