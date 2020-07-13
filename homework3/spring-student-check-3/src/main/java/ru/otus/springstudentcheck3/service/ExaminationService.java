package ru.otus.springstudentcheck3.service;

import org.springframework.stereotype.Service;
import ru.otus.springstudentcheck3.domain.Check;

import java.util.Scanner;

@Service
public class ExaminationService implements Examination {

    private Check check;
    private Scanner sc;

    @Override
    public void setCheck(Check check){
        this.check = check;
    }

    @Override
    public String conversation(){
        sc = new Scanner(System.in);
        System.out.println(check.getQuestion());
        return sc.nextLine();
    }
}
