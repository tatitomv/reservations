package com.lmontev.restaurant.reservation.service.manager.hours.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HourManagerBaseDTO {
    private LocalTime hour;
}
