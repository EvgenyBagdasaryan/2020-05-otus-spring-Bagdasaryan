package ru.otus.spring.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.dto.CommentDto;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.CommentService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentControllerRest {

    private final CommentService commentService;

    @GetMapping("/api/comments/{bookId}")
    public List<CommentDto> list(@PathVariable String bookId) {
        return commentService.getCommentsByBookId(bookId).stream().map(CommentDto::toDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/api/comments/{bookId}")
    public ResponseEntity<CommentDto> commentSave(@PathVariable String bookId, @RequestBody @Valid CommentDto comment){
        commentService.saveComment(bookId, new Comment(UUID.randomUUID().toString(), comment.getName()));
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping(value = "/api/comments/{bookId}")
    public ResponseEntity<CommentDto> commentUpdate(@PathVariable String bookId, @RequestBody @Valid CommentDto comment){
        commentService.updateComment(comment.getId(), new Comment(null, comment.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<Void> commentDelete(@PathVariable String id) throws DocumentNotFoundException {
        commentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
