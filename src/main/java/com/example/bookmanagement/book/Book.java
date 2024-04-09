package com.example.bookmanagement.book;

import com.example.bookmanagement.bookCategory.BookCategory;
import com.example.bookmanagement.priceGroup.PriceGroup;
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

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookCategory> bookCategories;
    @Column(nullable = false)
    private String bookTitle;
    @Column(nullable = false)
    private String synopsis;
    private String bookCode = "BCK_0";
    private String isbn;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String edition;
    @Column(nullable = false)
    private String numberOfPages;
    @Column(columnDefinition = "Decimal(10,2)")
    private float price;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_group_id", referencedColumnName = "price_group_id")
    private PriceGroup priceGroup;
    private String barcode;
    @Column(columnDefinition = "Decimal(10,2)")
    private float height;
    @Column(columnDefinition = "Decimal(10,2)")
    private float width;
    @Column(columnDefinition = "Decimal(10,2)")
    private float depth;
    @Column(columnDefinition = "Decimal(10,2)")
    private float weight;
    private boolean status = Boolean.FALSE;
    private String reason;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public Book() {}

    public Book(Long bookId, String bookTitle, String synopsis, String isbn, String bookCode, String barcode, int year, String edition, String numberOfPages, float height, float width, float depth, float weight) {
        this.bookId = bookId;
        setBookTitle(bookTitle);
        setSynopsis(synopsis);
        setIsbn(isbn);
        setBookCode(bookCode);
        setBarcode(barcode);
        setYear(year);
        setEdition(edition);
        setNumberOfPages(numberOfPages);
        setHeight(height);
        setWidth(width);
        setDepth(depth);
        setWeight(weight);
    }

    public Book(String bookTitle, String synopsis, String isbn, String bookCode, String barcode, int year, String edition, String numberOfPages, float height, float width, float depth, float weight) {
        setBookTitle(bookTitle);
        setSynopsis(synopsis);
        setIsbn(isbn);
        setBookCode(bookCode);
        setBarcode(barcode);
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

    public Set<BookCategory> getBookCategories() {
        return bookCategories;
    }

    public void setBookCategories(Set<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        if (bookCode == null || bookCode.isBlank())
            bookCode = getCode(99991, 2);
        this.bookCode += bookCode;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(PriceGroup priceGroup) {
        this.priceGroup = priceGroup;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (barcode == null || barcode.isBlank())
            barcode = getCode(99999971, 769);
        this.barcode = barcode;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    protected String getCode(int end, int start) {
        Random random = new Random(LocalDateTime.now().getSecond());
        long code = random.nextLong((end - start) + 1) + start;
        return Long.toString(code);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", bookCode='" + bookCode + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", edition='" + edition + '\'' +
                ", numberOfPages='" + numberOfPages + '\'' +
                ", price=" + price +
                ", priceGroup=" + priceGroup +
                ", barcode='" + barcode + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", depth=" + depth +
                ", weight=" + weight +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
