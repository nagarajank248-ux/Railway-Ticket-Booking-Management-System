package com.zsgs.trainx.controller;

import com.zsgs.trainx.data.dto.Booking;
import com.zsgs.trainx.data.dto.BookingRequest;
import com.zsgs.trainx.data.dto.BookingResponse;
import com.zsgs.trainx.data.dto.RefundResponse;
import com.zsgs.trainx.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public BookingResponse book(@RequestBody BookingRequest request) {
        return bookingService.book(request);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsForUser(@PathVariable int userId) {
        return bookingService.getBookingsForUser(userId);
    }

    @PostMapping("/cancel/{bookingId}")
    public RefundResponse cancel(@PathVariable int bookingId) {
        return bookingService.cancel(bookingId);
    }

    @GetMapping("/all")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
