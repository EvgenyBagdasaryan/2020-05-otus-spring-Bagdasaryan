package ru.otus.springstudentcheck4.dao;

import ru.otus.springstudentcheck4.domain.Check;

import java.util.Optional;

public interface ChecksDao {

    Check getCheckByNum(int numCheck);
}
