package com.lmontev.restaurant.reservation.repositories.reservation;

import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationMO, Long> {

    List<ReservationMO> findByDate(LocalDate date);

    List<ReservationMO> findByDateAndTableMOMaxDinersGreaterThanEqual(LocalDate date, Long quantity);
}
