package com.lmontev.restaurant.reservation.controller.reservation.impl;

import com.lmontev.restaurant.reservation.api.reservations.ReservationsApi;
import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.ReservationControllerTransformer;
import com.lmontev.restaurant.reservation.service.manager.reservation.ReservationManager;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationControllerImpl implements ReservationsApi {

    @Autowired
    private ReservationControllerTransformer transformer;

    @Autowired
    private ReservationManager manager;

    @Override
    public AvailableTablesRSDTO getAvailableTablesByDate(String date) {
        final AvailableTablesIDTO availableTablesIDTO = transformer.toAvailableTablesIDTO(date);
        final AvailableTablesODTO availableTables = manager.getAvailableTables(availableTablesIDTO);
        return transformer.toAvailableTablesRSDTO(availableTables);
    }

    @Override
    public ReservationRSDTO reserve(ReservationRQDTO reservationRQDTO) {
        ReservationODTO reservationODTO = manager.reserveTable(transformer.toReservationIDTO(reservationRQDTO));
        return transformer.toReservationRSDTO(reservationODTO);
    }

    @Override
    public ReservationRSDTO getReservationById(Long id) {
        final ReservationODTO reservationODTO = manager.getReservationById(id);
        return transformer.toReservationRSDTO(reservationODTO);
    }

    @Override
    public ReservationRSDTO editReservation(Long id, ReservationRQDTO reservationRQDTO) {
        final ReservationControllerIDTO reservationControllerIDTO = transformer
                .toEditReservationIDTO(id, reservationRQDTO);
        final ReservationODTO reservationODTO = manager.editReservation(reservationControllerIDTO);
        return transformer.toReservationRSDTO(reservationODTO);
    }

    @Override
    public List<ReservationRSDTO> getReservesByDate(String date) {
        final List<ReservationODTO> reservations = manager.getReservationsByDate(transformer.toReservationByDateIDTO(date));
        return reservations
                .stream()
                .map(transformer::toReservationRSDTO)
                .collect(Collectors.toList());
    }
}
