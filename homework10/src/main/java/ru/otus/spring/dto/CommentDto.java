package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import ru.otus.spring.domain.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private String id;
    private String name;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getName());
    }
}
