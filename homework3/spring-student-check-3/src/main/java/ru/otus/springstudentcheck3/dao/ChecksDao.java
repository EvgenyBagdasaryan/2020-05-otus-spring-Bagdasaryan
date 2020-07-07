package ru.otus.springstudentcheck3.dao;

import ru.otus.springstudentcheck3.domain.Check;

public interface ChecksDao {

    Check getCheckByNum(int numCheck);
}
