package com.zsgs.trainx.data.repository;

import com.zsgs.trainx.data.dto.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {
    List<Train> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
}
