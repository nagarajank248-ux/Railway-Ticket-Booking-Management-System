package com.zsgs.trainx.features.admin;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.util.ConsoleInput;

import java.util.List;

public class AdminView {

    private final AdminModel model;

    public AdminView(AdminModel model) {
        this.model = model;
    }

    public void show() {
        boolean running = true;
        while (running) {
            System.out.println("---------ADMIN DASHBOARD--------");
            System.out.println("--------------------------------");
            System.out.println("1. View All Trains");
            System.out.println("2. Add Train");
            System.out.println("3. Update Train");
            System.out.println("4. Delete Train");
            System.out.println("5. Add Schedule");
            System.out.println("6. View All Schedule");
            System.out.println("7. View All Users");
            System.out.println("0. Logout");

            int choice = ConsoleInput.readMenuChoice("  Choice: ", 0, 7);
            switch (choice) {
                case 1 -> viewAllTrains();
                case 2 -> addTrain();
                case 3 -> updateTrain();
                case 4 -> deleteTrain();
                case 5 -> addSchedule();
                case 6 -> viewAllSchedules();
                case 7 -> viewAllUsers();
                case 0 -> running = false;
            }
        }
    }

    private void viewAllTrains() {
        TrainResponse resp = model.getAllTrains();
        System.out.println("\n  ── All Trains ──");
        if (resp.getTrains() == null || resp.getTrains().isEmpty()) {
            System.out.println("  No trains found.");
        } else {
            resp.getTrains().forEach(t -> System.out.println("  " + t));
        }
    }

    private void addTrain() {
        System.out.println("\n  ── Add Train ──");
        String number = ConsoleInput.readString("  Train Number : ");
        String name   = ConsoleInput.readString("  Train Name   : ");
        String src    = ConsoleInput.readString("  Source       : ");
        String dst    = ConsoleInput.readString("  Destination  : ");
        int total     = ConsoleInput.readInt("  Total Seats  : ");
        double fare   = ConsoleInput.readDouble("  Fare/Seat (₹): ");

        Train t = new Train(0, number, name, src, dst, total, total, fare);
        TrainResponse resp = model.addTrain(t);
        System.out.println("  " + (resp.isSuccess() ? "✔ " : "✘ ") + resp.getMessage());
    }

    private void updateTrain() {
        System.out.println("\n  ── Update Train ──");
        int id = ConsoleInput.readInt("  Train ID     : ");
        String number = ConsoleInput.readString("  Train Number : ");
        String name   = ConsoleInput.readString("  Train Name   : ");
        String src    = ConsoleInput.readString("  Source       : ");
        String dst    = ConsoleInput.readString("  Destination  : ");
        int total     = ConsoleInput.readInt("  Total Seats  : ");
        double fare   = ConsoleInput.readDouble("  Fare/Seat (₹): ");

        Train t = new Train(id, number, name, src, dst, total, total, fare);
        TrainResponse resp = model.updateTrain(id, t);
        System.out.println("  " + (resp.isSuccess() ? "✔ " : "✘ ") + resp.getMessage());
    }

    private void deleteTrain() {
        System.out.println("\n  ── Delete Train ──");
        int id = ConsoleInput.readInt("  Train ID: ");
        TrainResponse resp = model.deleteTrain(id);
        System.out.println("  " + (resp.isSuccess() ? "Yes " : "No") + resp.getMessage());
    }

    private void addSchedule() {
        System.out.println("\n  ── Add Schedule ──");
        int trainId   = ConsoleInput.readInt("  Train ID       : ");
        String date   = ConsoleInput.readString("  Journey Date   : ");
        String dep    = ConsoleInput.readString("  Departure Time : ");
        String arr    = ConsoleInput.readString("  Arrival Time   : ");
        int seats     = ConsoleInput.readInt("  Available Seats: ");

        Schedule s = new Schedule(0, trainId, date, dep, arr, seats);
        boolean ok = model.addSchedule(s);
        System.out.println("  " + (ok ? " Schedule added." : " Failed."));
    }

    private void viewAllSchedules() {
        List<Schedule> list = model.getAllSchedules();
        System.out.println("\n  ── All Schedules ──");
        if (list.isEmpty()) {
            System.out.println("  No schedules found.");
        } else {
            list.forEach(s -> System.out.println("  " + s));
        }
    }

    private void viewAllUsers() {
        List<User> users = model.getAllUsers();
        System.out.println("\n  ── All Users ──");
        users.forEach(u -> System.out.println("  " + u));
    }
}
