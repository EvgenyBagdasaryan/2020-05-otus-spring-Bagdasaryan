package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookControllerRest {

    private final BookService bookService;

    @GetMapping("/api/books")
    public List<BookDto> list() throws DocumentNotFoundException {
        return bookService.getBooks().stream().map(BookDto::toDto).collect(Collectors.toList());
    }
}
