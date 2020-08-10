package ru.otus.spring.domain;
import lombok.Data;

@Data
public class BookOnly {
    private final String bookName;
    private final String genreName;
    private final String authorName;
}
