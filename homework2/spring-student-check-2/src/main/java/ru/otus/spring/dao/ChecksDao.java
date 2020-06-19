package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import ru.otus.spring.domain.Check;

public interface ChecksDao {

    Check getCheckByNum(int numCheck);
    int getMinNumberSuccessValidChecks();
}
