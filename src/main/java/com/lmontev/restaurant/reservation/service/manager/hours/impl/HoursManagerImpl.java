package com.lmontev.restaurant.reservation.service.manager.hours.impl;

import com.lmontev.restaurant.reservation.service.integration.hours.HoursIntegration;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.HoursManager;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.HoursManagerTranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class HoursManagerImpl implements HoursManager {

    @Autowired
    private HoursIntegration hoursIntegration;

    @Autowired
    private HoursManagerTranformer tranformer;

    @Override
    public List<LocalTime> getAllAvailableHours() {
        return hoursIntegration.allHoursAvailables();
    }

    @Override
    public HourManagerODTO addHour(HourManagerIDTO idto) {
        final HourIntegrationIDTO hourIntegrationIDTO = hoursIntegration.addHour(idto.getHour());
        return tranformer.toODTO(hourIntegrationIDTO);
    }

    @Override
    public void deleteHour(HourManagerIDTO idto) {
        hoursIntegration.deleteHour(idto.getHour());
    }
}
