package com.lmontev.restaurant.reservation.service.manager.reservations.transformer;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.impl.ReservationManagerTransformerImpl;
import com.lmontev.restaurant.reservation.service.manager.reservation.transfomer.impl.mapper.ReservationManagerMapper;
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
public class ReservationManagerTransformerTest {

    @Spy
    private ReservationManagerMapper mapper = Mappers.getMapper(ReservationManagerMapper.class);

    @InjectMocks
    private ReservationManagerTransformerImpl transformer;

    @Test
    public void shouldTransformToODTO(){
        //given
        final ReservationIntegrationIDTO reservationIntegrationIDTO =
                Instancio.of(ReservationIntegrationIDTO.class).create();

        //when
        final ReservationODTO actual = transformer.toODTO(reservationIntegrationIDTO);

        //then
        verify(mapper).toReservationODTO(reservationIntegrationIDTO);
        assertThat(actual, notNullValue());
        assertThat(reservationIntegrationIDTO.getId(), is(actual.getId()));
        assertThat(reservationIntegrationIDTO.getName(), is(actual.getName()));
        assertThat(reservationIntegrationIDTO.getQuantity(), is(actual.getQuantity()));
        assertThat(reservationIntegrationIDTO.getDate(), is(actual.getDate()));
        assertThat(reservationIntegrationIDTO.getTime(), is(actual.getTime()));
        assertThat(reservationIntegrationIDTO.getContact(), is(actual.getContact()));
        assertThat(reservationIntegrationIDTO.getEmail(), is(actual.getEmail()));
        final RestaurantTableODTO actualTable = actual.getTable();
        assertThat(reservationIntegrationIDTO.getTableId(), is(actualTable.getId()));
    }

    @Test
    public void shouldTransformToReservationTableIDTO(){
        //given
        final RestaurantTableODTO restaurantTableODTO = Instancio
                .of(RestaurantTableODTO.class)
                .create();

        //when
        final RestaurantTableIDTO actual = transformer.toReservationTableIDTO(restaurantTableODTO);

        //then
        verify(mapper).toReservationTableIDTO(restaurantTableODTO);
        assertThat(actual, notNullValue());
        assertThat(restaurantTableODTO.getId(), is(actual.getId()));
        assertThat(restaurantTableODTO.getMaxDiners(), is(actual.getMaxDiners()));
    }
}
