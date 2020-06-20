package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ChecksDao;
import ru.otus.spring.domain.Check;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDao dao;
    private String FIO;
    private int numCheck = 0;
    private int numberValidChecks;
    private Check check;

    public StudentCheckServiceImpl(ChecksDao dao) {
        this.dao = dao;
    }

    public Check getCheckByNum(int numCheck){ return dao.getCheckByNum(numCheck); }

    public Check getNextCheck(){
        numCheck++;
        return dao.getCheckByNum(numCheck);
    }

    public void examination(){
        ExaminationService es;
        do{
            check = getNextCheck();
            if(check != null){
                es = new ExaminationService(check);
                String phrase = es.conversation();
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
    }

    public String resulting(){

        String conclusion;
        if(numberValidChecks > getMinNumberSuccessValidChecks()){
            conclusion = FIO + ", vi uspeshno protestirovani!";
        }
        else{
            conclusion = FIO + ", vi ne proshli testirovanie!";
        }

        return conclusion;
    }

    public int getMinNumberSuccessValidChecks(){
        return dao.getMinNumberSuccessValidChecks();
    }
}
