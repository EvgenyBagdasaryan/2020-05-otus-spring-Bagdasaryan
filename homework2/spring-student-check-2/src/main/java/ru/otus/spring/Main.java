package ru.otus.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.domain.Check;
import ru.otus.spring.service.StudentCheckService;

import java.util.Scanner;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

   @Bean
   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
       return new PropertySourcesPlaceholderConfigurer();
   }

   @Value("${successfullyNumberValidChecks}")
   int successfullyNumberValidChecks;

   public static void main(String[] args){

       AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(Main.class);

       StudentCheckService service = context.getBean(StudentCheckService.class);

       Check check;
       int numberValidChecks = 0;
       String FIO = "";

       Scanner sc = new Scanner(System.in);;
       do{
           check = service.getNextCheck();
           if(check != null){
               System.out.println(check.getQuestion());
               String phrase = sc.nextLine();
               if(check.getNumCheck() == 1){
                   FIO = phrase;
               }
               else{
                   if(phrase.trim().equals(check.getAnswer().trim())) {
                       numberValidChecks++;
                   }
               }
           }
       }while (check != null);

       System.out.println(FIO + ", vashe chislo pravilnih otvetov: " + numberValidChecks);

       if(numberValidChecks > 2){
           System.out.println(FIO + ", vi uspeshno protestirovani!");
       }
       else{
           System.out.println(FIO + ", vi ne proshli testirovanie!");
       }

       context.close();
   }
}