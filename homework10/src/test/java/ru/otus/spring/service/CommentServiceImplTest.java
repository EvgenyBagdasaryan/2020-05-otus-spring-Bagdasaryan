package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.CommentRepository;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@DisplayName("Тестирование CommentServiceImplTest")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommentServiceImpl.class)
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @DisplayName("Создать комментарий")
    @Test
    void createCommentTest() {
        String bookId  = "1";
        Comment comment = new Comment("2", "comment");
        commentService.saveComment(bookId, comment);
        verify(commentRepository).add(bookId, comment);
    }

    @DisplayName("Обновить комментарий")
    @Test
    void updateCommentTest() {
        Comment comment = new Comment(UUID.randomUUID().toString(), "comment");
        commentService.updateComment(comment.getId(), comment);
        verify(commentRepository).update(comment.getId(), comment);
    }

    @DisplayName("Получить комментарии по книге")
    @Test
    void findByBookIdTest() {
        String bookId = "1";
        commentService.getCommentsByBookId(bookId);
        verify(commentRepository).findCommentsByBookId(bookId);
    }

    @DisplayName("Удалить комментарий по идентификатору")
    @Test
    void deleteById() throws DocumentNotFoundException {
        commentService.deleteById("1");
        verify(commentRepository).deleteByCommentId("1");
    }

}