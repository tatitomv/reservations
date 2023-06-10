package com.lmontev.restaurant.reservation.service.integration.hours;

import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;

import java.time.LocalTime;
import java.util.List;

public interface HoursIntegration {

    List<LocalTime> allHoursAvailables();

    HourIntegrationIDTO addHour(LocalTime hour);

    void deleteHour(LocalTime hour);
}
