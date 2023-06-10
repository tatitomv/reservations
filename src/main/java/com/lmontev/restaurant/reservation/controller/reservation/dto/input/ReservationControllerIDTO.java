package com.lmontev.restaurant.reservation.controller.reservation.dto.input;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationControllerIDTO {
    private Long id;
    private String name;
    private Long quantity;
    private LocalDate date;
    private LocalTime time;
    private RestaurantTableIDTO table;
    private String contact;
    private String email;
}
