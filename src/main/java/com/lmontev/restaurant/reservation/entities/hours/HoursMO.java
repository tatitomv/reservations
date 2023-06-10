package com.lmontev.restaurant.reservation.entities.hours;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "hours")
@Data
public class HoursMO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalTime hour;
}
