package com.zsgs.trainx.features.cancel;

import com.zsgs.trainx.data.dto.CancelRequest;
import com.zsgs.trainx.data.dto.RefundResponse;
import com.zsgs.trainx.data.dto.TicketResponse;
import com.zsgs.trainx.features.booking.BookingModel;
import com.zsgs.trainx.features.payment.PaymentModel;

import java.util.UUID;

public class CancelModel {

    private final BookingModel bookingModel;

    public CancelModel(BookingModel bookingModel) {
        this.bookingModel = bookingModel;
    }

    public RefundResponse cancel(CancelRequest request) {
        TicketResponse ticket = bookingModel.getBookingById(request.getBookingId());

        if (ticket == null) {
            return new RefundResponse(false, "Booking not found.", request.getBookingId(),
                    0, "FAILED", "-", "-");
        }

        if (ticket.getBookingStatus().equals("CANCELLED")) {
            return new RefundResponse(false, "Booking already cancelled.", request.getBookingId(),
                    0, "FAILED", "-", "-");
        }

        boolean cancelled = bookingModel.cancelBooking(request.getBookingId());
        if (!cancelled) {
            return new RefundResponse(false, "Cancellation failed.", request.getBookingId(),
                    0, "FAILED", "-", "-");
        }

        // 80% refund policy
        double refundAmount  = ticket.getTotalFare() * 0.80;
        String refundTxnId   = "RFND" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String estimatedDate = "3-5 business days";

        return new RefundResponse(true, "Cancellation successful. Refund initiated.",
                request.getBookingId(), refundAmount, "INITIATED", refundTxnId, estimatedDate);
    }

    public TicketResponse getBookingById(int bookingId) {
        return bookingModel.getBookingById(bookingId);
    }
}
