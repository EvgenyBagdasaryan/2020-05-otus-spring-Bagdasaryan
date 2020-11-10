package ru.otus.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.dto.BookDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Author {
    @Id
    private String id;
    private String name;
}
