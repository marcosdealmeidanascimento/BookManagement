package com.example.bookmanagement.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository repository
    ) {
        return args -> {
            Book b1 = new Book(
                    "name",
                    "BCK_0",
                    2024,
                    "1st",
                    "123"
            );
            repository.saveAll(List.of(b1));
        };
    }
}
