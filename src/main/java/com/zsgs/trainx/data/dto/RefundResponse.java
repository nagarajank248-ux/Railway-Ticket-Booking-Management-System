package com.zsgs.trainx.data.dto;

public class RefundResponse {
    private boolean success;
    private String message;
    private int bookingId;
    private double refundAmount;
    private String refundStatus; // "initiate", "processed", "failed"
    private String refundTransactionId;
    private String estimatedArrival;

    public RefundResponse() {}

    public RefundResponse(boolean success, String message, int bookingId,
                          double refundAmount, String refundStatus,
                          String refundTransactionId, String estimatedArrival) {
        this.success = success;
        this.message = message;
        this.bookingId = bookingId;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundTransactionId = refundTransactionId;
        this.estimatedArrival = estimatedArrival;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public double getRefundAmount() { return refundAmount; }
    public void setRefundAmount(double refundAmount) { this.refundAmount = refundAmount; }

    public String getRefundStatus() { return refundStatus; }
    public void setRefundStatus(String refundStatus) { this.refundStatus = refundStatus; }

    public String getRefundTransactionId() { return refundTransactionId; }
    public void setRefundTransactionId(String refundTransactionId) { this.refundTransactionId = refundTransactionId; }

    public String getEstimatedArrival() { return estimatedArrival; }
    public void setEstimatedArrival(String estimatedArrival) { this.estimatedArrival = estimatedArrival; }

    public void printRefundDetails() {

        System.out.println("TRAINX - REFUND DETAILS");
        System.out.println("-----------------------------");
        System.out.printf("Booking ID   : %-28d -%n", bookingId);
        System.out.printf("Refund Amount: ₹%-27.2f -%n", refundAmount);
        System.out.printf("Status       : %-28s -%n", refundStatus);
        System.out.printf("Txn ID       : %-28s-%n", refundTransactionId);
        System.out.printf("Est. Arrival : %-28s -%n", estimatedArrival);

    }
}
