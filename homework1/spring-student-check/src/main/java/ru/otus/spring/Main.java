package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.StudentCheckService;
import java.io.IOException;

public class Main {

   public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        StudentCheckService service = context.getBean(StudentCheckService.class);
        String allTextChecks = service.getAll();
        System.out.println(allTextChecks);
        context.close();
    }
}
