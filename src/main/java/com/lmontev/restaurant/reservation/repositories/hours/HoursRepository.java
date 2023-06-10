package com.lmontev.restaurant.reservation.repositories.hours;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface HoursRepository extends JpaRepository<HoursMO, Long> {

    List<HoursMO> findByHour(LocalTime hour);
}
