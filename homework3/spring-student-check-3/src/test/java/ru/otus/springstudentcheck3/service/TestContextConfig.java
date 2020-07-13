package ru.otus.springstudentcheck3.service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableConfigurationProperties
@ComponentScan({"ru.otus.springstudentcheck3.configs", "ru.otus.springstudentcheck3.dao", "ru.otus.springstudentcheck3.service"})
public class TestContextConfig {
}
