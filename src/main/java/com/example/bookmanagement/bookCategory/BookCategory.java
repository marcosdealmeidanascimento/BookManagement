package com.example.bookmanagement.bookCategory;


import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.category.Category;
import jakarta.persistence.*;

@Entity
@Table(name = "BOOKS_CATEGORIES_MAPPING")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    public BookCategory() {}

    public BookCategory(Book book, Category category) {
        this.book = book;
        this.category = category;
    }
}
