package com.example.bookmanagement.bookCategory;

import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

    @Query("SELECT bc.book FROM BookCategory bc WHERE bc.category.categoryId = :categoryId")
    Iterable<Book> findBooksByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT bc.category FROM BookCategory bc WHERE bc.book.bookId = :bookId")
    Iterable<Category> findCategoriesByBookId(@Param("bookId") Long bookId);

    @Query("SELECT bc FROM BookCategory bc WHERE bc.book.bookId = :bookId AND bc.category.categoryId = :categoryId")
    Optional<BookCategory> findBookCategoryByBookIdAndCategoryId(@Param("bookId") Long bookId, @Param("categoryId") Long categoryId);


}
