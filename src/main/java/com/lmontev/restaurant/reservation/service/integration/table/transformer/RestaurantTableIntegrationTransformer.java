package com.lmontev.restaurant.reservation.service.integration.table.transformer;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;

public interface RestaurantTableIntegrationTransformer {

    RestaurantTableODTO toRestaurantTableODTO(RestaurantTableMO table);

    RestaurantTableMO toRestaurantTableMO(RestaurantTableIDTO idto);

    RestaurantTableIDTO toRestaurantTableIDTO(RestaurantTableMO restaurantTableMO);
}
