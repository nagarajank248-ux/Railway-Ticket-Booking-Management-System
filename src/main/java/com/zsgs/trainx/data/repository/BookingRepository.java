package com.zsgs.trainx.data.repository;

import com.zsgs.trainx.data.dto.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(int userId);
    List<Booking> findByPassengerNameIgnoreCase(String passengerName);
}
