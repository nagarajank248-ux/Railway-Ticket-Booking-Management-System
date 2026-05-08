package com.zsgs.trainx.data.dto;

public class CancelRequest {
    private int bookingId;
    private int userId;
    private String reason;

    public CancelRequest() {}

    public CancelRequest(int bookingId, int userId, String reason) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.reason = reason;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
