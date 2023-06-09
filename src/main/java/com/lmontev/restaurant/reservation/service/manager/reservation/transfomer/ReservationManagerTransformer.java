package com.lmontev.restaurant.reservation.service.manager.reservation.transfomer;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;

public interface ReservationManagerTransformer {

    ReservationODTO toODTO(ReservationIntegrationIDTO reservationIntegrationIDTO);

    RestaurantTableIDTO toReservationTableIDTO(RestaurantTableODTO freeTable);
}
