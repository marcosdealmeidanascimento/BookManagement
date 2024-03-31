package com.example.bookmanagement.book;

import com.example.bookmanagement.categories.Categories;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @SequenceGenerator(
            name = "books_sequence",
            sequenceName = "books_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "books_sequence"
    )

    private Long bookId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "BOOKS_CATEGORIES_MAPPING", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Categories> categories;
    private String bookCode;
    private String bookName;
    private int year;
    private String edition;
    private String numberOfPages;
    private boolean status;

    public Book() {}

    public Book(Long bookId, String bookCode, String bookName, int year, String edition, String numberOfPages) {
        setBookId(bookId);
        setBookCode(bookCode);
        setBookName(bookName);
        setYear(year);
        setEdition(edition);
        setNumberOfPages(numberOfPages);
        this.status = false;
    }

    public Book(String bookName, String bookCode, int year, String edition, String numberOfPages) {
        setBookName(bookName);
        setBookCode(bookCode);
        setYear(year);
        setEdition(edition);
        setNumberOfPages(numberOfPages);
        this.status = false;
    }


    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", categories=" + categories +
                ", bookCode='" + bookCode + '\'' +
                ", bookName='" + bookName + '\'' +
                ", year=" + year +
                ", edition='" + edition + '\'' +
                ", numberOfPages='" + numberOfPages + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
