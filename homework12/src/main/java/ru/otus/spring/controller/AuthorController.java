package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String listPage(@RequestParam("bookId") Long bookId, @RequestParam(required = false) Long id, Model model) throws DocumentNotFoundException {

        if(id != null){
            authorService.deleteById(id);
        }
        Author author = authorService.findAuthorsByBookId(bookId);
        model.addAttribute("authors", author);
        model.addAttribute("bookId", bookId);
        return "list_of_authors";
    }

    /*@GetMapping("/author")
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
    }*/
}