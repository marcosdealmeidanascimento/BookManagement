package com.example.bookmanagement.book;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(path = "api/v1/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) { this.bookService = bookService; }

    @GetMapping
    public List<Book> getAllBooks() { return bookService.getAllBooks(); }

    @PostMapping
    public void addNewBook(@RequestBody Book book) { bookService.addNewBook(book); }

    @PutMapping(path="{bookId}")
    public void updateBookDetails(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookService.updateBookDetails(bookId, book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PostMapping(value = "/assignBookToCategory/{bookId}")
    public void assignBookToCategory(@PathVariable("bookId") Long bookId, @PathParam("categoryId") Long categoryId) {
        bookService.assignBookToCategory(bookId, categoryId);
    }


}
