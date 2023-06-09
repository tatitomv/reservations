package com.lmontev.restaurant.reservation.api.reservations.dto.response;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TableHoursRSDTO {
    private RestaurantTableRSDTO table;
    private List<LocalTime> hours;
}
