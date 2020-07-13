package ru.otus.springstudentcheck3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springstudentcheck3.configs.YamlProps;
import ru.otus.springstudentcheck3.dao.ChecksDao;
import ru.otus.springstudentcheck3.domain.Check;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDao dao;
    private final Examination exam;
    private String FIO;
    private int numCheck = 0;
    private int numberValidChecks;
    private Check check;
    private final YamlProps props;
    private final MessageSource messageSource;

    public StudentCheckServiceImpl(ChecksDao dao, Examination exam, YamlProps props, MessageSource messageSource) {
        this.dao = dao;
        this.exam = exam;
        this.props = props;
        this.messageSource = messageSource;
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
        if(numberValidChecks > props.getSuccessfullyNumberValidChecks()){
            conclusion = messageSource.getMessage("success", new String[]{FIO}, props.getLocale());
        }
        else{
            conclusion = messageSource.getMessage("unsuccess", new String[]{FIO}, props.getLocale());
        }

        return conclusion;
    }
}
