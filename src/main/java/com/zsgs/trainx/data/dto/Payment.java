package com.zsgs.trainx.data.dto;

public class Payment {
    private int paymentId;
    private int bookingId;
    private double amount;
    private String paymentMode; // "cash", "card", "uip"
    private String paymentStatus; // "pending", "success", "failed", "refund"
    private String transactionId;
    private String paymentDate;

    public Payment() {}

    public Payment(int paymentId, int bookingId, double amount,
                   String paymentMode, String paymentStatus,
                   String transactionId, String paymentDate) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return String.format("Payment[%d] Booking#%d | ₹%.2f | Mode: %s | Status: %s | TxnID: %s",
                paymentId, bookingId, amount, paymentMode, paymentStatus, transactionId);
    }
}
