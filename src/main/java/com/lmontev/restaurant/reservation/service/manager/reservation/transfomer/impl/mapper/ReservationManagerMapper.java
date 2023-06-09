package com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.impl.mapper;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReservationManagerMapper {

    @Mapping(target = "table.id", source = "idto.tableId")
    @Mapping(target = "table.maxDiners", source = "idto.maxDiners")
    ReservationODTO toReservationODTO(ReservationIntegrationIDTO idto);

    RestaurantTableIDTO toReservationTableIDTO(RestaurantTableODTO restaurantTableODTO);
}
