package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ChecksDao;
import ru.otus.spring.domain.Check;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDao dao;
    private final Examination exam;
    private String FIO;
    private int numCheck = 0;
    private int numberValidChecks;
    private Check check;

    public StudentCheckServiceImpl(ChecksDao dao, Examination exam) {
        this.dao = dao;
        this.exam = exam;
    }

    @Override
    public Check getCheckByNum(int numCheck){ return dao.getCheckByNum(numCheck); }

    @Override
    public Check getNextCheck(){
        numCheck++;
        return dao.getCheckByNum(numCheck);
    }

    @Override
    public String examination(){

        do{
            check = getNextCheck();
            if(check != null){
                exam.setCheck(check);
                String phrase = exam.conversation();
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

        String conclusion;
        if(numberValidChecks > getMinNumberSuccessValidChecks()){
            conclusion = FIO + ", vi uspeshno protestirovani!";
        }
        else{
            conclusion = FIO + ", vi ne proshli testirovanie!";
        }

        return conclusion;
    }

    @Override
    public int getMinNumberSuccessValidChecks(){
        return dao.getMinNumberSuccessValidChecks();
    }
}
