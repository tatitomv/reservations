package com.lmontev.restaurant.reservation.service.manager.reservation.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.ReservationIntegration;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.ReservationManager;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.TableHourODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.ReservationManagerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationManagerImpl implements ReservationManager {

    private static final List<LocalTime> POSSIBLE_HOURS = Arrays.asList(LocalTime.of(13, 0),
            LocalTime.of(14, 0), LocalTime.of(15, 0),
            LocalTime.of(16, 0), LocalTime.of(20, 0),
            LocalTime.of(21, 0), LocalTime.of(22, 0));
    @Autowired
    private RestaurantTableIntegration tablesIntegration;

    @Autowired
    private ReservationIntegration reservationIntegration;

    @Autowired
    private ReservationManagerTransformer transformer;

    @Override
    public AvailableTablesODTO getAvailableTables(AvailableTablesIDTO availableTablesIDTO) {
        final List<RestaurantTableODTO> allRestaurantTable = tablesIntegration.findAllRestaurantTable();
        final List<ReservationIntegrationIDTO> reservations = reservationIntegration.findReservationsByDate(availableTablesIDTO.getDate());
        final AvailableTablesODTO availableTables = new AvailableTablesODTO();
        availableTables.setDate(availableTablesIDTO.getDate());
        final List<TableHourODTO> tableHours = getTablesFreeByHour(allRestaurantTable, reservations);
        availableTables
                .setTables(tableHours);
        return availableTables;
    }

    private List<TableHourODTO> getTablesFreeByHour(List<RestaurantTableODTO> allRestaurantTable, List<ReservationIntegrationIDTO> reservations) {
        return allRestaurantTable
                .stream()
                .map(table -> buildAvailableTable(reservations, table))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationODTO reserveTable(ReservationControllerIDTO reservationControllerIDTO) {
        final RestaurantTableODTO freeTable = getFreeTable(reservationControllerIDTO);
        reservationControllerIDTO.setTable(transformer.toReservationTableIDTO(freeTable));
        final ReservationIntegrationIDTO reservationIDTO = reservationIntegration.reserve(reservationControllerIDTO);
        return transformer.toODTO(reservationIDTO);
    }

    @Override
    public ReservationODTO getReservationById(Long id) {
        final ReservationIntegrationIDTO reservationIntegrationIDTO = reservationIntegration.findReservationById(id);
        return transformer.toODTO(reservationIntegrationIDTO);
    }

    @Override
    public ReservationODTO editReservation(ReservationControllerIDTO reservationControllerIDTO) {
        reservationControllerIDTO.setTable(transformer
                .toReservationTableIDTO(getFreeTable(reservationControllerIDTO)));
        final ReservationIntegrationIDTO reservationIDTO = reservationIntegration.editReservation(reservationControllerIDTO);
        return transformer.toODTO(reservationIDTO);
    }

    @Override
    public List<ReservationODTO> getReservationsByDate(ReservationsByDateIDTO date) {
        List<ReservationIntegrationIDTO> reservationsByDate = reservationIntegration
                .findReservationsByDate(date.getDate());
        return reservationsByDate
                .stream()
                .map(transformer::toODTO)
                .collect(Collectors.toList());
    }

    private RestaurantTableODTO getFreeTable(ReservationControllerIDTO reservationControllerIDTO) {
        final List<RestaurantTableODTO> allRestaurantTable = tablesIntegration
                .findTablesByQuantity(reservationControllerIDTO.getQuantity());
        final List<ReservationIntegrationIDTO> reservationsByDateAndDiners = reservationIntegration
                .findReservationsByDate(reservationControllerIDTO.getDate());
        final List<TableHourODTO> tablesFreeByHour = getTablesFreeByHour(allRestaurantTable,
                reservationsByDateAndDiners);
        final Optional<TableHourODTO> tableHourODTO = tablesFreeByHour.stream()
                .filter(table -> table.getHours().contains(reservationControllerIDTO.getTime()))
                .findFirst();
        if (!tableHourODTO.isPresent()){
            throw new RuntimeException("No hay mesas dispobibles");
        }
        return tableHourODTO.get().getTable();
    }

    private TableHourODTO buildAvailableTable(List<ReservationIntegrationIDTO> reservations, RestaurantTableODTO table) {
        final TableHourODTO tableHourODTO = new TableHourODTO();
        tableHourODTO.setTable(table);
        final List<LocalTime> hours = POSSIBLE_HOURS
                .stream()
                .filter(hour -> isAvailabe(reservations, hour, table))
                .collect(Collectors.toList());
        tableHourODTO.setHours(hours);
        return tableHourODTO;
    }

    private boolean isAvailabe(List<ReservationIntegrationIDTO> reservations, LocalTime hour, RestaurantTableODTO table) {
        return reservations
                .stream()
                .noneMatch(reservation -> reservation.getTableId() == table.getId()
                        && reservation.getTime() == hour);
    }
}
