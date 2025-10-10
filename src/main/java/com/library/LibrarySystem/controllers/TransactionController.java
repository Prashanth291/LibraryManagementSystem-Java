package com.library.LibrarySystem.controllers;

import com.library.LibrarySystem.models.Transaction;
import com.library.LibrarySystem.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String listAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "transactions-list";
    }
    
    @GetMapping("/transactions/borrow")
    public String showBorrowForm() {
        return "borrow-book"; 
    }

    @PostMapping("/transactions/borrow")
    public String processBorrow(@RequestParam String memberId, @RequestParam String bookId, RedirectAttributes redirectAttributes) {
        boolean success = transactionService.borrowBook(memberId, bookId);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Book borrowed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to borrow book. Check IDs and availability.");
        }

        return "redirect:/transactions";
    }
    @PostMapping("/transactions/return/{id}")
    public String processReturn(@PathVariable("id") String transactionId, RedirectAttributes redirectAttributes) {
        boolean success = transactionService.returnBook(transactionId);

        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Book returned successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to return book.");
        }

        return "redirect:/transactions";
    }
}