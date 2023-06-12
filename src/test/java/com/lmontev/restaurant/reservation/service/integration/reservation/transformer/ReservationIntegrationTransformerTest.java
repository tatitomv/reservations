package com.lmontev.restaurant.reservation.service.integration.reservation.transformer;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.impl.ReservationIntegrationTransformerImpl;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.impl.mapper.ReservationIntegrationMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReservationIntegrationTransformerTest {

    @Spy
    private ReservationIntegrationMapper mapper = Mappers.getMapper(ReservationIntegrationMapper.class);

    @InjectMocks
    private ReservationIntegrationTransformerImpl transformer;

    @Test
    public void shouldTransformToIDTO(){
        //given
        final ReservationMO reservationMO = Instancio.of(ReservationMO.class).create();

        //when
        final ReservationIntegrationIDTO actual = transformer.toIDTO(reservationMO);

        //then
        verify(mapper).toIdto(reservationMO);
        assertThat(actual, notNullValue());
        assertThat(reservationMO.getId(), is(actual.getId()));
        assertThat(reservationMO.getName(), is(actual.getName()));
        assertThat(reservationMO.getDate(), is(actual.getDate()));
        assertThat(reservationMO.getTime(), is(actual.getTime()));
        assertThat(reservationMO.getQuantity(), is(actual.getQuantity()));
        final RestaurantTableMO expectedTableMO = reservationMO.getTableMO();
        assertThat(expectedTableMO.getId(), is(actual.getTableId()));
        assertThat(expectedTableMO.getMaxDiners(), is(actual.getMaxDiners()));
        assertThat(reservationMO.getContact(), is(actual.getContact()));
        assertThat(reservationMO.getEmail(), is(actual.getEmail()));
    }

    @Test
    public void shouldTransformToReservationMO(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = Instancio.of(ReservationControllerIDTO.class)
                .create();

        //when
        final ReservationMO actual = transformer.toReservationMO(reservationControllerIDTO);

        //then
        verify(mapper).toReservationMO(reservationControllerIDTO);
        RestaurantTableMO actualTableMO = actual.getTableMO();
        RestaurantTableIDTO expectedTable = reservationControllerIDTO.getTable();
        assertThat(expectedTable.getId(), is(actualTableMO.getId()));
        assertThat(expectedTable.getMaxDiners(), is(actualTableMO.getMaxDiners()));
        assertThat(reservationControllerIDTO.getId(), is(actual.getId()));
        assertThat(reservationControllerIDTO.getName(), is(actual.getName()));
        assertThat(reservationControllerIDTO.getQuantity(), is(actual.getQuantity()));
        assertThat(reservationControllerIDTO.getDate(), is(actual.getDate()));
        assertThat(reservationControllerIDTO.getTime(), is(actual.getTime()));
        assertThat(reservationControllerIDTO.getContact(), is(actual.getContact()));
        assertThat(reservationControllerIDTO.getEmail(), is(actual.getEmail()));
    }
}
