package com.lmontev.restaurant.reservation.service.integration.reservation.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationIntegrationMapper {

    @Mapping(target = "maxDiners", source = "tableMO.maxDiners")
    @Mapping(target = "tableId", source = "tableMO.id")
    ReservationIntegrationIDTO toIdto(ReservationMO reservationMO);

    @Mapping(target = "tableMO", source = "table")
    ReservationMO toReservationMO(ReservationControllerIDTO reservationControllerIDTO);
}
