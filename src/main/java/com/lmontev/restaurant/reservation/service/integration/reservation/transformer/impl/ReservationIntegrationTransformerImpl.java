package com.lmontev.restaurant.reservation.service.integration.reservation.transformer.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.ReservationIntegrationTransformer;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.impl.mapper.ReservationIntegrationMapper;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationIntegrationTransformerImpl implements ReservationIntegrationTransformer {

    @Autowired
    private ReservationIntegrationMapper mapper;

    @Override
    public ReservationIntegrationIDTO toIDTO(ReservationMO reservationMO) {
        return mapper.toIdto(reservationMO);
    }

    @Override
    public ReservationMO toReservationMO(ReservationControllerIDTO reservationControllerIDTO) {
        return mapper.toReservationMO(reservationControllerIDTO);
    }
}
