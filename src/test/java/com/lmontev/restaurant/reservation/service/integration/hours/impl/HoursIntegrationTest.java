package com.lmontev.restaurant.reservation.service.integration.hours.impl;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.exception.handler.error.BadRequestCustomException;
import com.lmontev.restaurant.reservation.exception.handler.error.NotFoundCustomException;
import com.lmontev.restaurant.reservation.repositories.hours.HoursRepository;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.HoursIntegrationTransformer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HoursIntegrationTest {

    @Mock
    private HoursIntegrationTransformer transformer;

    @Mock
    private HoursRepository repository;

    @InjectMocks
    private HoursIntegrationImpl integration;

    @Test
    public void shouldFindAllAvailableHours(){
        //given
        final HoursMO hour = mock(HoursMO.class);
        final LocalTime time = Instancio.of(LocalTime.class).create();
        when(hour.getHour()).thenReturn(time);
        when(repository.findAll()).thenReturn(Collections.singletonList(hour));

        //when
        final List<LocalTime> actual = integration.allHoursAvailables();

        verify(repository).findAll();
    }

    @Test
    public void shouldAddAlreadyExistingHour(){
        //given
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        final HoursMO hourMO = mock(HoursMO.class);
        when(repository.findByHour(hour)).thenReturn(Collections.singletonList(hourMO));

        //when
        assertThrows(BadRequestCustomException.class, () -> {
            integration.addHour(hour);
        });
    }

    @Test
    public void shouldAddHour(){
        //given
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        when(repository.findByHour(hour)).thenReturn(Collections.emptyList());
        final HoursMO hourMO = mock(HoursMO.class);
        when(transformer.toHoursMO(hour)).thenReturn(hourMO);
        final HoursMO hourMOSaved = mock(HoursMO.class);
        when(repository.save(hourMO)).thenReturn(hourMOSaved);
        final HourIntegrationIDTO hourIDTO = mock(HourIntegrationIDTO.class);
        when(transformer.toIDTO(hourMOSaved)).thenReturn(hourIDTO);

        //when
        final HourIntegrationIDTO actual = integration.addHour(hour);

        //then
        final InOrder inOrder = inOrder(repository, transformer);
        inOrder.verify(repository).findByHour(hour);
        inOrder.verify(transformer).toHoursMO(hour);
        inOrder.verify(repository).save(hourMO);
        inOrder.verify(transformer).toIDTO(hourMOSaved);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotFindHourToDelete(){
        //given
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        when(repository.findByHour(hour)).thenReturn(Collections.emptyList());

        //when
        assertThrows(NotFoundCustomException.class, () -> {
            integration.deleteHour(hour);
        });
    }

    @Test
    public void shouldDeleteHour(){
        //given
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        final HoursMO hourMO = mock(HoursMO.class);
        when(repository.findByHour(hour)).thenReturn(Collections.singletonList(hourMO));

        //then
        integration.deleteHour(hour);

        final InOrder inOrder = inOrder(repository);
        inOrder.verify(repository).findByHour(hour);
        inOrder.verify(repository).delete(hourMO);
        inOrder.verifyNoMoreInteractions();
    }
}
