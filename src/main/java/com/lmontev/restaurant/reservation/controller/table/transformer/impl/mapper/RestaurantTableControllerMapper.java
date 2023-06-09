package com.lmontev.restaurant.reservation.controller.table.transformer.impl.mapper;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.mapstruct.Mapper;

@Mapper
public interface RestaurantTableControllerMapper {

    RestaurantTableRSDTO toRSDTO(RestaurantTableODTO tableODTO);

    RestaurantTableIDTO toIDTO(RestaurantTableRQDTO request);
}
