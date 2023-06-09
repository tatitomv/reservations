package com.lmontev.restaurant.reservation.controller.reservation.dto.input;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationsByDateIDTO {
    private LocalDate date;
}
