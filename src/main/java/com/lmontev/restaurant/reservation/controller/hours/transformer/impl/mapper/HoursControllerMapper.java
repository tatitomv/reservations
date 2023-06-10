package com.lmontev.restaurant.reservation.controller.hours.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import org.mapstruct.Mapper;

@Mapper
public interface HoursControllerMapper {

    HourManagerIDTO toHourManagerIDTO(HourRQDTO hourRQDTO);

    HourRSDTO toHourRSDTO(HourManagerODTO hourManagerODTO);
}
