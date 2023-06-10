package com.lmontev.restaurant.reservation.service.manager.hours.impl;

import com.lmontev.restaurant.reservation.service.integration.hours.HoursIntegration;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.HoursManagerTranformer;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HoursManagerTest {

    @Mock
    private HoursIntegration integration;

    @Mock
    private HoursManagerTranformer tranformer;

    @InjectMocks
    private HoursManagerImpl manager;

    @Test
    public void shouldGetAllAvailableHours(){
        //given
        final List<LocalTime> localTime = Instancio.ofList(LocalTime.class).size(10).create();
        when(integration.allHoursAvailables()).thenReturn(localTime);

        //when
        final List<LocalTime> actual = manager.getAllAvailableHours();

        //then
        final InOrder inOrder = inOrder(integration);
        inOrder.verify(integration).allHoursAvailables();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAddHour(){
        //given
        final HourManagerIDTO hourManagerIDTO = mock(HourManagerIDTO.class);
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        final HourIntegrationIDTO hourIntegrationIDTO = mock(HourIntegrationIDTO.class);
        final HourManagerODTO hourIntegrationODTO = mock(HourManagerODTO.class);
        when(hourManagerIDTO.getHour()).thenReturn(hour);
        when(integration.addHour(hourManagerIDTO.getHour())).thenReturn(hourIntegrationIDTO);
        when(tranformer.toODTO(hourIntegrationIDTO)).thenReturn(hourIntegrationODTO);

        //when
        final HourManagerODTO actual = manager.addHour(hourManagerIDTO);

        //then
        final InOrder inOrder = inOrder(integration,tranformer);
        inOrder.verify(integration).addHour(hourManagerIDTO.getHour());
        inOrder.verify(tranformer).toODTO(hourIntegrationIDTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldDeleteHour(){
        //given
        final HourManagerIDTO hourManagerIDTO = mock(HourManagerIDTO.class);
        final LocalTime hour = Instancio.of(LocalTime.class).create();
        when(hourManagerIDTO.getHour()).thenReturn(hour);

        //when
        manager.deleteHour(hourManagerIDTO);

        //then
        final InOrder inOrder = inOrder(integration);
        inOrder.verify(integration).deleteHour(hourManagerIDTO.getHour());
        inOrder.verifyNoMoreInteractions();
    }
}
