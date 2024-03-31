package com.example.bookmanagement;

import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.book.BookRepository;
import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Autowired
    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository bookRepository,
            CategoryRepository categoryRepository
    ) {
        return args -> {
            Book b1 = new Book(
                    "name",
                    "BCK_0",
                    2024,
                    "1st",
                    "123"
            );
            Category c1 = new Category(
                    "Horror"
            );Category c2 = new Category(
                    "Romance"
            );
            bookRepository.saveAll(List.of(b1));
            categoryRepository.saveAll(List.of(c1, c2));
        };
    }
}
