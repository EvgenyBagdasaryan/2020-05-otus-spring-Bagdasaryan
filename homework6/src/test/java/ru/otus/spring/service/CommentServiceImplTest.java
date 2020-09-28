package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.repositories.CommentRepositoryJpa;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Тестирование CommentServiceImplTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommentServiceImpl.class)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private BookRepositoryJpa bookRepo;

    @MockBean
    private CommentRepositoryJpa commentRepo;

    @DisplayName("Создать комментарий")
    @Test
    void createComment() {
        Book bookTest = new Book(
                1L,
                "Заповедник гоблинов",
                new Genre(null, "фантастика"),
                new Author(null, "Клиффорд Саймак", null));

        when(bookRepo.findById(bookTest.getId()))
                .thenReturn(Optional.of(bookTest));

        String comment = "тестовый коментарий";

        commentService.createComment(bookTest.getId(), comment);

        Comment commentTest = new Comment(null, comment, bookTest);
        verify(commentRepo).save(commentTest);
    }

    @DisplayName("Прочитать все коментарии")
    @Test
    void readComments() {

        Book bookTest1 = new Book(1L, "", null, null);
        Book bookTest2 = new Book(2L, "", null, null);

        Comment comment1 = new Comment(2L, "коментарий", bookTest1);
        Comment comment2 = new Comment(3L, "коментарий", bookTest2);

        when(commentRepo.findById(comment1.getId())).thenReturn(Optional.of(comment1));
        when(commentRepo.findById(comment2.getId())).thenReturn(Optional.of(comment2));

        commentService.readTable();

        verify(commentRepo).findAll();
    }

    @DisplayName("Обновить комментарий")
    @Test
    void update() {
        Book book = new Book(1L, "", null, null);
        Comment comment = new Comment(2L, "коментарий", book);

        when(commentRepo.findById(comment.getId()))
                .thenReturn(Optional.of(comment));

        String newComment = "другой коментарий";
        commentService.updateComment(comment.getId(), newComment);
        Comment commentTest = new Comment(2L, newComment, book);
        verify(commentRepo).save(commentTest);
    }

    @DisplayName("Удалить комментарий по идентификатору")
    @Test
    void deleteById() {
        commentService.deleteById(1L);
        verify(commentRepo).deleteById(1L);
    }
}