package com.zsgs.trainx.data.dto;

public class TicketResponse {
    private boolean success;
    private String message;
    private int bookingId;
    private String pnrNumber;
    private String passengerName;
    private int passengerAge;
    private String trainName;
    private String trainNumber;
    private String source;
    private String destination;
    private String journeyDate;
    private String departureTime;
    private String arrivalTime;
    private int numberOfSeats;
    private double totalFare;
    private String bookingStatus;

    public TicketResponse() {}

    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getPnrNumber() { return pnrNumber; }
    public void setPnrNumber(String pnrNumber) { this.pnrNumber = pnrNumber; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public String getTrainNumber() { return trainNumber; }
    public void setTrainNumber(String trainNumber) { this.trainNumber = trainNumber; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getNumberOfSeats() { return numberOfSeats; }
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public double getTotalFare() { return totalFare; }
    public void setTotalFare(double totalFare) { this.totalFare = totalFare; }

    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    public void printTicket() {
        System.out.println("TRAINX - BOOKING TICKET");
        System.out.println("--------------------------");
        System.out.printf("PNR       : %-31s -%n", pnrNumber);
        System.out.printf("Booking ID: %-31d -%n", bookingId);
        System.out.printf("Passenger : %-31s -%n", passengerName);
        System.out.printf("Age       : %-31d -%n", passengerAge);
        System.out.println("----------------------------------");
        System.out.printf("Train     : [%s] %-20s -%n", trainNumber, trainName);
        System.out.printf("From      : %-31s -%n", source);
        System.out.printf("To        : %-31s -%n", destination);
        System.out.printf("Date      : %-31s -%n", journeyDate);
        System.out.printf("Departure : %-31s -%n", departureTime);
        System.out.printf("Arrival   : %-31s -%n", arrivalTime);
        System.out.println("-------------------------------------");
        System.out.printf("Seats     : %-31d -%n", numberOfSeats);
        System.out.printf("Total Fare: ₹%-30.2f -%n", totalFare);
        System.out.printf("Status    : %-31s -%n", bookingStatus);
    }
}
