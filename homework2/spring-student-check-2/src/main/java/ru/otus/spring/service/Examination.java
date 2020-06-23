package ru.otus.spring.service;

import ru.otus.spring.domain.Check;

public interface Examination {
    public String conversation();
    public void setCheck(Check check);
}
