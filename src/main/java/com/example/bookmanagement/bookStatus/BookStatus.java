package com.example.bookmanagement.bookStatus;

import com.example.bookmanagement.book.Book;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "BOOK_STATUS")
@EntityListeners(AuditingEntityListener.class)
public class BookStatus {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long bookStatusId;
    private String reason;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Long getBookStatusId() {
        return bookStatusId;
    }

    public void setBookStatusId(Long bookStatusId) {
        this.bookStatusId = bookStatusId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        if (reason == null || reason.isBlank()) throw new IllegalStateException("Reason must not be null");
        this.reason = reason;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookStatus() {}

    public BookStatus(Long bookStatusId, String reason, Book book) {
        this.bookStatusId = bookStatusId;
        setReason(reason);
        setBook(book);
    }

    public BookStatus(String reason, Book book) {
        setReason(reason);
        setBook(book);
    }
}
