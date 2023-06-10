package com.lmontev.restaurant.reservation.service.integration.hours.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalTime;

@Mapper
public interface HoursIntegrationMapper {

    HourIntegrationIDTO toIdto(HoursMO hours);

}
