package com.lmontev.restaurant.reservation.controller.table.transformer.impl;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.controller.table.transformer.RestaurantTableControllerTransformer;
import com.lmontev.restaurant.reservation.controller.table.transformer.impl.mapper.RestaurantTableControllerMapper;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantTableControllerTransformerImpl implements RestaurantTableControllerTransformer {

    @Autowired
    private RestaurantTableControllerMapper mapper;

    @Override
    public RestaurantTableRSDTO toRSDTO(RestaurantTableODTO tableODTO) {
        return mapper.toRSDTO(tableODTO);
    }

    @Override
    public RestaurantTableIDTO toIDTO(RestaurantTableRQDTO request) {
        return mapper.toIDTO(request);
    }
}
