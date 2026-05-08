package com.zsgs.trainx.features.admin;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.features.home.HomeModel;
import com.zsgs.trainx.features.signin.SignInModel;

import java.util.List;

public class AdminModel {

    private final List<Train> trainStore    = HomeModel.getTrainStore();
    private final List<Schedule> schedules  = HomeModel.getScheduleStore();
    private final List<User> userStore      = SignInModel.getUserStore();

    // ── Train Management ────────────────────────────────────────────────────

    public TrainResponse addTrain(Train train) {
        int newId = trainStore.size() + 1;
        train.setTrainId(newId);
        trainStore.add(train);
        return new TrainResponse(true, "Train added successfully.", List.of(train));
    }

    public TrainResponse updateTrain(int trainId, Train updated) {
        for (int i = 0; i < trainStore.size(); i++) {
            if (trainStore.get(i).getTrainId() == trainId) {
                updated.setTrainId(trainId);
                trainStore.set(i, updated);
                return new TrainResponse(true, "Train updated.", List.of(updated));
            }
        }
        return new TrainResponse(false, "Train not found.", null);
    }

    public TrainResponse deleteTrain(int trainId) {
        boolean removed = trainStore.removeIf(t -> t.getTrainId() == trainId);
        if (removed) {
            return new TrainResponse(true, "Train deleted.", null);
        }
        return new TrainResponse(false, "Train not found.", null);
    }

    public TrainResponse getAllTrains() {
        return new TrainResponse(true, "Fetched all trains.", trainStore);
    }

    // ── Schedule Management ─────────────────────────────────────────────────

    public boolean addSchedule(Schedule schedule) {
        int newId = schedules.size() + 1;
        schedule.setScheduleId(newId);
        schedules.add(schedule);
        return true;
    }

    public List<Schedule> getAllSchedules() {
        return schedules;
    }

    // ── User Management ─────────────────────────────────────────────────────

    public List<User> getAllUsers() {
        return userStore;
    }
}
