package com.zsgs.trainx.features.payment;

import com.zsgs.trainx.data.dto.Payment;
import com.zsgs.trainx.util.ConsoleInput;

public class PaymentView {

    private final PaymentModel model;

    public PaymentView(PaymentModel model) {
        this.model = model;
    }

    public void show(int bookingId, double amount) {
        System.out.println("PAYMENT");
        System.out.println("--------------------------------");
        System.out.printf( "Booking ID : %-14d -%n", bookingId);
        System.out.printf( "Amount     : ₹%-13.2f -%n", amount);
        System.out.println("--------------------------------");
        System.out.println("Payment Mode:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash");
        System.out.println("---------------------------------");

        int choice = ConsoleInput.readMenuChoice("  Choose mode (1-3): ", 1, 3);
        String mode = switch (choice) {
            case 1 -> "UPI";
            case 2 -> "CARD";
            default -> "CASH";
        };

        if (mode.equals("UPI")) {
            ConsoleInput.readString("  Enter UPI ID : ");
        } else if (mode.equals("CARD")) {
            ConsoleInput.readString("  Card Number  : ");
            ConsoleInput.readString("  CVV          : ");
            ConsoleInput.readString("  Expiry (MM/YY): ");
        }

        System.out.println("\n  Processing payment...");
        Payment payment = model.processPayment(bookingId, amount, mode);

        System.out.println("\n Payment Successful!");
        System.out.println("  " + payment);
    }
}
