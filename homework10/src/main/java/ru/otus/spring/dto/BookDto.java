package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.spring.domain.Book;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String id;
    private String name;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName());
    }
}
