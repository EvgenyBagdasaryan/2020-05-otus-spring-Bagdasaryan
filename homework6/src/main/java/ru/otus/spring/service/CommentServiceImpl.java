package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.repositories.BookRepositoryJpa;
import ru.otus.spring.repositories.CommentRepositoryJpa;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final IOService ioService;
    private final CommentRepositoryJpa commentRepository;
    private final BookRepositoryJpa bookRepository;

    @Transactional
    @Override
    public void createComment(long bookId, String comment) {
        Book book = bookRepository.findById(bookId).get();
        commentRepository.save(new Comment(null, comment, book));
    }

    @Transactional
    @Override
    public void updateComment(long commentId, String comment) {
        Comment commentEntity = commentRepository.findById(commentId).get();
        commentEntity.setComment(comment);
        commentRepository.save(commentEntity);
    }

    @Override
    public List<Comment> readTable() {
        return commentRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
