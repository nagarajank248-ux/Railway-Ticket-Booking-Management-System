package com.zsgs.trainx;

import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.dto.UserResponse;
import com.zsgs.trainx.features.admin.AdminModel;
import com.zsgs.trainx.features.admin.AdminView;
import com.zsgs.trainx.features.home.HomeModel;
import com.zsgs.trainx.features.home.HomeView;
import com.zsgs.trainx.features.signin.SignInModel;
import com.zsgs.trainx.features.signin.SignInView;
import com.zsgs.trainx.features.signup.SignUpModel;
import com.zsgs.trainx.features.signup.SignUpView;
import com.zsgs.trainx.util.ConsoleInput;

public class ConsoleApplication {

    public static void main(String[] args) {


        SignInModel signInModel = new SignInModel();
        HomeModel homeModel = new HomeModel();

        boolean running = true;
        while (running) {

            System.out.println("TRAINX RAILWAY SYSTEM");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit ");

            int choice = ConsoleInput.readMenuChoice("  Choice: ", 0, 2);

            switch (choice) {
                case 1 -> {
                    SignInView signInView = new SignInView(signInModel);
                    UserResponse response = signInView.show();

                    if (response.isSuccess()) {
                        User user = response.getUser();
                        if ("ADMIN".equals(user.getRole())) {
                            AdminModel adminModel = new AdminModel();
                            AdminView adminView = new AdminView(adminModel);
                            adminView.show();
                        } else {
                            HomeView homeView = new HomeView(homeModel, user);
                            homeView.show();
                        }
                        signInModel.logout();
                    }
                }
                case 2 -> {
                    SignUpModel signUpModel = new SignUpModel();
                    SignUpView signUpView = new SignUpView(signUpModel);
                    signUpView.show();

                }
                case 0 -> {
                    System.out.println("\n  Thank you for using TrainX. Goodbye!");
                    running = false;
                }
            }
        }

        ConsoleInput.close();
    }

}
