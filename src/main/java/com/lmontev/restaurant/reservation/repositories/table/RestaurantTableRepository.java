package com.lmontev.restaurant.reservation.repositories.table;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTableMO,Long> {
    List<RestaurantTableMO> findByMaxDinersGreaterThanEqualOrderByMaxDinersAsc(Long quantity);
}
