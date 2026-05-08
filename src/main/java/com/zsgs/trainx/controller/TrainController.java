package com.zsgs.trainx.controller;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.TrainResponse;
import com.zsgs.trainx.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping
    public TrainResponse getAllTrains() {
        return trainService.getAllTrains();
    }

    @GetMapping("/search")
    public TrainResponse search(@RequestParam String from, @RequestParam String to) {
        return trainService.searchTrains(from, to);
    }

    @GetMapping("/schedules")
    public List<Schedule> getAllSchedules() {
        return trainService.getAllSchedules();
    }

    @PostMapping("/add")
    public TrainResponse addTrain(@RequestBody Train train) {
        return trainService.addTrain(train);
    }

    @PostMapping("/schedules/add")
    public boolean addSchedule(@RequestBody Schedule schedule) {
        return trainService.addSchedule(schedule);
    }
}
