package com.zsgs.trainx.controller;

import com.zsgs.trainx.data.dto.LoginRequest;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.dto.UserRequest;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
