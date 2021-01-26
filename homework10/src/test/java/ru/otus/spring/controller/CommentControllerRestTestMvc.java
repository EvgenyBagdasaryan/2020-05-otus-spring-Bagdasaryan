package ru.otus.spring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование BookControllerRest")
@WebMvcTest(BookController.class)
class CommentControllerRestTestMvc {

    @Import(BookController.class)
    @ComponentScan
    @Configuration
    static class TestConfig{}

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void contextLoads() throws Exception {

        final Author author = new Author(UUID.randomUUID().toString(), "Author1");

        final Genre genre = new Genre(UUID.randomUUID().toString(), "Genre");

        final Comment comment = new Comment(UUID.randomUUID().toString(), "Comment");

        final Book book = new Book(
                UUID.randomUUID().toString(),
                "book",
                List.of(genre),
                List.of(author),
                List.of(comment)
        );

        when(bookService.getBooks())
                .thenReturn(List.of(book));

        mvc.perform(get("/api/books")).andExpect(status().isOk());
    }
}