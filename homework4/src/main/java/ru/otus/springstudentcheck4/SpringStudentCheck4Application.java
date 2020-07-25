package ru.otus.springstudentcheck4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.springstudentcheck4.configs.YamlProps;

@EnableConfigurationProperties(YamlProps.class)
@SpringBootApplication
class SpringStudentCheck4Application {
	public static void main(String[] args) { SpringApplication.run(SpringStudentCheck4Application.class, args);}
}