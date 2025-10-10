package com.library.LibrarySystem.controllers;

import com.library.LibrarySystem.models.Book;
import com.library.LibrarySystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> bookList = bookService.getAllBooks();

        model.addAttribute("books", bookList);

        return "books-list";
    }
    @GetMapping("/books/add")
public String showAddBookForm(Model model) {
    model.addAttribute("book", new Book("", "", "", "", "", 0));
    return "add-book"; 
}


    @PostMapping("/books/save")
    public String saveNewBook(@ModelAttribute("book") Book book) {
        String newBookId = bookService.generateBookId();
        book.setBookId(newBookId);

        bookService.addBook(
            book.getBookId(), 
            book.getTitle(), 
            book.getAuthor(), 
            book.getIsbn(), 
            book.getCategory(), 
            book.getTotalCopies()
        );

        return "redirect:/books";
    }
    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") String bookId, Model model) {
        Book book = bookService.findBookById(bookId);
        model.addAttribute("book", book);
        return "edit-book"; 
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(
            book.getBookId(),
            book.getTitle(),
            book.getAuthor(),
            book.getIsbn(),
            book.getCategory(),
            book.getTotalCopies()
        );
        return "redirect:/books";
    }
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") String bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}