package com.example.bookmanagement.book;

import com.example.bookmanagement.bookStatus.BookStatus;
import com.example.bookmanagement.category.Category;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )

    private Long bookId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "BOOKS_CATEGORIES_MAPPING", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    private String bookTitle;
    private String synopsis;
    private String bookCode = "BCK_0";
    private String ISBN;
    private int year;
    private String edition;
    private String numberOfPages;
    private String barcode;
    private float height;
    private float width;
    private float depth;
    private float weight;
    private boolean status = Boolean.FALSE;
    @Transient
    @OneToMany(mappedBy = "bookStatus")
    private Set<BookStatus> bookStatuses;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public Book() {}

    public Book(String bookTitle, String synopsis, String ISBN, int year, String edition, String numberOfPages, float height, float width, float depth, float weight) {
        setBookTitle(bookTitle);
        setSynopsis(synopsis);
        setBookCode();
        setBarcode();
        setISBN(ISBN);
        setYear(year);
        setEdition(edition);
        setNumberOfPages(numberOfPages);
        setHeight(height);
        setWidth(width);
        setDepth(depth);
        setWeight(weight);
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode() {
        Random random = new Random(LocalDateTime.now().getSecond());
        long code = random.nextLong(999999) + 1;
        this.bookCode += Long.toString(code);
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode() {
        Random random = new Random(LocalDateTime.now().getSecond());
        long barcode = random.nextLong((99999999 - 999) + 1) + 999;
        this.barcode = Long.toString(barcode);
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", categories=" + categories +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookCode='" + bookCode + '\'' +
                ", year=" + year +
                ", edition='" + edition + '\'' +
                ", numberOfPages='" + numberOfPages + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
