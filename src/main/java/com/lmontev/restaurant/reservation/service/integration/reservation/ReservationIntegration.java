package com.lmontev.restaurant.reservation.service.integration.reservation;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservationIntegration {

    List<ReservationIntegrationIDTO> findReservationsByDate(LocalDate date);

    List<ReservationIntegrationIDTO> findReservationsByDateAndDiners(LocalDate date, Long diners);

    ReservationIntegrationIDTO reserve(ReservationControllerIDTO reservationControllerIDTO);

    ReservationIntegrationIDTO findReservationById(Long id);

    ReservationIntegrationIDTO editReservation(ReservationControllerIDTO reservationControllerIDTO);
}
