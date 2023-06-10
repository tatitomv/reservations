package com.lmontev.restaurant.reservation.service.integration.hours.dto.input;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HourIntegrationIDTO {
    private Long id;
    private LocalTime hour;
}
