package ru.otus.springstudentcheck4.service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableConfigurationProperties
@ComponentScan({"ru.otus.springstudentcheck4.configs", "ru.otus.springstudentcheck4.dao", "ru.otus.springstudentcheck4.service"})
public class TestContextConfig {
}
