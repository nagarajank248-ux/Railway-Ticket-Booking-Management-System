package com.zsgs.trainx.data.dto;

public class BookingRequest {
    private int userId;
    private int scheduleId;
    private int numberOfSeats;
    private String passengerName;
    private int passengerAge;

    public BookingRequest() {}

    public BookingRequest(int userId, int scheduleId, int numberOfSeats,
                          String passengerName, int passengerAge) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.numberOfSeats = numberOfSeats;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public int getNumberOfSeats() { return numberOfSeats; }
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }
}
