package com.lmontev.restaurant.reservation.entities.table;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_tables")
@Data
public class RestaurantTableMO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long maxDiners;
}
