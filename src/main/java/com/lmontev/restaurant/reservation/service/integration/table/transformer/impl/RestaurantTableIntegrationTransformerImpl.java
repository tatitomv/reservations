package com.lmontev.restaurant.reservation.service.integration.table.transformer.impl;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.RestaurantTableIntegrationTransformer;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.impl.mapper.RestaurantTableIntegrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableIntegrationTransformerImpl implements RestaurantTableIntegrationTransformer {

    @Autowired
    private RestaurantTableIntegrationMapper mapper;
    @Override
    public RestaurantTableODTO toRestaurantTableODTO(RestaurantTableMO table) {
        return mapper.toRestaurantTableODTO(table);
    }

    @Override
    public RestaurantTableMO toRestaurantTableMO(RestaurantTableIDTO idto) {
        return mapper.toRestaurantTableMO(idto);
    }

    @Override
    public RestaurantTableIDTO toRestaurantTableIDTO(RestaurantTableMO restaurantTableMO) {
        return mapper.toRestaurantTableIDTO(restaurantTableMO);
    }
}
