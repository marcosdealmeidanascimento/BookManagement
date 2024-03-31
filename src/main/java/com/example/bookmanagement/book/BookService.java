package com.example.bookmanagement.book;

import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Book> getAllBooks() { return bookRepository.findAll(); }

    public void addNewBook(Book book) {
        Optional<Book> optionalBooks = bookRepository.findBooksByBookCode(book.getBookCode());
        if (optionalBooks.isPresent()) throw new IllegalStateException("This books is already registered");
        bookRepository.save(book);
    }

    @Transactional
    public void updateBookDetails(Long bookId, Book newBook) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));

        if (newBook.getBookName() == null || newBook.getBookName().isBlank()) throw new IllegalStateException("The book name must not be empty");
        book.setBookName(newBook.getBookName());

        if (newBook.getYear() < 0) throw new IllegalStateException("The release year must be a positive number");
        book.setYear(newBook.getYear());

        if (newBook.getEdition() == null || newBook.getEdition().isBlank()) throw new IllegalStateException("The book edition must not be empty");
        book.setEdition(newBook.getEdition());

        if (newBook.getNumberOfPages() == null || newBook.getNumberOfPages().isBlank()) throw new IllegalStateException("The number of pages of the book must not be empty");
        book.setNumberOfPages(newBook.getNumberOfPages());

        book.setStatus(newBook.getStatus());
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));

        bookRepository.deleteById(bookId);
    }

    public void assignBookToCategory(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("This category does not exist"));
        Set<Book> books = new HashSet<>();
        books.add(book);

        category.setBooks(books);
        categoryRepository.save(category);
    }

}
