package com.lmontev.restaurant.reservation.service.manager.reservation.dto.output;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AvailableTablesODTO {
    private LocalDate date;
    private List<TableHourODTO> tables;
}
