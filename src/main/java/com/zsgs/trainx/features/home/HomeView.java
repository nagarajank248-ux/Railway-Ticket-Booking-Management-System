package com.zsgs.trainx.features.home;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.features.booking.BookingModel;
import com.zsgs.trainx.features.booking.BookingView;
import com.zsgs.trainx.features.cancel.CancelModel;
import com.zsgs.trainx.features.cancel.CancelView;
import com.zsgs.trainx.util.ConsoleInput;

import java.util.List;

public class HomeView {

    private final HomeModel  homeModel;
    private final User       currentUser;

    public HomeView(HomeModel homeModel, User currentUser) {
        this.homeModel   = homeModel;
        this.currentUser = currentUser;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.printf( "Welcome, %-22s -%n", currentUser.getName());
            System.out.println("--------------------------------------------");
            System.out.println("1. Search Trains");
            System.out.println("2. View All Trains");
            System.out.println("3. Book a Ticket");
            System.out.println("4. My Bookings / View Ticket");
            System.out.println("5. Cancel Booking");
            System.out.println("0. Logout");

            int choice = ConsoleInput.readMenuChoice("  Choice: ", 0, 5);
            switch (choice) {
                case 1 -> searchTrains();
                case 2 -> viewAllTrains();
                case 3 -> bookTicket();
                case 4 -> viewMyBookings();
                case 5 -> cancelBooking();
                case 0 -> running = false;
            }
        }
    }

    private void searchTrains() {
        System.out.println("\n  ── Search Trains ──");
        String src = ConsoleInput.readString("  From : ");
        String dst = ConsoleInput.readString("  To   : ");

        TrainResponse resp = homeModel.searchTrains(src, dst);
        if (!resp.isSuccess()) {
            System.out.println("  ✘ " + resp.getMessage());
            return;
        }
        System.out.println("\n  Results:");
        for (Train t : resp.getTrains()) {
            System.out.println("  " + t);
            List<Schedule> schedules = homeModel.getSchedulesForTrain(t.getTrainId());
            schedules.forEach(s -> System.out.println("    → " + s));
        }
    }

    private void viewAllTrains() {
        TrainResponse resp = homeModel.getAllTrains();
        System.out.println("\n  ── Available Trains ──");
        resp.getTrains().forEach(t -> System.out.println("  " + t));
    }

    private void bookTicket() {
        BookingModel bModel = new BookingModel(homeModel);
        BookingView  bView  = new BookingView(bModel, currentUser);
        bView.show();
    }

    private void viewMyBookings() {
        BookingModel bModel = new BookingModel(homeModel);
        BookingView  bView  = new BookingView(bModel, currentUser);
        bView.showMyBookings();
    }

    private void cancelBooking() {
        BookingModel bModel  = new BookingModel(homeModel);
        CancelModel  cModel  = new CancelModel(bModel);
        CancelView   cView   = new CancelView(cModel, currentUser);
        cView.show();
    }
}
