package ru.otus.spring.service;

import ru.otus.spring.dao.ChecksDaoSimple;
import java.io.IOException;

public class StudentCheckServiceImpl implements StudentCheckService {

    private final ChecksDaoSimple dao;

    public StudentCheckServiceImpl(ChecksDaoSimple dao) {
        this.dao = dao;
    }

    public String getAll() throws IOException {
        return dao.getAllChecks();
    }
}
