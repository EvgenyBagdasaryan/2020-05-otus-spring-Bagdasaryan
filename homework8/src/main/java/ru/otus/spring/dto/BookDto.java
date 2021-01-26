package ru.otus.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    private String name;

    @Override
    public String toString() {
        return "Book{" +
                ", nameBook = '" + name + "}";
    }
}
