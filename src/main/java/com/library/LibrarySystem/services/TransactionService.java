package com.library.LibrarySystem.services;

import com.library.LibrarySystem.models.Book;
import com.library.LibrarySystem.models.Member;
import com.library.LibrarySystem.models.Transaction;
import com.library.LibrarySystem.repositories.BookRepository;
import com.library.LibrarySystem.repositories.MemberRepository;
import com.library.LibrarySystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private static final int BORROW_PERIOD_DAYS = 14;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              BookRepository bookRepository,
                              MemberRepository memberRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public boolean borrowBook(String memberId, String bookId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalMember.isEmpty() || optionalBook.isEmpty()) {
            return false;
        }

        Member member = optionalMember.get();
        Book book = optionalBook.get();
        
        if (!member.isActive() || !member.canBorrowMore() || !book.isAvailable()) {
            return false;
        }
        
        String transactionId = generateTransactionId();
        Date borrowDate = new Date();
        Date dueDate = calculateDueDate(borrowDate);
        
        Transaction transaction = new Transaction(transactionId, member, book, borrowDate, dueDate);

        book.borrowCopy();
        member.addBorrowedBook(book.getBookId());

        transactionRepository.save(transaction);
        bookRepository.save(book);
        memberRepository.save(member);
        return true;
    }

    public boolean returnBook(String transactionId) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);
        if (optionalTransaction.isEmpty() || optionalTransaction.get().getStatus().equals("RETURNED")) {
            return false;
        }

        Transaction transaction = optionalTransaction.get();
        Book book = transaction.getBook();
        Member member = transaction.getMember();
        
        if (book == null || member == null) {
            return false;
        }

        transaction.setReturnDate(new Date());
        transaction.setStatus("RETURNED");

        book.returnCopy();
        member.removeBorrowedBook(book.getBookId());

        transactionRepository.save(transaction);
        bookRepository.save(book);
        memberRepository.save(member);
        return true;
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public String generateTransactionId() {
        long count = transactionRepository.count();
        return String.format("T%03d", count + 1);
    }
    
    private Date calculateDueDate(Date borrowDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DAY_OF_MONTH, BORROW_PERIOD_DAYS);
        return calendar.getTime();
    }
}