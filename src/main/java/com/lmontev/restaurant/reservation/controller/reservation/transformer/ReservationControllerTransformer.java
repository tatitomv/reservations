package com.lmontev.restaurant.reservation.controller.reservation.transformer;

import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;

public interface ReservationControllerTransformer {


    AvailableTablesIDTO toAvailableTablesIDTO(String date);

    AvailableTablesRSDTO toAvailableTablesRSDTO(AvailableTablesODTO availableTables);

    ReservationControllerIDTO toReservationIDTO(ReservationRQDTO reservationRQDTO);

    ReservationRSDTO toReservationRSDTO(ReservationODTO reservationODTO);

    ReservationControllerIDTO toEditReservationIDTO(Long id, ReservationRQDTO reservationRQDTO);

    ReservationsByDateIDTO toReservationByDateIDTO(String date);
}
