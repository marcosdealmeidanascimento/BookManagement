package com.example.bookmanagement.books;

import com.example.bookmanagement.categories.Categories;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Books {

    @Id
    @SequenceGenerator(
            name = "books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "books_sequence"
    )

    private Long bookId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "BOOKS_CATEGORIES_MAPPING", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Categories> categories;
    private String bookCode;
    private String bookName;
    private LocalDate year;
    private String edition;
    private String numberOfPages;


}
