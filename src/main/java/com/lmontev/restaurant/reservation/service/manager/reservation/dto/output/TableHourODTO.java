package com.lmontev.restaurant.reservation.service.manager.reservation.dto.output;

import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TableHourODTO {
    private RestaurantTableODTO table;
    private List<LocalTime> hours;
}
