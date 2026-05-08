package com.zsgs.trainx.util;

import com.zsgs.trainx.data.dto.Schedule;
import com.zsgs.trainx.data.dto.Train;
import com.zsgs.trainx.data.dto.User;
import com.zsgs.trainx.data.repository.ScheduleRepository;
import com.zsgs.trainx.data.repository.TrainRepository;
import com.zsgs.trainx.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Seed Users
        if (userRepository.count() == 0) {
            userRepository.save(new User(0, "Admin", "admin@trainx.com", "admin123", "9000000000", "ADMIN"));
            userRepository.save(new User(0, "Alice", "alice@example.com", "alice123", "9111111111", "USER"));
        }

        // Seed Trains
        if (trainRepository.count() == 0) {
            trainRepository.save(new Train(0, "12345", "Express One", "Tirunelveli", "Chennai", 200, 120, 850.0));
            trainRepository.save(new Train(0, "67890", "Vande Bharat", "Chennai", "Bangalore", 300, 200, 1200.0));
            trainRepository.save(new Train(0, "11223", "MGR Express", "Coimbatore", "Chennai", 150, 80, 450.0));
        }

        // Seed Schedules
        if (scheduleRepository.count() == 0) {
            scheduleRepository.save(new Schedule(0, 1, "2026-05-10", "06:00", "18:00", 120));
            scheduleRepository.save(new Schedule(0, 2, "2026-05-11", "08:00", "22:00", 200));
            scheduleRepository.save(new Schedule(0, 3, "2026-05-12", "07:30", "11:30", 80));
        }
        
        System.out.println(">> Database Seeded with initial TrainX data.");
    }
}
