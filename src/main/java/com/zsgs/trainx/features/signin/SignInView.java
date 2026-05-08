package com.zsgs.trainx.features.signin;

import com.zsgs.trainx.data.dto.LoginRequest;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.util.ConsoleInput;

public class SignInView {

    private final SignInModel model;

    public SignInView(SignInModel model) {
        this.model = model;
    }

    public UserResponse show() {
        System.out.println("USER SIGN IN");

        String email    = ConsoleInput.readString("  Email    : ");
        String password = ConsoleInput.readPassword("  Password : ");

        LoginRequest request = new LoginRequest(email, password);
        UserResponse response = model.login(request);

        if (response.isSuccess()) {
            System.out.println("\n   " + response.getMessage());
        } else {
            System.out.println("\n   " + response.getMessage());
        }

        return response;
    }
}
