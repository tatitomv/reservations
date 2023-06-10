package com.lmontev.restaurant.reservation.service.manager.hours.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import org.mapstruct.Mapper;

@Mapper
public interface HoursManagerMapper {

    HourManagerODTO toODTO(HourIntegrationIDTO idto);
}
