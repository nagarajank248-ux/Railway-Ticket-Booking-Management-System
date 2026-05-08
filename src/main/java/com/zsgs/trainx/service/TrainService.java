package com.zsgs.trainx.service;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;
import com.zsgs.trainx.data.repository.ScheduleRepository;
import com.zsgs.trainx.data.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public TrainResponse searchTrains(String source, String destination) {
        List<Train> result = trainRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
        if (result.isEmpty()) {
            return new TrainResponse(false, "No trains found for the given route.", null);
        }
        return new TrainResponse(true, "Trains found.", result);
    }

    public TrainResponse getAllTrains() {
        return new TrainResponse(true, "Fetched all trains.", trainRepository.findAll());
    }

    public Train getTrainById(int trainId) {
        return trainRepository.findById(trainId).orElse(null);
    }

    public List<Schedule> getSchedulesForTrain(int trainId) {
        return scheduleRepository.findByTrainId(trainId);
    }

    public Schedule getScheduleById(int scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public TrainResponse addTrain(Train train) {
        trainRepository.save(train);
        return new TrainResponse(true, "Train added successfully.", List.of(train));
    }

    public boolean addSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
        return true;
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}
