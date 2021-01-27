package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тестирование BookController")
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Import(BookController.class)
    //@ComponentScan//(basePackageClasses = BookMapper.class)
    @Configuration
    static class TestConfig{}

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

        /*mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());*/
    }
}