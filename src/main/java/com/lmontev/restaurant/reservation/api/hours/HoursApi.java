package com.lmontev.restaurant.reservation.api.hours;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RequestMapping("/hours")
public interface HoursApi {

    @GetMapping("/allAvailableHours")
    List<LocalTime> getAllAvailableHours();

    @PutMapping()
    HourRSDTO addHour(@RequestBody HourRQDTO hourRQDTO);

    @DeleteMapping()
    void deleteHour(@RequestBody HourRQDTO hourRQDTO);
}
