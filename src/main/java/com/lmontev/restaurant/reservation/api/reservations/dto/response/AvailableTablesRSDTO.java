package com.lmontev.restaurant.reservation.api.reservations.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailableTablesRSDTO {
    private LocalDate date;
    private List<TableHoursRSDTO> tables;
}
