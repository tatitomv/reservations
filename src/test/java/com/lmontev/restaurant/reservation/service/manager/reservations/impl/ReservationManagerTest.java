package com.lmontev.restaurant.reservation.service.manager.reservations.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.exception.handler.error.NotAvailableTableException;
import com.lmontev.restaurant.reservation.service.integration.reservation.ReservationIntegration;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.hours.HoursManager;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.TableHourODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.impl.ReservationManagerImpl;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.ReservationManagerTransformer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationManagerTest {

    @Mock
    private RestaurantTableIntegration tablesIntegration;

    @Mock
    private HoursManager hoursManager;

    @Mock
    private ReservationIntegration reservationIntegration;

    @Mock
    private ReservationManagerTransformer transformer;

    @InjectMocks
    private ReservationManagerImpl manager;

    @Test
    public void shouldGetAvailableTables(){
        //given
        final AvailableTablesIDTO availableTablesIDTO = mock(AvailableTablesIDTO.class);
        final RestaurantTableODTO restaurantTable = mock(RestaurantTableODTO.class);
        final ReservationIntegrationIDTO reservations = mock(ReservationIntegrationIDTO.class);
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final List<LocalTime> hours = Instancio.ofList(LocalTime.class).size(5).create();
        when(reservations.getTableId()).thenReturn(1L);
        when(restaurantTable.getId()).thenReturn(1L);
        when(reservations.getTime()).thenReturn(hours.get(0));
        when(hoursManager.getAllAvailableHours()).thenReturn(hours);
        when(availableTablesIDTO.getDate()).thenReturn(date);
        when(reservationIntegration.findReservationsByDate(availableTablesIDTO.getDate()))
                .thenReturn(Collections.singletonList(reservations));
        when(tablesIntegration.findAllRestaurantTable())
                .thenReturn(Collections.singletonList(restaurantTable));

        //when
        final AvailableTablesODTO actual = manager.getAvailableTables(availableTablesIDTO);

        //then
        TableHourODTO tableHourODTO = actual.getTables()
                .stream()
                .filter(table -> findTable(table, 1l))
                .findFirst().get();
        List<LocalTime> hours1 = tableHourODTO.getHours();
        assertTrue(!hours1.contains(hours.get(0)));
        final InOrder inOrder = inOrder(hoursManager,reservationIntegration,tablesIntegration);
        inOrder.verify(tablesIntegration).findAllRestaurantTable();
        inOrder.verify(reservationIntegration).findReservationsByDate(availableTablesIDTO.getDate());
        inOrder.verify(hoursManager).getAllAvailableHours();
        inOrder.verifyNoMoreInteractions();

    }

    @Test
    public void shouldReserveTable(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        final RestaurantTableODTO restauranTableODTO = mock(RestaurantTableODTO.class);
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final List<LocalTime> availableHours = Instancio.ofList(LocalTime.class).size(5).create();
        final RestaurantTableIDTO restaurantTableIDTO = mock(RestaurantTableIDTO.class);
        final ReservationIntegrationIDTO reservationIntegrationIDTO = mock(ReservationIntegrationIDTO.class);
        final ReservationODTO reservationODTO = mock(ReservationODTO.class);
        when(transformer.toODTO(reservationIntegrationIDTO)).thenReturn(reservationODTO);
        when(reservationIntegration.reserve(reservationControllerIDTO)).thenReturn(reservationIntegrationIDTO);
        when(transformer.toReservationTableIDTO(restauranTableODTO)).thenReturn(restaurantTableIDTO);
        when(reservationControllerIDTO.getTime()).thenReturn(availableHours.get(0));
        when(hoursManager.getAllAvailableHours()).thenReturn(availableHours);
        when(reservationControllerIDTO.getDate()).thenReturn(date);
        when(reservationControllerIDTO.getQuantity()).thenReturn(2L);
        when(tablesIntegration.findTablesByQuantity(reservationControllerIDTO.getQuantity()))
                .thenReturn(Collections.singletonList(restauranTableODTO));


        //when
        final ReservationODTO actual = manager.reserveTable(reservationControllerIDTO);

        //then
        final InOrder inOrder = inOrder(hoursManager,reservationIntegration,tablesIntegration,transformer);
        inOrder.verify(tablesIntegration).findTablesByQuantity(reservationControllerIDTO.getQuantity());
        inOrder.verify(reservationIntegration).findReservationsByDate(reservationControllerIDTO.getDate());
        inOrder.verify(hoursManager).getAllAvailableHours();
        inOrder.verify(transformer).toReservationTableIDTO(restauranTableODTO);
        inOrder.verify(reservationIntegration).reserve(reservationControllerIDTO);
        inOrder.verify(transformer).toODTO(reservationIntegrationIDTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotReserveTable(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        final RestaurantTableODTO restauranTableODTO = mock(RestaurantTableODTO.class);
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final List<LocalTime> availableHours = Instancio.ofList(LocalTime.class).size(1).create();
        final ReservationIntegrationIDTO reservationIDTO = mock(ReservationIntegrationIDTO.class);
        when(reservationControllerIDTO.getDate()).thenReturn(date);
        when(reservationIDTO.getTime()).thenReturn(availableHours.get(0));
        when(reservationIntegration.findReservationsByDate(reservationControllerIDTO.getDate()))
                .thenReturn(Collections.singletonList(reservationIDTO));
        when(reservationControllerIDTO.getTime()).thenReturn(availableHours.get(0));
        when(hoursManager.getAllAvailableHours()).thenReturn(availableHours);
        when(reservationControllerIDTO.getQuantity()).thenReturn(2L);
        when(tablesIntegration.findTablesByQuantity(reservationControllerIDTO.getQuantity()))
                .thenReturn(Collections.singletonList(restauranTableODTO));

        assertThrows(NotAvailableTableException.class, () -> {
            manager.reserveTable(reservationControllerIDTO);
        });
    }

    @Test
    public void shouldGetReservationByID(){
        //given
        final Long RESERVATION_ID = 1l;
        final ReservationIntegrationIDTO reservation = mock(ReservationIntegrationIDTO.class);
        final ReservationODTO reservationODTO = mock(ReservationODTO.class);
        when(transformer.toODTO(reservation)).thenReturn(reservationODTO);
        when(reservationIntegration.findReservationById(RESERVATION_ID)).thenReturn(reservation);

        //when
        final ReservationODTO actual = manager.getReservationById(RESERVATION_ID);

        //then
        final InOrder inOrder = inOrder(transformer, reservationIntegration);
        inOrder.verify(reservationIntegration).findReservationById(RESERVATION_ID);
        inOrder.verify(transformer).toODTO(reservation);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldEditTable(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        final RestaurantTableODTO restaurantTableODTO = mock(RestaurantTableODTO.class);
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final List<LocalTime> availableHours = Instancio.ofList(LocalTime.class).size(3).create();
        final RestaurantTableIDTO restaurantTableIDTO = mock(RestaurantTableIDTO.class);
        final ReservationIntegrationIDTO reservationIntegrationIDTO = mock(ReservationIntegrationIDTO.class);
        final ReservationODTO reservationODTO = mock(ReservationODTO.class);
        when(reservationControllerIDTO.getDate()).thenReturn(date);
        when(reservationControllerIDTO.getTime()).thenReturn(availableHours.get(0));
        when(transformer.toODTO(reservationIntegrationIDTO))
                .thenReturn(reservationODTO);
        when(reservationIntegration.editReservation(reservationControllerIDTO))
                .thenReturn(reservationIntegrationIDTO);
        when(transformer.toReservationTableIDTO(restaurantTableODTO))
                .thenReturn(restaurantTableIDTO);
        when(hoursManager.getAllAvailableHours()).thenReturn(availableHours);
        when(reservationIntegration
                .findReservationsByDate(reservationControllerIDTO.getDate()))
                .thenReturn(Collections.emptyList());
        when(reservationControllerIDTO.getQuantity()).thenReturn(2L);
        when(tablesIntegration
                .findTablesByQuantity(reservationControllerIDTO.getQuantity()))
                .thenReturn(Collections.singletonList(restaurantTableODTO));

        //when
        final ReservationODTO actual = manager.editReservation(reservationControllerIDTO);

        //then
        final InOrder inOrder = inOrder(tablesIntegration, hoursManager, reservationIntegration, transformer);
        inOrder.verify(tablesIntegration).findTablesByQuantity(reservationControllerIDTO.getQuantity());
        inOrder.verify(reservationIntegration).findReservationsByDate(reservationControllerIDTO.getDate());
        inOrder.verify(hoursManager).getAllAvailableHours();
        inOrder.verify(transformer).toReservationTableIDTO(restaurantTableODTO);
        inOrder.verify(reservationIntegration).editReservation(reservationControllerIDTO);
        inOrder.verify(transformer).toODTO(reservationIntegrationIDTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldGetReservationByDate(){
        //given
        final ReservationsByDateIDTO reservationsByDateIDTO = mock(ReservationsByDateIDTO.class);
        final ReservationIntegrationIDTO reservationIntegrationIDTO  = mock(ReservationIntegrationIDTO.class);
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final ReservationODTO reservationODTO = mock(ReservationODTO.class);
        when(reservationsByDateIDTO.getDate()).thenReturn(date);
        when(transformer.toODTO(reservationIntegrationIDTO)).thenReturn(reservationODTO);
        when(reservationIntegration.findReservationsByDate(reservationsByDateIDTO.getDate()))
                .thenReturn(Collections.singletonList(reservationIntegrationIDTO));

        //when
        final List<ReservationODTO> actual = manager.getReservationsByDate(reservationsByDateIDTO);

        //then
        final InOrder inOrder = inOrder(transformer, reservationIntegration);
        inOrder.verify(reservationIntegration).findReservationsByDate(reservationsByDateIDTO.getDate());
        inOrder.verify(transformer).toODTO(reservationIntegrationIDTO);
        inOrder.verifyNoMoreInteractions();
    }
    private boolean findTable(TableHourODTO table, Long id) {
        return table.getTable().getId() == id;
    }
}
