package com.zsgs.trainx.data.dto;

import jakarta.persistence.*;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private int trainId;
    private String journeyDate;
    private String departureTime;
    private String arrivalTime;
    private int availableSeats;

    public Schedule() {}

    public Schedule(int scheduleId, int trainId, String journeyDate,
                    String departureTime, String arrivalTime, int availableSeats) {
        this.scheduleId = scheduleId;
        this.trainId = trainId;
        this.journeyDate = journeyDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }

    public int getTrainId() { return trainId; }
    public void setTrainId(int trainId) { this.trainId = trainId; }

    public String getJourneyDate() { return journeyDate; }
    public void setJourneyDate(String journeyDate) { this.journeyDate = journeyDate; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return String.format("Schedule[%d] Date: %s | Dep: %s | Arr: %s | Seats: %d",
                scheduleId, journeyDate, departureTime, arrivalTime, availableSeats);
    }
}
