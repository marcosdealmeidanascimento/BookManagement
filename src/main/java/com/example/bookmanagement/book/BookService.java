package com.example.bookmanagement.book;

import com.example.bookmanagement.bookStatus.BookStatus;
import com.example.bookmanagement.bookStatus.BookStatusRepository;
import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookStatusRepository bookStatusRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, BookStatusRepository bookStatusRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookStatusRepository = bookStatusRepository;
    }

    public Iterable<Book> getAllActiveBooks() { return bookRepository.findAllActiveBooks(); }

    public Iterable<Book> getAllBooks() { return bookRepository.findAll(); }

    public void addNewBook(Book book) {
        if (book.getBookTitle() == null || book.getBookTitle().isBlank()) throw new IllegalStateException("The title of the book must not be empty");
        Optional<Book> optionalBooks = bookRepository.findBooksByBookCode(book.getBookCode());
        if (optionalBooks.isPresent()) throw new IllegalStateException("This books is already registered");
        bookRepository.save(book);
    }

    @Transactional
    public void updateBookDetails(Long bookId, Book newBook) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));

        if (newBook.getBookTitle() == null || newBook.getBookTitle().isBlank()) throw new IllegalStateException("The book name must not be empty");
        book.setBookTitle(newBook.getBookTitle());

        if (newBook.getYear() <= 0) throw new IllegalStateException("The release year must be a positive number");
        book.setYear(newBook.getYear());

        if (newBook.getEdition() == null || newBook.getEdition().isBlank()) throw new IllegalStateException("The book edition must not be empty");
        book.setEdition(newBook.getEdition());

        if (newBook.getNumberOfPages() == null || newBook.getNumberOfPages().isBlank()) throw new IllegalStateException("The number of pages of the book must not be empty");
        book.setNumberOfPages(newBook.getNumberOfPages());

    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));

        bookRepository.deleteById(bookId);
    }

    public void assignBookToCategory(Long bookId, Long categoryId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("This category does not exist"));
        if (category.getBooks().contains(book)) throw new IllegalStateException("This book is already assigned to this category");
        Set<Book> books = new HashSet<>();
        books.add(book);
        category.setBooks(books);
        categoryRepository.save(category);
    }

    public void changeBookStatus(Long bookId, String reason) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));
        BookStatus bookStatus = new BookStatus(
                reason,
                book
        );
        book.setStatus(!book.getStatus());
        bookStatusRepository.save(bookStatus);
    }

}
