package ru.otus.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.service.StudentCheckService;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

   @Bean
   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
       return new PropertySourcesPlaceholderConfigurer();
   }

    public static void main(String[] args){

       AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(Main.class);

       StudentCheckService service = context.getBean(StudentCheckService.class);

       service.examination();
       System.out.println(service.resulting());

       context.close();
   }
}