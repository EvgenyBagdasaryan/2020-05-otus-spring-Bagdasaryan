package ru.otus.spring.service;

import ru.otus.spring.domain.Check;

public interface StudentCheckService {
    Check getNextCheck();
    Check getCheckByNum(int numCheck);
    int getMinNumberSuccessValidChecks();
    void examination();
    String resulting();
}
