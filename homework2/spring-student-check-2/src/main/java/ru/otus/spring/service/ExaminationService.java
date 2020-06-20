package ru.otus.spring.service;

import ru.otus.spring.domain.Check;
import java.util.Scanner;

public class ExaminationService {

    Check check;
    Scanner sc = new Scanner(System.in);

    public ExaminationService(Check check){
        this.check = check;
    }
    public String conversation(){
        System.out.println(check.getQuestion());
        return sc.nextLine();
    }

}
