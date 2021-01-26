package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void saveComment(String bookId, Comment comment) {

        commentRepository.add(bookId, comment);
    }

    @Override
    public List<Comment> getCommentsByBookId(String bookId) {

        return commentRepository.findCommentsByBookId(bookId);
    }

    @Override
    public Comment findByCommentId(String commentId) throws DocumentNotFoundException {
        return commentRepository.findByCommentId(commentId);
    }

    @Override
    public void updateComment(String id, Comment comment) {
        commentRepository.update(id, comment);
    }

    @Override
    public void deleteById(String id) throws DocumentNotFoundException {
        commentRepository.deleteByCommentId(id);
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }
}