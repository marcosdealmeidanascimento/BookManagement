package com.example.bookmanagement.book;

import com.example.bookmanagement.bookCategory.BookCategory;
import com.example.bookmanagement.bookCategory.BookCategoryRepository;
import com.example.bookmanagement.bookStatus.BookStatus;
import com.example.bookmanagement.bookStatus.BookStatusRepository;
import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import com.example.bookmanagement.priceGroup.PriceGroup;
import com.example.bookmanagement.priceGroup.PriceGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookStatusRepository bookStatusRepository;
    private final PriceGroupRepository priceGroupRepository;
    private final BookCategoryRepository bookCategoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, BookStatusRepository bookStatusRepository, PriceGroupRepository priceGroupRepository, BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookStatusRepository = bookStatusRepository;
        this.priceGroupRepository = priceGroupRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public Iterable<Book> getAllBooksAvailable() { return bookRepository.findAllBooksAvailable(); }

    public Iterable<Book> getAllBooks() { return bookRepository.findAll(); }

    public void addNewBook(Book book) {
        String barcode = book.getCode(99999971, 769);
        book.setBarcode(barcode);
        String bookCode = book.getCode(99991, 2);
        book.setBookCode(bookCode);

        if (book.getBookTitle() == null || book.getBookTitle().isBlank()) throw new IllegalStateException("The title of the book must not be empty");
        if (book.getPrice() < 1) throw new IllegalStateException("The price must be a positive entry");
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
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("This category does not exist. Id " + categoryId));

        Optional<BookCategory> optionalBookCategory = bookCategoryRepository.findBookCategoryByBookIdAndCategoryId(bookId, categoryId);
        if (optionalBookCategory.isPresent()) throw new IllegalStateException("This book is already assigned to this category");

        BookCategory bookCategory = new BookCategory(book, category);

        bookRepository.save(book);
        categoryRepository.save(category);

        bookCategoryRepository.save(bookCategory);
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

    public void assignBookToPriceGroup(Long bookId, Long priceGroupId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("This book does not exist. Id " + bookId));
        PriceGroup priceGroup = priceGroupRepository.findById(priceGroupId).orElseThrow(() -> new IllegalStateException("This Price Group does not exist. Id " + priceGroupId));

        book.setPriceGroup(priceGroup);
        bookRepository.save(book);
    }

    public Iterable<Book> getBooksByCategory(Long categoryId) {
        return bookCategoryRepository.findBooksByCategoryId(categoryId);
    }

    public Iterable<Category> getCategoriesByBook(Long bookId) {
        return bookCategoryRepository.findCategoriesByBookId(bookId);
    }
}
