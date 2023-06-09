package com.lmontev.restaurant.reservation.entities.reservation;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
@Data
public class ReservationMO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long quantity;
    private LocalDate date;
    private LocalTime time;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private RestaurantTableMO tableMO;
}
