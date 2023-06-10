package com.lmontev.restaurant.reservation.controller.hours.impl;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.controller.hours.HoursController;
import com.lmontev.restaurant.reservation.controller.hours.transformer.HoursControllerTransformer;
import com.lmontev.restaurant.reservation.service.manager.hours.HoursManager;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
public class HoursControllerImpl implements HoursController {

    @Autowired
    private HoursManager manager;

    @Autowired
    private HoursControllerTransformer transformer;

    @Override
    public List<LocalTime> getAllAvailableHours() {
        return manager.getAllAvailableHours();
    }

    @Override
    public HourRSDTO addHour(HourRQDTO hourRQDTO) {
        final HourManagerODTO hourManagerODTO = manager.addHour(transformer.toHourManagerIDTO(hourRQDTO));
        return transformer.toHourRSDTO(hourManagerODTO);
    }

    @Override
    public void deleteHour(HourRQDTO hourRQDTO) {
        manager.deleteHour(transformer.toHourManagerIDTO(hourRQDTO));
    }
}
