package com.lmontev.restaurant.reservation.service.manager.reservation.dto.output;

import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationODTO {
    private Long id;
    private String name;
    private Long quantity;
    private LocalDate date;
    private LocalTime time;
    private RestaurantTableODTO table;
}
