package com.example.bookmanagement;

import com.example.bookmanagement.book.Book;
import com.example.bookmanagement.book.BookRepository;
import com.example.bookmanagement.bookCategory.BookCategory;
import com.example.bookmanagement.bookCategory.BookCategoryRepository;
import com.example.bookmanagement.category.Category;
import com.example.bookmanagement.category.CategoryRepository;
import com.example.bookmanagement.priceGroup.PriceGroup;
import com.example.bookmanagement.priceGroup.PriceGroupRepository;
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
            CategoryRepository categoryRepository,
            PriceGroupRepository priceGroupRepository,
            BookCategoryRepository bookCategoryRepository
    ) {
        return args -> {
            PriceGroup p1 = new PriceGroup(
                    100.12f,
                    500.02f
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
                    "Literatura Infantil"
            };

            for (String categoryName: categoryNames) {
                categories.add(new Category(categoryName));
            }

            Book b1 = new Book(
                    "BookTitle",
                    "Synopsis",
                    "ISBN",
                    "",
                    "",
                    2024,
                    "1st Edition",
                    "123",
                    10.00f,
                    20.00f,
                    30.00f,
                    100.00f
            );

            BookCategory bc1 = new BookCategory(new Book("BookTitle",
                    "Synopsis",
                    "ISBN",
                    "",
                    "",
                    2024,
                    "1st Edition",
                    "123",
                    10.00f,
                    20.00f,
                    30.00f,
                    100.00f), new Category("Livros de Referência"));

            bookRepository.saveAll(List.of(b1));
            priceGroupRepository.saveAll(List.of(p1));
            categoryRepository.saveAll(categories);

            bookCategoryRepository.save(bc1);

        };
    }
}
