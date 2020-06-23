package ru.otus.spring.dao;

import ru.otus.spring.domain.Check;

public interface ChecksDao {

    Check getCheckByNum(int numCheck);
    int getMinNumberSuccessValidChecks();
}
