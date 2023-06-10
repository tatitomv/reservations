package com.lmontev.restaurant.reservation.controller.hours.transformer;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;

public interface HoursControllerTransformer {

    HourManagerIDTO toHourManagerIDTO(HourRQDTO hourRQDTO);

    HourRSDTO toHourRSDTO(HourManagerODTO hourManagerODTO);
}
