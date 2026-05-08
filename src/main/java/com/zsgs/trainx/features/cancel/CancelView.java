package com.zsgs.trainx.features.cancel;

import com.zsgs.trainx.data.dto.CancelRequest;
import com.zsgs.trainx.data.dto.RefundResponse;
import com.zsgs.trainx.data.dto.TicketResponse;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.util.ConsoleInput;

public class CancelView {

    private final CancelModel model;
    private final User        currentUser;

    public CancelView(CancelModel model, User currentUser) {
        this.model       = model;
        this.currentUser = currentUser;
    }

    public void show() {

        System.out.println("CANCEL BOOKING");

        int bookingId = ConsoleInput.readInt("  Booking ID: ");

        TicketResponse ticket = model.getBookingById(bookingId);
        if (ticket == null) {
            System.out.println("   Booking not found.");
            return;
        }

        System.out.println("\n  Booking Details:");
        ticket.printTicket();

        String confirm = ConsoleInput.readString("\n  Confirm cancellation? (y/n): ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("  Cancellation aborted.");
            return;
        }

        String reason = ConsoleInput.readString("  Reason (optional): ");
        CancelRequest request = new CancelRequest(bookingId, currentUser.getUserId(), reason);

        RefundResponse refund = model.cancel(request);

        if (refund.isSuccess()) {
            System.out.println("\n  yes " + refund.getMessage());
            refund.printRefundDetails();
        } else {
            System.out.println("  NO " + refund.getMessage());
        }
    }
}
