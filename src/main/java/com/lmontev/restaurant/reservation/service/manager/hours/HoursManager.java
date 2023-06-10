package com.lmontev.restaurant.reservation.service.manager.hours;

import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;

import java.time.LocalTime;
import java.util.List;

public interface HoursManager {

    List<LocalTime> getAllAvailableHours();

    HourManagerODTO addHour(HourManagerIDTO idto);

    void deleteHour(HourManagerIDTO idto);
}
