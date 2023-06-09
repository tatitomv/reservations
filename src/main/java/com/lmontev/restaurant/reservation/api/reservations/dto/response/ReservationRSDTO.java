package com.lmontev.restaurant.reservation.api.reservations.dto.response;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationRSDTO {
    private Long id;
    private String name;
    private Long quantity;
    private LocalDate date;
    private LocalTime time;
    private RestaurantTableRSDTO table;
}
