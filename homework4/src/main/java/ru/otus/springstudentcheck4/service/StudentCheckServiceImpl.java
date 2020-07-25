package ru.otus.springstudentcheck4.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.springstudentcheck4.configs.YamlProps;
import ru.otus.springstudentcheck4.dao.ChecksDao;
import ru.otus.springstudentcheck4.domain.Check;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private int numCheck = 0;
    private int numberValidChecks;
    private Check check;
    private final ChecksDao dao;
    private final YamlProps props;
    private final MessageSource messageSource;
    private final IOService ioService;

    public StudentCheckServiceImpl(ChecksDao dao, YamlProps props, MessageSource messageSource, IOService ioService) {
        this.dao = dao;
        this.props = props;
        this.messageSource = messageSource;
        this.ioService = ioService;
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

        ioService.out(messageSource.getMessage("yourName", new String[]{}, props.getLocale()));
        String name = ioService.readString();

        do{
            check = getNextCheck();
            if(check != null){
                ioService.out(check.getQuestion());
                if(ioService.readString().trim().equals(check.getAnswer().trim())) {
                    numberValidChecks++;
                }
            }
        }while (check != null);

        String conclusion;
        if(numberValidChecks > props.getSuccessfullyNumberValidChecks()){
            conclusion = messageSource.getMessage("success", new String[]{name}, props.getLocale());
        }
        else{
            conclusion = messageSource.getMessage("unsuccess", new String[]{name}, props.getLocale());
        }

        return conclusion;
    }
}
