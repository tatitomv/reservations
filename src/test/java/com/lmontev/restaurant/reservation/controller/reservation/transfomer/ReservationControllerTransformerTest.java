package com.lmontev.restaurant.reservation.controller.reservation.transfomer;

import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.TableHoursRSDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.impl.ReservationControllerTransformerImpl;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.impl.mapper.ReservationControllerMapper;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.TableHourODTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTransformerTest {

    @Spy
    private ReservationControllerMapper mapper = Mappers.getMapper(ReservationControllerMapper.class);

    @InjectMocks
    private ReservationControllerTransformerImpl transformer;

    @Test
    public void shouldTransformToAvailableTablesIDTO(){
        //given
        final String date = "2022-01-05";

        //when
        final AvailableTablesIDTO actual = transformer.toAvailableTablesIDTO(date);

        //then
        verify(mapper).toAvailableTablesIDTO(date);
        final LocalDate dateExpected = LocalDate.of(2022,1,5);
        assertThat(dateExpected, is(actual.getDate()));
    }

    @Test
    public void shouldTransformToAvailableTablesRSDTO(){
        //given
        final AvailableTablesODTO availableTablesODTO = Instancio.of(AvailableTablesODTO.class).create();

        //when
        final AvailableTablesRSDTO actual = transformer.toAvailableTablesRSDTO(availableTablesODTO);

        //then
        verify(mapper).toAvailableTablesRSDTO(availableTablesODTO);
        assertThat(availableTablesODTO.getDate(), is(actual.getDate()));
        final List<TableHourODTO> expectedTables = availableTablesODTO.getTables();
        final List<TableHoursRSDTO> actualTables = actual.getTables();
        expectedTables
                .forEach( tableHourODTO -> assertTrue(assertTableODTO(tableHourODTO, actualTables)));
    }

    @Test
    public void shouldTransformToReservationControllerIDTO(){
        //given
        final ReservationRQDTO request = Instancio.of(ReservationRQDTO.class).create();

        //when
        final ReservationControllerIDTO actual = transformer.toReservationIDTO(request);

        //then
        verify(mapper).toReservationIDTO(request);
        assertThat(request.getName(), is(actual.getName()));
        assertThat(request.getQuantity(), is(actual.getQuantity()));
        assertThat(request.getDate(), is(actual.getDate()));
        assertThat(request.getTime(), is(actual.getTime()));
        assertThat(request.getContact(), is(actual.getContact()));
        assertThat(request.getEmail(), is(actual.getEmail()));
    }

    @Test
    public void shouldTransformToReservationRSDTO(){
        //given
        final ReservationODTO reservationODTO = Instancio.of(ReservationODTO.class).create();

        //when
        final ReservationRSDTO actual = transformer.toReservationRSDTO(reservationODTO);

        //then
        verify(mapper).toReservationRSDTO(reservationODTO);
        assertThat(reservationODTO.getId(), is(actual.getId()));
        assertThat(reservationODTO.getName(), is(actual.getName()));
        assertThat(reservationODTO.getQuantity(), is(actual.getQuantity()));
        assertThat(reservationODTO.getDate(), is(actual.getDate()));
        assertThat(reservationODTO.getTime(), is(actual.getTime()));
        assertThat(reservationODTO.getContact(), is(actual.getContact()));
        assertThat(reservationODTO.getEmail(), is(actual.getEmail()));
        final RestaurantTableODTO expectedTable = reservationODTO.getTable();
        final RestaurantTableRSDTO actualTable = actual.getTable();
        assertThat(expectedTable.getId(), is(actualTable.getId()));
        assertThat(expectedTable.getMaxDiners(), is(actualTable.getMaxDiners()));
    }

    @Test
    public void shouldTransformToEditReservationIDTO(){
        //given
        final Long id = Instancio.of(Long.class).create();
        final ReservationRQDTO request = Instancio.of(ReservationRQDTO.class).create();

        //when
        final ReservationControllerIDTO actual = transformer.toEditReservationIDTO(id, request);

        //then
        verify(mapper).toEditReservationIDTO(id, request);
        assertThat(id, is(actual.getId()));
        assertThat(request.getName(), is(actual.getName()));
        assertThat(request.getQuantity(), is(actual.getQuantity()));
        assertThat(request.getDate(), is(actual.getDate()));
        assertThat(request.getTime(), is(actual.getTime()));
        assertThat(request.getContact(), is(actual.getContact()));
        assertThat(request.getEmail(), is(actual.getEmail()));
    }

    @Test
    public void shouldTransformToReservationByDateIDTO(){
        //given
        final LocalDate date = Instancio.of(LocalDate.class).create();

        //when
        final ReservationsByDateIDTO actual = transformer.toReservationByDateIDTO(date.toString());

        //then
        verify(mapper).toReservationByDateIDTO(date.toString());
        assertThat(date, is(actual.getDate()));
    }

    private Boolean assertTableODTO(TableHourODTO tableHourODTO, List<TableHoursRSDTO> actualTables) {
        Optional<TableHoursRSDTO> first = actualTables
                .stream()
                .filter(tableHoursRSDTO -> tableHoursRSDTO.getTable().getId() == tableHourODTO.getTable().getId())
                .findFirst();
        if (!first.isPresent()){
            return Boolean.FALSE;
        }
        final TableHoursRSDTO tableHoursRSDTO = first.get();
        tableHoursRSDTO.getHours()
                .forEach(hour -> assertHour(hour, tableHourODTO.getHours()));
        return Boolean.TRUE;
    }

    private void assertHour(LocalTime hour, List<LocalTime> hours) {
        assertTrue(hours.contains(hour));
    }
}
