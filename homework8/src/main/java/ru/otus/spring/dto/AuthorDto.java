package ru.otus.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

    private String fullName;

    @Override
    public String toString() {
        return fullName;
    }
}
