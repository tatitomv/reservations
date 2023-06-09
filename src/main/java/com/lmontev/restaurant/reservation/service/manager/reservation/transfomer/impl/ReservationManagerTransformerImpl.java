package com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.ReservationManagerTransformer;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.impl.mapper.ReservationManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationManagerTransformerImpl implements ReservationManagerTransformer {

    @Autowired
    private ReservationManagerMapper mapper;

    @Override
    public ReservationODTO toODTO(ReservationIntegrationIDTO reservationIntegrationIDTO) {
        return mapper.toReservationODTO(reservationIntegrationIDTO);
    }

    @Override
    public RestaurantTableIDTO toReservationTableIDTO(RestaurantTableODTO restaurantTableODTO) {
        return mapper.toReservationTableIDTO(restaurantTableODTO);
    }
}
