package ru.otus.spring.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(){

        String resComment = "";
        for (Comment item : commentRepository.findAll())
            resComment += item.getId() + " " + item.getName() + " \n ";

        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByBookID(String bookId){

        List<Comment> foundComments  = bookRepository.findCommentsByBookId(bookId);

        String resComment = "";
        for (Comment item : foundComments)
            resComment += item.getId() + " " + item.getName() + " \n ";

        return foundComments;
    }

    @Override
    public void deleteComment(String id)  throws DocumentNotFoundException {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id, Comment.class.getName()));

        commentRepository.deleteById(id);
    }
}