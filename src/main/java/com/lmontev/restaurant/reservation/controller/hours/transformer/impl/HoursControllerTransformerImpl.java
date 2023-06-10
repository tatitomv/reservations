package com.lmontev.restaurant.reservation.controller.hours.transformer.impl;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.controller.hours.transformer.HoursControllerTransformer;
import com.lmontev.restaurant.reservation.controller.hours.transformer.impl.mapper.HoursControllerMapper;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HoursControllerTransformerImpl implements HoursControllerTransformer {

    @Autowired
    private HoursControllerMapper mapper;
    @Override
    public HourManagerIDTO toHourManagerIDTO(HourRQDTO hourRQDTO) {
        return mapper.toHourManagerIDTO(hourRQDTO);
    }

    @Override
    public HourRSDTO toHourRSDTO(HourManagerODTO hourManagerODTO) {
        return mapper.toHourRSDTO(hourManagerODTO);
    }
}
