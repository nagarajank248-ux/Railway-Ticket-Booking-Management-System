package com.zsgs.trainx.data.repository;

import com.zsgs.trainx.data.dto.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByTrainId(int trainId);
}
