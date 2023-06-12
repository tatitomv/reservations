package com.lmontev.restaurant.reservation.service.integration.reservation.impl;

import com.lmontev.restaurant.reservation.controller.reservation.dto.input.ReservationControllerIDTO;
import com.lmontev.restaurant.reservation.entities.reservation.ReservationMO;
import com.lmontev.restaurant.reservation.exception.handler.error.NotFoundCustomException;
import com.lmontev.restaurant.reservation.repositories.reservation.ReservationRepository;
import com.lmontev.restaurant.reservation.service.integration.reservation.dto.input.ReservationIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.reservation.transformer.ReservationIntegrationTransformer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationIntegrationTest {

    @Mock
    private ReservationRepository repository;

    @Mock
    private ReservationIntegrationTransformer transformer;

    @InjectMocks
    private ReservationIntegrationImpl integration;

    @Test
    public void shouldFindReservationByDate(){
        //given
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final ReservationMO reservationMO = mock(ReservationMO.class);
        when(repository.findByDate(date))
                .thenReturn(Collections.singletonList(reservationMO));
        final ReservationIntegrationIDTO reservationIDTO = mock(ReservationIntegrationIDTO.class);
        when(transformer.toIDTO(reservationMO)).thenReturn(reservationIDTO);

        //when
        final List<ReservationIntegrationIDTO> actual = integration.findReservationsByDate(date);

        //then
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(repository).findByDate(date);
        inOrder.verify(transformer).toIDTO(reservationMO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldFindReservationByDateAndDiners(){
        //given
        final LocalDate date = Instancio.of(LocalDate.class).create();
        final Long diners = Instancio.of(Long.class).create();
        final ReservationMO reservationMO1 = mock(ReservationMO.class);
        final ReservationMO reservationMO2 = mock(ReservationMO.class);
        final List<ReservationMO> listReservationMO = Arrays.asList(reservationMO2, reservationMO1);
        when(repository.findByDateAndTableMOMaxDinersGreaterThanEqual(date, diners))
                .thenReturn(listReservationMO);
        final ReservationIntegrationIDTO reservationIDTO1 = mock(ReservationIntegrationIDTO.class);
        when(reservationIDTO1.getMaxDiners()).thenReturn(3L);
        when(transformer.toIDTO(reservationMO1)).thenReturn(reservationIDTO1);
        final ReservationIntegrationIDTO reservationIDTO2 = mock(ReservationIntegrationIDTO.class);
        when(reservationIDTO2.getMaxDiners()).thenReturn(1L);
        when(transformer.toIDTO(reservationMO2)).thenReturn(reservationIDTO2);

        //when
        final List<ReservationIntegrationIDTO> actual = integration.findReservationsByDateAndDiners(date, diners);

        //then
        assertThat(1L, is(actual.get(0).getMaxDiners()));
        assertThat(3L, is(actual.get(1).getMaxDiners()));
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(repository).findByDateAndTableMOMaxDinersGreaterThanEqual(date,diners);
        inOrder.verify(transformer).toIDTO(reservationMO2);
        inOrder.verify(transformer).toIDTO(reservationMO1);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldReserve(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        final ReservationMO reservationMO = mock(ReservationMO.class);
        when(transformer.toReservationMO(reservationControllerIDTO)).thenReturn(reservationMO);
        final ReservationMO reservationMOComplete = mock(ReservationMO.class);
        when(repository.save(reservationMO)).thenReturn(reservationMOComplete);
        final ReservationIntegrationIDTO reservationIDTO = mock(ReservationIntegrationIDTO.class);
        when(transformer.toIDTO(reservationMOComplete)).thenReturn(reservationIDTO);

        //when
        final ReservationIntegrationIDTO reserve = integration.reserve(reservationControllerIDTO);

        //then
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(transformer).toReservationMO(reservationControllerIDTO);
        inOrder.verify(repository).save(reservationMO);
        inOrder.verify(transformer).toIDTO(reservationMOComplete);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotFindReservationById(){
        //given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundCustomException.class, () -> {
            integration.findReservationById(1L);
        });
    }

    @Test
    public void shouldFindReservationById(){
        //given
        final ReservationMO reservationMO = mock(ReservationMO.class);
        when(repository.findById(1L)).thenReturn(Optional.of(reservationMO));
        final ReservationIntegrationIDTO reservationIDTO = mock(ReservationIntegrationIDTO.class);
        when(transformer.toIDTO(reservationMO)).thenReturn(reservationIDTO);

        //when
        final ReservationIntegrationIDTO actual = integration.findReservationById(1L);

        //then
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(repository).findById(1L);
        inOrder.verify(transformer).toIDTO(reservationMO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotEditReservation(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        when(repository.findById(reservationControllerIDTO.getId())).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundCustomException.class, () -> {
            integration.editReservation(reservationControllerIDTO);
        });
    }

    @Test
    public void shouldEditReservation(){
        //given
        final ReservationControllerIDTO reservationControllerIDTO = mock(ReservationControllerIDTO.class);
        final ReservationMO reservationMO = mock(ReservationMO.class);
        when(repository.findById(reservationControllerIDTO.getId())).thenReturn(Optional.of(reservationMO));
        final ReservationMO reservationMOForEdit = mock(ReservationMO.class);
        when(transformer.toReservationMO(reservationControllerIDTO)).thenReturn(reservationMOForEdit);
        final ReservationMO reservationMOEdited = mock(ReservationMO.class);
        when(repository.save(reservationMOForEdit)).thenReturn(reservationMOEdited);
        final ReservationIntegrationIDTO reservationIDTO = mock(ReservationIntegrationIDTO.class);
        when(transformer.toIDTO(reservationMOEdited)).thenReturn(reservationIDTO);

        //when
        ReservationIntegrationIDTO actual = integration.editReservation(reservationControllerIDTO);

        //then
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(repository).findById(reservationControllerIDTO.getId());
        inOrder.verify(transformer).toReservationMO(reservationControllerIDTO);
        inOrder.verify(repository).save(reservationMOForEdit);
        inOrder.verify(transformer).toIDTO(reservationMOEdited);
        inOrder.verifyNoMoreInteractions();
    }
}
