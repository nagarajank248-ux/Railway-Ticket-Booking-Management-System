package com.zsgs.trainx.features.booking;

import com.zsgs.trainx.data.dto.*;
import com.zsgs.trainx.features.home.HomeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingModel {

    // Shared booking store across features
    private static final List<TicketResponse> bookingStore = new ArrayList<>();

    private final HomeModel homeModel;

    public BookingModel(HomeModel homeModel) {
        this.homeModel = homeModel;
    }

    public BookingResponse book(BookingRequest request) {
        Schedule schedule = homeModel.getScheduleById(request.getScheduleId());
        if (schedule == null) {
            return new BookingResponse(false, "Schedule not found.", 0, 0, "FAILED");
        }
        if (schedule.getAvailableSeats() < request.getNumberOfSeats()) {
            return new BookingResponse(false, "Not enough seats available.", 0, 0, "FAILED");
        }

        Train train = homeModel.getTrainById(schedule.getTrainId());
        if (train == null) {
            return new BookingResponse(false, "Train not found.", 0, 0, "FAILED");
        }

        double totalFare = train.getFarePerSeat() * request.getNumberOfSeats();
        int bookingId = bookingStore.size() + 1;
        String pnr = "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Reduce available seats
        schedule.setAvailableSeats(schedule.getAvailableSeats() - request.getNumberOfSeats());

        // Build ticket
        TicketResponse ticket = new TicketResponse();
        ticket.setSuccess(true);
        ticket.setMessage("Booking confirmed.");
        ticket.setBookingId(bookingId);
        ticket.setPnrNumber(pnr);
        ticket.setPassengerName(request.getPassengerName());
        ticket.setPassengerAge(request.getPassengerAge());
        ticket.setTrainName(train.getTrainName());
        ticket.setTrainNumber(train.getTrainNumber());
        ticket.setSource(train.getSource());
        ticket.setDestination(train.getDestination());
        ticket.setJourneyDate(schedule.getJourneyDate());
        ticket.setDepartureTime(schedule.getDepartureTime());
        ticket.setArrivalTime(schedule.getArrivalTime());
        ticket.setNumberOfSeats(request.getNumberOfSeats());
        ticket.setTotalFare(totalFare);
        ticket.setBookingStatus("CONFIRMED");

        bookingStore.add(ticket);

        return new BookingResponse(true, "Booking successful!", bookingId, totalFare, "CONFIRMED");
    }

    public List<TicketResponse> getBookingsForUser(int userId, String userName) {
        // Simple match by passenger name (extend with userId field if needed)
        return bookingStore.stream()
                .filter(t -> t.getPassengerName().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }

    public TicketResponse getBookingById(int bookingId) {
        return bookingStore.stream()
                .filter(t -> t.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    public boolean cancelBooking(int bookingId) {
        for (TicketResponse t : bookingStore) {
            if (t.getBookingId() == bookingId && t.getBookingStatus().equals("CONFIRMED")) {
                t.setBookingStatus("CANCELLED");
                return true;
            }
        }
        return false;
    }

    public HomeModel getHomeModel() {
        return homeModel;
    }

    public static List<TicketResponse> getBookingStore() {
        return bookingStore;
    }
}
