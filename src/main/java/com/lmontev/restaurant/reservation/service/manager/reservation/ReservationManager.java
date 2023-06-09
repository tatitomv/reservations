package com.lmontev.restaurant.reservation.service.manager.reservation;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationManager {

    AvailableTablesODTO getAvailableTables(AvailableTablesIDTO date);

    ReservationODTO reserveTable(ReservationControllerIDTO reservationControllerIDTO);

    ReservationODTO getReservationById(Long id);

    ReservationODTO editReservation(ReservationControllerIDTO reservationControllerIDTO);

    List<ReservationODTO> getReservationsByDate(ReservationsByDateIDTO date);
}
