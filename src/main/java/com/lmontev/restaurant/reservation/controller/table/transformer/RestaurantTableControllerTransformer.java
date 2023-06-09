package com.lmontev.restaurant.reservation.controller.table.transformer;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;

public interface RestaurantTableControllerTransformer {
    RestaurantTableRSDTO toRSDTO(RestaurantTableODTO tableODTO);

    RestaurantTableIDTO toIDTO(RestaurantTableRQDTO request);
}
