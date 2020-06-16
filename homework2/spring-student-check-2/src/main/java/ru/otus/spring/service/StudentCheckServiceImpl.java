package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ChecksDao;
import ru.otus.spring.domain.Check;

@Service
public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDao dao;

    int numCheck = 0;

    //@Autowired
    public StudentCheckServiceImpl(ChecksDao dao) {
        this.dao = dao;
    }

    public Check getCheckByNum(int numCheck){ return dao.getCheckByNum(numCheck); }

    public Check getNextCheck(){

        numCheck++;
        return dao.getCheckByNum(numCheck);
    }
}
