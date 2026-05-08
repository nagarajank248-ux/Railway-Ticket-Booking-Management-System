package com.zsgs.trainx.features.home;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeModel {

    private static final List<Train> trainStore = new ArrayList<>();
    private static final List<Schedule> scheduleStore = new ArrayList<>();

    static {
        // Seed trains
        trainStore.add(new Train(1, "12345", "Express One",  "Tirunelveli",   "Chennai",    200, 120, 850.0));
        trainStore.add(new Train(2, "67890", "VanthBharath",     "Chennai",     "Bangalore", 300, 200, 1200.0));
        trainStore.add(new Train(3, "11223", "MGR",     "Coimbatore","Chennai",   150, 80,  450.0));
        trainStore.add(new Train(4, "44556", "Mumbai Exp",      "Mumbai",    "Kolkata",   250, 175, 980.0));

        // Seed schedules
        scheduleStore.add(new Schedule(1, 1, "2026-05-10", "06:00", "18:00", 120));
        scheduleStore.add(new Schedule(2, 2, "2026-05-11", "08:00", "22:00", 200));
        scheduleStore.add(new Schedule(3, 3, "2026-05-12", "07:30", "11:30",  80));
        scheduleStore.add(new Schedule(4, 4, "2026-05-13", "09:00", "23:00", 175));
    }

    public TrainResponse searchTrains(String source, String destination) {
        List<Train> result = trainStore.stream()
                .filter(t -> t.getSource().equalsIgnoreCase(source)
                          && t.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            return new TrainResponse(false, "No trains found for the given route.", null);
        }
        return new TrainResponse(true, "Trains found.", result);
    }

    public TrainResponse getAllTrains() {
        return new TrainResponse(true, "All trains.", trainStore);
    }

    public List<Schedule> getSchedulesForTrain(int trainId) {
        return scheduleStore.stream()
                .filter(s -> s.getTrainId() == trainId)
                .collect(Collectors.toList());
    }

    public Schedule getScheduleById(int scheduleId) {
        return scheduleStore.stream()
                .filter(s -> s.getScheduleId() == scheduleId)
                .findFirst()
                .orElse(null);
    }

    public Train getTrainById(int trainId) {
        return trainStore.stream()
                .filter(t -> t.getTrainId() == trainId)
                .findFirst()
                .orElse(null);
    }

    // Expose stores to other models
    public static List<Train> getTrainStore()       { return trainStore; }
    public static List<Schedule> getScheduleStore() { return scheduleStore; }
}
