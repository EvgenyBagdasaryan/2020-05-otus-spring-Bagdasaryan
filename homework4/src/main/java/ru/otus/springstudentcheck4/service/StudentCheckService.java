package ru.otus.springstudentcheck4.service;

import ru.otus.springstudentcheck4.domain.Check;

public interface StudentCheckService {
    Check getNextCheck();
    Check getCheckByNum(int numCheck);
    String examination();
}
