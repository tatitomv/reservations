package com.lmontev.restaurant.reservation.service.manager.table.impl;

import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.table.RestaurantTableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableManagerImpl implements RestaurantTableManager {

    @Autowired
    private RestaurantTableIntegration integration;

    @Override
    public RestaurantTableODTO findRestaurantTableById(Long id) {
        return integration.findTableById(id);
    }

    @Override
    public List<RestaurantTableODTO> findAllRestaurantTables() {
        return integration.findAllRestaurantTable();
    }

    @Override
    public RestaurantTableODTO addTable(RestaurantTableIDTO idto) {
        return integration.addTable(idto);
    }

    @Override
    public RestaurantTableODTO editTable(RestaurantTableIDTO idto) {
        return integration.editTable(idto);
    }
}
