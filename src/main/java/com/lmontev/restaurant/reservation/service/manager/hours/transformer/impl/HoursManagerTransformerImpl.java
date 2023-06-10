package com.lmontev.restaurant.reservation.service.manager.hours.transformer.impl;

import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.HoursManagerTranformer;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.impl.mapper.HoursManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HoursManagerTransformerImpl implements HoursManagerTranformer {

    @Autowired
    private HoursManagerMapper mapper;

    @Override
    public HourManagerODTO toODTO(HourIntegrationIDTO idto) {
        return mapper.toODTO(idto);
    }
}
