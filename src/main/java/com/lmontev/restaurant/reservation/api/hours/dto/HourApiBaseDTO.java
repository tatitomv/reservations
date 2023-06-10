package com.lmontev.restaurant.reservation.api.hours.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HourApiBaseDTO {
    private LocalTime hour;
}
