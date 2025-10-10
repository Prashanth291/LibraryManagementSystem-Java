package com.library.LibrarySystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity 
public class Book implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    private String bookId;
    
    private String title;
    private String author;
    private String isbn;
    private String category;
    private int totalCopies;
    private int availableCopies;
    private boolean isAvailable;

    public Book() {
    }
    
    public Book(String bookId, String title, String author, String isbn, 
                String category, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.isAvailable = true;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
        this.isAvailable = availableCopies > 0;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
            isAvailable = availableCopies > 0;
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            isAvailable = true;
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Author: %s | ISBN: %s | Category: %s | Available: %d/%d",
                bookId, title, author, isbn, category, availableCopies, totalCopies);
    }
}