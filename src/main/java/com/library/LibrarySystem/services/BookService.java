package com.library.LibrarySystem.services;

import com.library.LibrarySystem.models.Book;
import com.library.LibrarySystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean addBook(String bookId, String title, String author, String isbn, String category, int totalCopies) {
        if (bookRepository.existsById(bookId)) {
            System.out.println("Error: Book ID already exists!");
            return false;
        }
        if (bookRepository.findByIsbn(isbn).isPresent()) {
            System.out.println("Error: Book with this ISBN already exists!");
            return false;
        }

        Book book = new Book(bookId, title, author, isbn, category, totalCopies);
        bookRepository.save(book);
        return true;
    }

    public boolean updateBook(String bookId, String title, String author, String isbn, String category, int totalCopies) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found!");
            return false;
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setCategory(category);
        book.setTotalCopies(totalCopies);

        bookRepository.save(book); 
        return true;
    }

    public boolean deleteBook(String bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found!");
            return false;
        }
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            System.out.println("Error: Cannot delete book that is currently borrowed!");
            return false;
        }

        bookRepository.deleteById(bookId); 
        bookRepository.deleteById(bookId); 
        return true;
    }

    public Book findBookById(String bookId) {
        return bookRepository.findById(bookId).orElse(null); 
    }

    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).orElse(null); 
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public String generateBookId() {
        long count = bookRepository.count();
        return String.format("B%03d", count + 1);
    }
}