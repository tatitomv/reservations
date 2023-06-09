package com.lmontev.restaurant.reservation.service.integration.reservation.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.repositories.reservation.ReservationRepository;
import com.lmontev.restaurant.reservation.service.integration.reservation.ReservationIntegration;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.ReservationIntegrationTransformer;
import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationIntegrationImpl implements ReservationIntegration {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ReservationIntegrationTransformer transformer;

    @Autowired
    private RestaurantTableIntegration restaurantTableIntegration;

    @Override
    public List<ReservationIntegrationIDTO> findReservationsByDate(LocalDate date) {
        final List<ReservationMO> reservations = repository.findByDate(date);
        return reservations
                .stream()
                .map(transformer::toIDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationIntegrationIDTO> findReservationsByDateAndDiners(LocalDate date, Long diners) {
        return repository
                .findByDateAndTableMOMaxDinersGreaterThanEqual(date, diners)
                .stream()
                .map(transformer::toIDTO)
                .sorted(Comparator.comparing(ReservationIntegrationIDTO::getMaxDiners))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationIntegrationIDTO reserve(ReservationControllerIDTO reservationControllerIDTO) {
        final ReservationMO reservationMO = transformer.toReservationMO(reservationControllerIDTO);
        ReservationMO reservationComplete = repository.save(reservationMO);
        return transformer.toIDTO(reservationComplete);
    }

    @Override
    public ReservationIntegrationIDTO findReservationById(Long id) {
        Optional<ReservationMO> reservationMO = repository.findById(id);
        if(!reservationMO.isPresent()){
            throw new RuntimeException("No existe la reservacion");
        }
        return transformer.toIDTO(reservationMO.get());
    }

    @Override
    public ReservationIntegrationIDTO editReservation(ReservationControllerIDTO reservationControllerIDTO) {
        final Optional<ReservationMO> optionalReservationMO = repository
                .findById(reservationControllerIDTO.getId());
        if (!optionalReservationMO.isPresent()){
            throw new RuntimeException("No existe la reserva");
        }
        final ReservationMO reservationMO = transformer.toReservationMO(reservationControllerIDTO);
        final ReservationMO reservationEdited = repository.save(reservationMO);
        return transformer.toIDTO(reservationEdited);
    }
}
