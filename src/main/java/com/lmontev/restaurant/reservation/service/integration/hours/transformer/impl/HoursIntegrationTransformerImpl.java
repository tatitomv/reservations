package com.lmontev.restaurant.reservation.service.integration.hours.transformer.impl;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.HoursIntegrationTransformer;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.impl.mapper.HoursIntegrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class HoursIntegrationTransformerImpl implements HoursIntegrationTransformer {

    @Autowired
    private HoursIntegrationMapper mapper;

    @Override
    public HourIntegrationIDTO toIDTO(HoursMO hours) {
        return mapper.toIdto(hours);
    }

    @Override
    public HoursMO toHoursMO(LocalTime hour) {
        final HoursMO hoursMO = new HoursMO();
        hoursMO.setHour(hour);
        return hoursMO;
    }
}
