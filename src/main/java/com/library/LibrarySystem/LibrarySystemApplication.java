package com.library.LibrarySystem;

import com.library.LibrarySystem.models.Book; 
import com.library.LibrarySystem.models.User;
import com.library.LibrarySystem.repositories.BookRepository;
import com.library.LibrarySystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibrarySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadInitialData(BookRepository bookRepository, UserRepository userRepository) {
        return args -> {
            System.out.println("========================================");
            System.out.println("  CHECKING FOR INITIAL DATA...");
            System.out.println("========================================");

            if (bookRepository.count() == 0) {
                Book sampleBook = new Book("B001", "The Hobbit", "J.R.R. Tolkien", "978-0345339683", "Fantasy", 5);
                bookRepository.save(sampleBook);
                System.out.println("✓ Sample book created.");
            }

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User("U001", "admin", "admin123", "ADMIN", "System Administrator");
                userRepository.save(admin);
                System.out.println("✓ Default admin user created.");
            }
            
            if (userRepository.findByUsername("librarian").isEmpty()) {
                User librarian = new User("U002", "librarian", "lib123", "LIBRARIAN", "Library Staff");
                userRepository.save(librarian);
                System.out.println("✓ Default librarian user created.");
            }
        };
    }
}