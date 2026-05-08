package com.zsgs.trainx.features.signup;

import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.dto.UserRequest;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.features.signin.SignInModel;

import java.util.List;

public class SignUpModel {

    private final List<User> userStore = SignInModel.getUserStore();

    public UserResponse register(UserRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return new UserResponse(false, "Name cannot be empty.", null);
        }
        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            return new UserResponse(false, "Invalid email address.", null);
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            return new UserResponse(false, "Password must be at least 6 characters.", null);
        }
        if (request.getPhone() == null || request.getPhone().length() != 10) {
            return new UserResponse(false, "Phone number must be 10 digits.", null);
        }

        // Check duplicate email
        for (User u : userStore) {
            if (u.getEmail().equalsIgnoreCase(request.getEmail())) {
                return new UserResponse(false, "Email already registered. Please sign in.", null);
            }
        }

        int newId = userStore.size() + 1;
        User newUser = new User(newId, request.getName(), request.getEmail(),
                request.getPassword(), request.getPhone(), "USER");
        userStore.add(newUser);

        return new UserResponse(true, "Registration successful! You can now sign in.", newUser);
    }
}
