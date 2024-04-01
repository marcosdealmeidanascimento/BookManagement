package com.example.bookmanagement;

import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.book.BookRepository;
import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class Config {

    @Autowired
    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository bookRepository,
            CategoryRepository categoryRepository
    ) {
        return args -> {
            Book b1 = new Book(
                    "BookTitle",
                    "Synopsis",
                    "ISBN",
                    2024,
                    "1st Edition",
                    "123",
                    10.0f,
                    20.0f,
                    30.0f,
                    100.0f
            );

            ArrayList<Category> categories = new ArrayList<>();
            String[] categoryNames = {
                    "Ficção Científica", "Romance", "Fantasia", "Mistério", "Não Ficção",
                    "Terror", "História", "Policial", "Aventura", "Autoajuda", "Biografia",
                    "Clássico", "Comédia", "Conto", "Culinária", "Drama", "Educação",
                    "Espiritualidade", "Esporte", "Família e Relacionamentos", "Ficção Histórica",
                    "Humor", "Jornalismo", "LGBTQ+", "Matemática", "Medicina", "Memórias",
                    "Musical", "Poesia", "Psicologia", "Religião", "Romance Policial",
                    "Saúde e Fitness", "Tecnologia", "Viagem", "Negócios", "Artes",
                    "Auto-biografia", "Desenvolvimento Pessoal", "Economia",
                    "Ficção Histórica Alternativa", "Ficção Realista", "Investigação",
                    "Literatura Infantil", "Livros de Referência"
            };

            for (String categoryName: categoryNames) {
                categories.add(new Category(categoryName));
            }

            bookRepository.saveAll(List.of(b1));
            categoryRepository.saveAll(categories);
        };
    }
}
