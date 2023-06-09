package com.lmontev.restaurant.reservation.controller.table.impl;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.controller.table.RestaurantTableController;
import com.lmontev.restaurant.reservation.controller.table.transformer.RestaurantTableControllerTransformer;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.table.RestaurantTableManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestaurantTableControllerImpl implements RestaurantTableController {

    @Autowired
    private RestaurantTableManager manager;

    @Autowired
    private RestaurantTableControllerTransformer transformer;

    @Override
    public RestaurantTableRSDTO getTable(Long id) {
        final RestaurantTableODTO tableODTO = manager.findRestaurantTableById(id);
        return transformer.toRSDTO(tableODTO);
    }

    @Override
    public List<RestaurantTableRSDTO> getTables() {
        final List<RestaurantTableODTO> tableODTOS = manager.findAllRestaurantTables();
        return tableODTOS
                .stream()
                .map(transformer::toRSDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantTableRSDTO addTable(RestaurantTableRQDTO request) {
        final RestaurantTableIDTO idto = transformer.toIDTO(request);
        final RestaurantTableODTO newTable = manager.addTable(idto);
        return transformer.toRSDTO(newTable);
    }

    @Override
    public RestaurantTableRSDTO editTable(RestaurantTableRQDTO request) {
        final RestaurantTableIDTO idto = transformer.toIDTO(request);
        final RestaurantTableODTO tableODTO = manager.editTable(idto);
        return transformer.toRSDTO(tableODTO);
    }
}
