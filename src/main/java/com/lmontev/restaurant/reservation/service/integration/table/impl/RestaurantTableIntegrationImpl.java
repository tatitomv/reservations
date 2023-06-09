package com.lmontev.restaurant.reservation.service.integration.table.impl;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.repositories.table.RestaurantTableRepository;
import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.RestaurantTableIntegrationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestaurantTableIntegrationImpl implements RestaurantTableIntegration {

    @Autowired
    private RestaurantTableRepository repository;

    @Autowired
    private RestaurantTableIntegrationTransformer transformer;


    @Override
    public RestaurantTableODTO findTableById(Long id) {
        Optional<RestaurantTableMO> table = repository.findById(id);
        if (!table.isPresent()){
            throw new RuntimeException("no existe la tabla");
        }
        return transformer.toRestaurantTableODTO(table.get());
    }

    @Override
    public List<RestaurantTableODTO> findAllRestaurantTable() {
        return repository.findAll()
                .stream()
                .map(transformer::toRestaurantTableODTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantTableODTO addTable(RestaurantTableIDTO idto) {
        if(Objects.isNull(idto.getMaxDiners())){
            throw new RuntimeException("debes editar la mesa");
        }
        final RestaurantTableMO newTable = repository.save(transformer.toRestaurantTableMO(idto));
        return transformer.toRestaurantTableODTO(newTable);
    }

    @Override
    public RestaurantTableODTO editTable(RestaurantTableIDTO idto) {
        if(Objects.isNull(idto.getId())){
            throw new RuntimeException("debes crear la mesa");
        }
        final Optional<RestaurantTableMO> tableMO = repository.findById(idto.getId());
        if (!tableMO.isPresent()){
            throw new RuntimeException("no existe ninguna tabla con ese id");
        }
        final RestaurantTableMO editedTableMO = repository.save(transformer.toRestaurantTableMO(idto));
        return transformer.toRestaurantTableODTO(editedTableMO);
    }

    @Override
    public List<RestaurantTableODTO> findTablesByQuantity(Long quantity) {
        List<RestaurantTableMO> tables = repository.findByMaxDinersGreaterThanEqualOrderByMaxDinersAsc(quantity);
        return tables
                .stream()
                .map(transformer::toRestaurantTableODTO)
                .collect(Collectors.toList());
    }
}
