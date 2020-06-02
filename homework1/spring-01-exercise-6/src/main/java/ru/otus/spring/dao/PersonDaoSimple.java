package ru.otus.spring.dao;

import ru.otus.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {

    private int age;

    public void setDefaultAge(int age){
        this.age = age;
    }

    public Person findByName(String name)
    {
        return new Person(name, age);
    }
}
