package com.lmontev.restaurant.reservation.service.integration.reservation.transformer;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;

public interface ReservationIntegrationTransformer {

    ReservationIntegrationIDTO toIDTO(ReservationMO reservationMO);

    ReservationMO toReservationMO(ReservationControllerIDTO reservationControllerIDTO);
}
