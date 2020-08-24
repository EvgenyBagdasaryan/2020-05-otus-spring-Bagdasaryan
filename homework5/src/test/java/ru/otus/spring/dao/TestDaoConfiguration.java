package ru.otus.spring.dao;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan(basePackages = {"ru.otus.spring.dao"})
public class TestDaoConfiguration {
}
