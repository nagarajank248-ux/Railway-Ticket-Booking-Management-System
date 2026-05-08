package com.zsgs.trainx.service;

import com.zsgs.trainx.data.dto.*;
import com.zsgs.trainx.data.repository.BookingRepository;
import com.zsgs.trainx.data.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public BookingResponse book(BookingRequest request) {
        Schedule schedule = trainService.getScheduleById(request.getScheduleId());
        if (schedule == null) {
            return new BookingResponse(false, "Schedule not found.", 0, 0, "FAILED");
        }

        if (schedule.getAvailableSeats() < request.getNumberOfSeats()) {
            return new BookingResponse(false, "Not enough seats available.", 0, 0, "FAILED");
        }

        Train train = trainService.getTrainById(schedule.getTrainId());
        if (train == null) {
            return new BookingResponse(false, "Train not found.", 0, 0, "FAILED");
        }

        double totalFare = train.getFarePerSeat() * request.getNumberOfSeats();
        String pnr = "PNR" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Update seat count
        schedule.setAvailableSeats(schedule.getAvailableSeats() - request.getNumberOfSeats());
        scheduleRepository.save(schedule);

        // Save booking
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setScheduleId(request.getScheduleId());
        booking.setPnrNumber(pnr);
        booking.setPassengerName(request.getPassengerName());
        booking.setPassengerAge(request.getPassengerAge());
        booking.setNumberOfSeats(request.getNumberOfSeats());
        booking.setTotalFare(totalFare);
        booking.setBookingStatus("CONFIRMED");
        booking.setJourneyDate(schedule.getJourneyDate());
        booking.setTrainName(train.getTrainName());

        booking = bookingRepository.save(booking);

        return new BookingResponse(true, "Booking successful!", booking.getBookingId(), totalFare, "CONFIRMED");
    }

    public List<Booking> getBookingsForUser(int userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByPassenger(String passengerName) {
        return bookingRepository.findByPassengerNameIgnoreCase(passengerName);
    }

    public RefundResponse cancel(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null && "CONFIRMED".equals(booking.getBookingStatus())) {
            booking.setBookingStatus("CANCELLED");
            bookingRepository.save(booking);

            double refundAmount = booking.getTotalFare() * 0.8;
            String refundId = "RFND" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

            return new RefundResponse(true, "Cancellation successful.", bookingId,
                    refundAmount, "INITIATED", refundId, "3 Days");
        }

        return new RefundResponse(false, "Booking not found or already cancelled.",
                bookingId, 0, "FAILED", "-", "-");
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
