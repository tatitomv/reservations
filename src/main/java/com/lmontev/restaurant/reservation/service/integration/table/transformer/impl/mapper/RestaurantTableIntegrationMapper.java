package com.lmontev.restaurant.reservation.service.integration.table.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.mapstruct.Mapper;

@Mapper
public interface RestaurantTableIntegrationMapper {

    RestaurantTableODTO toRestaurantTableODTO(RestaurantTableMO restaurant);

    RestaurantTableMO toRestaurantTableMO(RestaurantTableIDTO idto);

    RestaurantTableIDTO toRestaurantTableIDTO(RestaurantTableMO tableMO);
}
