package com.zsgs.trainx.features.signin;

import com.zsgs.trainx.data.dto.LoginRequest;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.dto.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class SignInModel {

    // In-memory user store (replace with DB logic as needed)
    private static final List<User> userStore = new ArrayList<>();

    static {
        // Seed default users
        userStore.add(new User(1, "Admin", "admin@trainx.com", "admin123", "9000000000", "ADMIN"));
        userStore.add(new User(2, "Alice", "alice@example.com", "alice123", "9111111111", "USER"));
        userStore.add(new User(3, "Bob",   "bob@example.com",   "bob123",   "9222222222", "USER"));
    }

    private User loggedInUser = null;

    public UserResponse login(LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return new UserResponse(false, "Email cannot be empty.", null);
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return new UserResponse(false, "Password cannot be empty.", null);
        }

        for (User u : userStore) {
            if (u.getEmail().equalsIgnoreCase(request.getEmail())
                    && u.getPassword().equals(request.getPassword())) {
                loggedInUser = u;
                return new UserResponse(true, "Login successful. Welcome, " + u.getName() + "!", u);
            }
        }
        return new UserResponse(false, "Invalid email or password.", null);
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Expose user store so SignUpModel can add users
    public static List<User> getUserStore() {
        return userStore;
    }
}
