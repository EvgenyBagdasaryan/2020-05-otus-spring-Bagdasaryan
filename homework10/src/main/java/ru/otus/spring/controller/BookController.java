package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;
import ru.otus.spring.exceptions.DocumentNotFoundException;

import java.util.List;
import java.util.UUID;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String listPage(@RequestParam(required = false) String bookId, Model model) throws DocumentNotFoundException {

        if(bookId != null){
            bookService.deleteById(bookId);
        }
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "list_of_books";
    }

    @GetMapping("/book")
    public String editBook(@RequestParam(required = false) String bookId, Model model) throws DocumentNotFoundException {

        //Book book = (bookId == null) ? (new Book()) : bookService.getBookById(bookId);

        Book book = null;
        if(bookId != null){
            book = bookService.getBookById(bookId);
        }else{
            book = new Book();
            book.setId(UUID.randomUUID().toString());
            bookService.saveBook(book);
        }
        //Book book = bookService.getBookById(bookId);*/
        model.addAttribute("book", book);
        return "selected_book";
    }
    
    @PostMapping("/book")
    public String updateBook(@RequestParam("bookId") String bookId, String name, Model model) {
        bookService.updateNameById(bookId, name);
        return "redirect:/";
    }
}