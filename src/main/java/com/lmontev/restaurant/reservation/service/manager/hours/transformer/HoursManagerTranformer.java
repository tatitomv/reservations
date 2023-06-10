package com.lmontev.restaurant.reservation.service.manager.hours.transformer;

import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;

public interface HoursManagerTranformer {

    HourManagerODTO toODTO(HourIntegrationIDTO idto);
}
