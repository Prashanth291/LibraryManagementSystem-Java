package com.library.LibrarySystem.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String memberId;
    
    private String name;
    private String email;
    private String phone;
    
    private Date registrationDate = new Date();
    @ElementCollection
    private List<String> borrowedBookIds = new ArrayList<>();
    private int maxBooksAllowed = 5;
    private boolean isActive = true;

    public Member() {
    }

    public Member(String memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }
    
    public void setBorrowedBookIds(List<String> borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }

    public void addBorrowedBook(String bookId) {
        this.borrowedBookIds.add(bookId);
    }

    public void removeBorrowedBook(String bookId) {
        this.borrowedBookIds.remove(bookId);
    }

    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }
    
    public void setMaxBooksAllowed(int maxBooksAllowed) {
        this.maxBooksAllowed = maxBooksAllowed;
    }

    public boolean canBorrowMore() {
        return borrowedBookIds.size() < maxBooksAllowed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}