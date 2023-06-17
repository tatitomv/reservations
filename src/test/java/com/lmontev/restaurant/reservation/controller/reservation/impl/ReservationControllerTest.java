package com.lmontev.restaurant.reservation.controller.reservation.impl;

import com.lmontev.restaurant.reservation.api.reservations.dto.request.ReservationRQDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.AvailableTablesRSDTO;
import com.lmontev.restaurant.reservation.api.reservations.dto.response.ReservationRSDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.AvailableTablesIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationsByDateIDTO;
import com.lmontev.restaurant.reservation.controller.reservation.transformer.ReservationControllerTransformer;
import com.lmontev.restaurant.reservation.service.manager.reservation.ReservationManager;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.AvailableTablesODTO;
import com.lmontev.restaurant.reservation.service.manager.reservation.dto.output.ReservationODTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationControllerTest {

    @Mock
    private ReservationControllerTransformer transformer;

    @Mock
    private ReservationManager manager;

    @InjectMocks
    private ReservationControllerImpl controller;

    @Test
    public void shouldGetAvailableTablesByDate(){
        //given
        final String date = Instancio.of(String.class).create();
        final AvailableTablesIDTO availableTablesIDTO = mock(AvailableTablesIDTO.class);
        when(transformer.toAvailableTablesIDTO(date)).thenReturn(availableTablesIDTO);
        final AvailableTablesODTO availablesTablesODTO = mock(AvailableTablesODTO.class);
        when(manager.getAvailableTables(availableTablesIDTO)).thenReturn(availablesTablesODTO);
        final AvailableTablesRSDTO availablesTablesRSDTO = mock(AvailableTablesRSDTO.class);
        when(transformer.toAvailableTablesRSDTO(availablesTablesODTO)).thenReturn(availablesTablesRSDTO);

        //when
        final AvailableTablesRSDTO actual = controller.getAvailableTablesByDate(date);

        //then
        final InOrder inOrder = inOrder(transformer, manager);
        inOrder.verify(transformer).toAvailableTablesIDTO(date);
        inOrder.verify(manager).getAvailableTables(availableTablesIDTO);
        inOrder.verify(transformer).toAvailableTablesRSDTO(availablesTablesODTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldReserve(){
        //given
        final ReservationRQDTO request = mock(ReservationRQDTO.class);
        final ReservationControllerIDTO idto = mock(ReservationControllerIDTO.class);
        when(transformer.toReservationIDTO(request)).thenReturn(idto);
        final ReservationODTO odto = mock(ReservationODTO.class);
        when(manager.reserveTable(idto)).thenReturn(odto);
        final ReservationRSDTO response = mock(ReservationRSDTO.class);
        when(transformer.toReservationRSDTO(odto)).thenReturn(response);

        //when
        final ReservationRSDTO actual = controller.reserve(request);

        //then
        final InOrder inOrder = inOrder(transformer, manager);
        inOrder.verify(transformer).toReservationIDTO(request);
        inOrder.verify(manager).reserveTable(idto);
        inOrder.verify(transformer).toReservationRSDTO(odto);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldGetReservationByID(){
        //given
        final Long id = 1L;
        final ReservationODTO odto = mock(ReservationODTO.class);
        when(manager.getReservationById(id)).thenReturn(odto);
        final ReservationRSDTO response = mock(ReservationRSDTO.class);
        when(transformer.toReservationRSDTO(odto)).thenReturn(response);

        //when
        final ReservationRSDTO actual = controller.getReservationById(id);

        //then
        final InOrder inOrder = inOrder(transformer, manager);
        inOrder.verify(manager).getReservationById(id);
        inOrder.verify(transformer).toReservationRSDTO(odto);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldEditReservation(){
        //given
        final Long id = 1L;
        final ReservationRQDTO request = mock(ReservationRQDTO.class);
        final ReservationControllerIDTO idto = mock(ReservationControllerIDTO.class);
        when(transformer.toEditReservationIDTO(id, request)).thenReturn(idto);
        final ReservationODTO odto = mock(ReservationODTO.class);
        when(manager.editReservation(idto)).thenReturn(odto);
        final ReservationRSDTO response = mock(ReservationRSDTO.class);
        when(transformer.toReservationRSDTO(odto)).thenReturn(response);

        //when
        final ReservationRSDTO actual = controller.editReservation(id, request);

        //then
        final InOrder inOrder = inOrder(transformer, manager);
        inOrder.verify(transformer).toEditReservationIDTO(id, request);
        inOrder.verify(manager).editReservation(idto);
        inOrder.verify(transformer).toReservationRSDTO(odto);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldGetReservesByDate(){
        //given
        final String date = Instancio.of(String.class).create();
        final ReservationsByDateIDTO idto = mock(ReservationsByDateIDTO.class);
        when(transformer.toReservationByDateIDTO(date)).thenReturn(idto);
        final ReservationODTO odto = mock(ReservationODTO.class);
        when(manager.getReservationsByDate(idto)).thenReturn(Collections.singletonList(odto));
        final ReservationRSDTO response = mock(ReservationRSDTO.class);
        when(transformer.toReservationRSDTO(odto)).thenReturn(response);

        //when
        final List<ReservationRSDTO> actual = controller.getReservesByDate(date);

        //then
        final InOrder inOrder = inOrder(transformer, manager);
        inOrder.verify(transformer).toReservationByDateIDTO(date);
        inOrder.verify(manager).getReservationsByDate(idto);
        inOrder.verify(transformer).toReservationRSDTO(odto);
        inOrder.verifyNoMoreInteractions();
    }
}
