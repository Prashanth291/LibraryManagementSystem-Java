package com.library.LibrarySystem.services;

import com.library.LibrarySystem.models.User;
import com.library.LibrarySystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        
        if (optionalUser.isEmpty()) {
            System.out.println("Error: User not found!");
            return false;
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(oldPassword)) {
            System.out.println("Error: Incorrect old password!");
            return false;
        }

        if (newPassword.length() < 6) {
            System.out.println("Error: Password must be at least 6 characters long!");
            return false;
        }

        user.setPassword(newPassword);
        userRepository.save(user); // Use save() instead of update()
        System.out.println("✓ Password changed successfully!");
        return true;
    }
    
    public boolean deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        
        if (optionalUser.isEmpty()) {
            System.out.println("Error: User not found!");
            return false;
        }
        
        User user = optionalUser.get();
        
        userRepository.delete(user); 
        System.out.println("✓ User deleted successfully!");
        return true;
    }
}