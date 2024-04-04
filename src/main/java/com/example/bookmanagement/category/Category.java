package com.example.bookmanagement.category;


import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.bookCategory.BookCategory;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;

import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookCategory> bookCategories;

    public Category(){}

    public Category(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
