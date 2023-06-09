package com.lmontev.restaurant.reservation.service.manager.table;

import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;

import java.util.List;

public interface RestaurantTableManager {

    RestaurantTableODTO findRestaurantTableById(Long id);

    List<RestaurantTableODTO> findAllRestaurantTables();

    RestaurantTableODTO addTable(RestaurantTableIDTO idto);

    RestaurantTableODTO editTable(RestaurantTableIDTO idto);
}
