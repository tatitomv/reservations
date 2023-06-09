package com.lmontev.restaurant.reservation.api.table;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tables")
public interface RestaurantTableApi {

    @GetMapping("/table/{id}")
    RestaurantTableRSDTO getTable(@PathVariable Long id);

    @GetMapping
    List<RestaurantTableRSDTO> getTables();

    @PutMapping("/table")
    RestaurantTableRSDTO addTable(@RequestBody  RestaurantTableRQDTO request);

    @PostMapping("/table")
    RestaurantTableRSDTO editTable(@RequestBody RestaurantTableRQDTO request);
}
