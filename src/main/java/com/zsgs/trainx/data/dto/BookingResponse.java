package com.zsgs.trainx.data.dto;

public class BookingResponse {
    private boolean success;
    private String message;
    private int bookingId;
    private double totalFare;
    private String bookingStatus;

    public BookingResponse() {}

    public BookingResponse(boolean success, String message, int bookingId,
                           double totalFare, String bookingStatus) {
        this.success = success;
        this.message = message;
        this.bookingId = bookingId;
        this.totalFare = totalFare;
        this.bookingStatus = bookingStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
