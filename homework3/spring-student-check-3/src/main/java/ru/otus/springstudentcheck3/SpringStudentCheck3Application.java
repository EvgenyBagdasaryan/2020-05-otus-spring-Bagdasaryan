package ru.otus.springstudentcheck3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.springstudentcheck3.configs.YamlProps;
import ru.otus.springstudentcheck3.service.StudentCheckService;

@EnableConfigurationProperties(YamlProps.class)
@SpringBootApplication
class SpringStudentCheck3Application {

	public static void main(String[] args) {

		var context = SpringApplication.run(SpringStudentCheck3Application.class, args);

		StudentCheckService service = context.getBean(StudentCheckService.class);

		System.out.println(service.examination());

		context.close();
	}
}