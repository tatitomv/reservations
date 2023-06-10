package com.lmontev.restaurant.reservation.service.integration.hours.transformer;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;

import java.time.LocalTime;

public interface HoursIntegrationTransformer {

    HourIntegrationIDTO toIDTO(HoursMO hours);

    HoursMO toHoursMO(LocalTime hour);
}
