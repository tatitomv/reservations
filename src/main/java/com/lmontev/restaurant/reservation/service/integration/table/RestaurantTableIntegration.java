package com.lmontev.restaurant.reservation.service.integration.table;

import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;

import java.util.List;

public interface RestaurantTableIntegration {

    RestaurantTableODTO findTableById(Long id);

    List<RestaurantTableODTO> findAllRestaurantTable();

    RestaurantTableODTO addTable(RestaurantTableIDTO idto);

    RestaurantTableODTO editTable(RestaurantTableIDTO idto);

    List<RestaurantTableODTO> findTablesByQuantity(Long quantity);

}
