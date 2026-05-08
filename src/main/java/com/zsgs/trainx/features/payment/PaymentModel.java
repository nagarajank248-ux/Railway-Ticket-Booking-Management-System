package com.zsgs.trainx.features.payment;

import com.zsgs.trainx.data.dto.Payment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentModel {

    private static final List<Payment> paymentStore = new ArrayList<>();

    public Payment processPayment(int bookingId, double amount, String paymentMode) {
        String txnId = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String date  = LocalDate.now().toString();

        Payment payment = new Payment(
                paymentStore.size() + 1,
                bookingId,
                amount,
                paymentMode,
                "SUCCESS",
                txnId,
                date
        );

        paymentStore.add(payment);
        return payment;
    }

    public Payment getPaymentByBookingId(int bookingId) {
        return paymentStore.stream()
                .filter(p -> p.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    public static List<Payment> getPaymentStore() {
        return paymentStore;
    }
}
