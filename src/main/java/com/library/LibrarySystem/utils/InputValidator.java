package com.library.LibrarySystem.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputValidator {
    
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final String PHONE_PATTERN = 
        "^[+]?[0-9]{10,15}$";
    
    private static final String ISBN10_PATTERN = 
        "^(?:\\d{9}X|\\d{10})$";
    
    private static final String ISBN13_PATTERN = 
        "^(?:978|979)\\d{10}$";
    
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
    private static final Pattern isbn10Pattern = Pattern.compile(ISBN10_PATTERN);
    private static final Pattern isbn13Pattern = Pattern.compile(ISBN13_PATTERN);

    /**
     * Validates email address format
     * @param email The email address to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validates phone number format
     * @param phone The phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // Remove spaces, hyphens, and parentheses
        String cleanPhone = phone.replaceAll("[\\s\\-()]", "");
        Matcher matcher = phonePattern.matcher(cleanPhone);
        return matcher.matches();
    }

    /**
     * Validates ISBN format (both ISBN-10 and ISBN-13)
     * @param isbn The ISBN to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidISBN(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }
        // Remove hyphens and spaces
        String cleanIsbn = isbn.replaceAll("[\\s\\-]", "");
        
        return isValidISBN10(cleanIsbn) || isValidISBN13(cleanIsbn);
    }

    /**
     * Validates ISBN-10 format
     * @param isbn The ISBN-10 to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidISBN10(String isbn) {
        if (isbn == null || !isbn10Pattern.matcher(isbn).matches()) {
            return false;
        }
        
        // Calculate checksum for ISBN-10
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (isbn.charAt(i) - '0') * (10 - i);
        }
        
        char lastChar = isbn.charAt(9);
        if (lastChar == 'X') {
            sum += 10;
        } else {
            sum += (lastChar - '0');
        }
        
        return (sum % 11 == 0);
    }

    /**
     * Validates ISBN-13 format
     * @param isbn The ISBN-13 to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidISBN13(String isbn) {
        if (isbn == null || !isbn13Pattern.matcher(isbn).matches()) {
            return false;
        }
        
        // Calculate checksum for ISBN-13
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = isbn.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == (isbn.charAt(12) - '0');
    }

    /**
     * Validates that a string is not null or empty
     * @param str The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validates that a string contains only letters and spaces
     * @param str The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isAlphabetic(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return str.matches("^[a-zA-Z\\s]+$");
    }

    /**
     * Validates that a string is a positive integer
     * @param str The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isPositiveInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            int num = Integer.parseInt(str);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that a string is a valid number (integer or decimal)
     * @param str The string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates string length
     * @param str The string to validate
     * @param minLength Minimum length
     * @param maxLength Maximum length
     * @return true if valid, false otherwise
     */
    public static boolean isValidLength(String str, int minLength, int maxLength) {
        if (str == null) {
            return false;
        }
        int length = str.trim().length();
        return length >= minLength && length <= maxLength;
    }

    /**
     * Validates password strength
     * @param password The password to validate
     * @return true if valid (at least 6 characters), false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        return true;
    }

    /**
     * Validates username format (alphanumeric and underscore only)
     * @param username The username to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
    }
}
