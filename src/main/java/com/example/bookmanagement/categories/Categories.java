package com.example.bookmanagement.categories;


import com.example.bookmanagement.books.Books;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Categories {

    @Id
    @SequenceGenerator(
            name = "categories_sequence",
            sequenceName = "categories_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categories_sequence"
    )

    private Long categoryId;
    private String categoryName;


    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "BOOKS_CATEGORIES_MAPPING", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Books> books;

    public Categories(){}

    public Categories(Long categoryId, String categoryName) {
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }
    public Categories(String categoryName) {
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
}
