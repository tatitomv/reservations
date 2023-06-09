package com.lmontev.restaurant.reservation.controller.reservation.transformer.impl;

import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.ReservationControllerTransformer;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.impl.mapper.ReservationControllerMapper;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationControllerTransformerImpl implements ReservationControllerTransformer {

    @Autowired
    private ReservationControllerMapper mapper;

    @Override
    public AvailableTablesIDTO toAvailableTablesIDTO(String date) {
        return mapper.toAvailableTablesIDTO(date);
    }

    @Override
    public AvailableTablesRSDTO toAvailableTablesRSDTO(AvailableTablesODTO availableTables) {
        return mapper.toAvailableTablesRSDTO(availableTables);
    }

    @Override
    public ReservationControllerIDTO toReservationIDTO(ReservationRQDTO reservationRQDTO) {
        return mapper.toReservationIDTO(reservationRQDTO);
    }

    @Override
    public ReservationRSDTO toReservationRSDTO(ReservationODTO reservationODTO) {
        return mapper.toReservationRSDTO(reservationODTO);
    }

    @Override
    public ReservationControllerIDTO toEditReservationIDTO(Long id, ReservationRQDTO reservationRQDTO) {
        return mapper.toEditReservationIDTO(id, reservationRQDTO);
    }

    @Override
    public ReservationsByDateIDTO toReservationByDateIDTO(String date) {
        return mapper.toReservationByDateIDTO(date);
    }
}
