package com.example.bookmanagement.category;


import com.example.bookmanagement.book.Book;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @SequenceGenerator(
            name = "categories_sequence",
            sequenceName = "categories_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "categories_sequence"
    )

    private Long categoryId;
    private String categoryName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "BOOKS_CATEGORIES_MAPPING", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Category(){}

    public Category(Long categoryId, String categoryName) {
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }
    public Category(String categoryName) {
        setCategoryName(categoryName);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) throw new IllegalStateException("The name of the category must not be empty!");
        this.categoryName = categoryName;
    }

    public Set<Book> getBooks() { return books; }

    public void setBooks(Set<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", books=" + books +
                '}';
    }
}
