package ru.otus.springstudentcheck3.service;

import ru.otus.springstudentcheck3.domain.Check;

public interface Examination {
    public String conversation();
    public void setCheck(Check check);
}
