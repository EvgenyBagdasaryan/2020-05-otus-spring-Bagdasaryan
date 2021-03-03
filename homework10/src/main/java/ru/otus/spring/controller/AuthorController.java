package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;
import java.util.UUID;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listPage(@RequestParam("bookId") String bookId, @RequestParam(required = false) String id, Model model) throws DocumentNotFoundException {

        if(id != null){
            authorService.deleteById(id);
        }
        List<Author> authors = authorService.getAuthorsByBookId(bookId);
        model.addAttribute("authors", authors);
        model.addAttribute("bookId", bookId);
        return "list_of_authors";
    }

    @GetMapping("/author")
    public String editAuthor(@RequestParam String bookId, @RequestParam(required = false) String id, Model model) throws DocumentNotFoundException {

        Author author = null;
        if(id != null){
            author = authorService.findByAuthorId(id);
        }else{
            author = new Author();
            author.setId(UUID.randomUUID().toString());
            authorService.saveAuthor(bookId, author);
        }

        model.addAttribute("author", author);
        model.addAttribute("bookId", bookId);
        return "selected_author";
    }

    @PostMapping("/author")
    public String updateAuthor(@RequestParam("bookId") String bookId, @RequestParam("id") String id, @RequestParam("name") String name, Model model) {
        authorService.updateAuthor(id, new Author(null, name));
        return String.format("redirect:/authors?bookId=%s", bookId);
    }
}