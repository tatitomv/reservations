package com.lmontev.restaurant.reservation.service.integration.reservation.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationBaseDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private Long quantity;
    private Long tableId;
    private Long maxDiners;
}
