package com.zsgs.trainx.service;

import com.zsgs.trainx.data.dto.LoginRequest;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.dto.UserRequest;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            if (u.getPassword().equals(request.getPassword())) {
                return new UserResponse(true, "Login successful. Welcome, " + u.getName() + "!", u);
            }
        }
        return new UserResponse(false, "Invalid email or password.", null);
    }

    public UserResponse register(UserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new UserResponse(false, "Email already registered.", null);
        }

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setPhone(request.getPhone());
        newUser.setRole("USER");
        
        userRepository.save(newUser);
        return new UserResponse(true, "Registration successful!", newUser);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
