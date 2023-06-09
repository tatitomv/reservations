package com.lmontev.restaurant.reservation.api.reservations;

import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/reservations")
public interface ReservationsApi {

    @GetMapping("/getAvailableReservations")
    AvailableTablesRSDTO getAvailableTablesByDate(@RequestParam String date);

    @PutMapping("/reserve")
    ReservationRSDTO reserve(@RequestBody ReservationRQDTO reservationRQDTO);

    @GetMapping("/reserve/{id}")
    ReservationRSDTO getReservationById(@PathVariable Long id);

    @PostMapping("/reserve/{id}")
    ReservationRSDTO editReservation(@PathVariable Long id, @RequestBody ReservationRQDTO reservationRQDTO);

    @GetMapping("/reserve")
    List<ReservationRSDTO> getReservesByDate(@RequestParam String date);
}
