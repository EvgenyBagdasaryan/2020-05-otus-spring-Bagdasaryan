package ru.otus.springstudentcheck3.service;

import ru.otus.springstudentcheck3.domain.Check;

public interface StudentCheckService {
    Check getNextCheck();
    Check getCheckByNum(int numCheck);
    String examination();
}
