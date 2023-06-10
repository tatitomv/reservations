package com.lmontev.restaurant.reservation.api.reservations.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRQDTO {
    private String name;
    private Long quantity;
    private LocalDate date;
    private LocalTime time;
    private String contact;
    private String email;
}
