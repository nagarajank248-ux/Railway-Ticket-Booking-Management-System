package com.zsgs.trainx.features.signup;

import com.zsgs.trainx.data.dto.UserRequest;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.util.ConsoleInput;

public class SignUpView {

    private final SignUpModel model;

    public SignUpView(SignUpModel model) {
        this.model = model;
    }

    public UserResponse show() {
        System.out.println("USER SIGN UP");

        String name     = ConsoleInput.readString("  Full Name : ");
        String email    = ConsoleInput.readString("  Email     : ");
        String password = ConsoleInput.readPassword("  Password  : ");
        String phone    = ConsoleInput.readString("  Phone     : ");

        UserRequest request = new UserRequest(name, email, password, phone);
        UserResponse response = model.register(request);

        if (response.isSuccess()) {
            System.out.println("\n  yes " + response.getMessage());
        } else {
            System.out.println("\n  No " + response.getMessage());
        }

        return response;
    }
}
