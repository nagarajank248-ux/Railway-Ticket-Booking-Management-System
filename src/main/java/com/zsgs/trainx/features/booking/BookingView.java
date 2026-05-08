package com.zsgs.trainx.features.booking;

import com.zsgs.trainx.data.dto.*;
import com.zsgs.trainx.features.payment.PaymentModel;
import com.zsgs.trainx.features.payment.PaymentView;
import com.zsgs.trainx.util.ConsoleInput;

import java.util.List;

public class BookingView {

    private final BookingModel model;
    private final User         currentUser;

    public BookingView(BookingModel model, User currentUser) {
        this.model       = model;
        this.currentUser = currentUser;
    }

    public void show() {
        System.out.println("BOOK A TICKET");

        // Show schedules
        List<Schedule> schedules = model.getHomeModel().getScheduleStore();
        if (schedules.isEmpty()) {
            System.out.println("  No schedules available.");
            return;
        }
        System.out.println("\n  Available Schedules:");
        schedules.forEach(s -> {
            Train t = model.getHomeModel().getTrainById(s.getTrainId());
            if (t != null) {
                System.out.printf("  [%d] %s | %s → %s | Date: %s | Seats: %d | ₹%.2f%n",
                        s.getScheduleId(), t.getTrainName(),
                        t.getSource(), t.getDestination(),
                        s.getJourneyDate(), s.getAvailableSeats(), t.getFarePerSeat());
            }
        });

        int scheduleId = ConsoleInput.readInt("\n  Enter Schedule ID: ");
        String pName   = ConsoleInput.readString("  Passenger Name  : ");
        int pAge       = ConsoleInput.readInt("  Passenger Age   : ");
        int seats      = ConsoleInput.readInt("  Number of Seats : ");

        BookingRequest request = new BookingRequest(
                currentUser.getUserId(), scheduleId, seats, pName, pAge);

        BookingResponse response = model.book(request);

        if (!response.isSuccess()) {
            System.out.println("  ✘ " + response.getMessage());
            return;
        }

        System.out.println("  ✔ " + response.getMessage());
        System.out.printf("  Total Fare: ₹%.2f%n", response.getTotalFare());

        // Proceed to payment
        String pay = ConsoleInput.readString("\n  Proceed to payment? (y/n): ");
        if (pay.equalsIgnoreCase("y")) {
            PaymentModel pModel = new PaymentModel();
            PaymentView  pView  = new PaymentView(pModel);
            pView.show(response.getBookingId(), response.getTotalFare());
        }

        // Print ticket
        TicketResponse ticket = model.getBookingById(response.getBookingId());
        if (ticket != null) {
            ticket.printTicket();
        }
    }

    public void showMyBookings() {
        System.out.println("MY BOOKINGS");

        List<TicketResponse> bookings = model.getBookingsForUser(
                currentUser.getUserId(), currentUser.getName());

        if (bookings.isEmpty()) {
            System.out.println("  No bookings found.");
            return;
        }

        for (TicketResponse t : bookings) {
            t.printTicket();
            ConsoleInput.pressEnterToContinue();
        }
    }
}
