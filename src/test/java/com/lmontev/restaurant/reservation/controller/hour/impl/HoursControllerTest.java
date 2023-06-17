package com.lmontev.restaurant.reservation.controller.hour.impl;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.controller.hours.impl.HoursControllerImpl;
import com.lmontev.restaurant.reservation.controller.hours.transformer.HoursControllerTransformer;
import com.lmontev.restaurant.reservation.controller.hours.transformer.impl.HoursControllerTransformerImpl;
import com.lmontev.restaurant.reservation.service.manager.hours.HoursManager;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HoursControllerTest {

    @Mock
    private HoursControllerTransformer transformer;

    @Mock
    private HoursManager manager;

    @InjectMocks
    private HoursControllerImpl controller;

    @Test
    public void shouldGetAllAvailableHours(){
        //given
        when(manager.getAllAvailableHours()).thenReturn(Collections.singletonList(LocalTime.now()));

        //when
        controller.getAllAvailableHours();

        //then
        final InOrder inOrder = inOrder(manager);
        inOrder.verify(manager).getAllAvailableHours();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAddHour(){
        //given
        final HourRQDTO request = mock(HourRQDTO.class);
        final HourManagerIDTO idto = mock(HourManagerIDTO.class);
        when(transformer.toHourManagerIDTO(request)).thenReturn(idto);
        final HourManagerODTO odto = mock(HourManagerODTO.class);
        when(manager.addHour(idto)).thenReturn(odto);
        final HourRSDTO response = mock(HourRSDTO.class);
        when(transformer.toHourRSDTO(odto)).thenReturn(response);

        //when
        final HourRSDTO actual = controller.addHour(request);

        //then
        final InOrder inOrder = inOrder(manager,transformer);
        inOrder.verify(transformer).toHourManagerIDTO(request);
        inOrder.verify(manager).addHour(idto);
        inOrder.verify(transformer).toHourRSDTO(odto);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldDeleteHour(){
        //given
        final HourRQDTO request = mock(HourRQDTO.class);
        final HourManagerIDTO idto = mock(HourManagerIDTO.class);
        when(transformer.toHourManagerIDTO(request)).thenReturn(idto);

        //when
        controller.deleteHour(request);

        //then
        final InOrder inOrder = inOrder(manager, transformer);
        inOrder.verify(transformer).toHourManagerIDTO(request);
        inOrder.verify(manager).deleteHour(idto);
        inOrder.verifyNoMoreInteractions();
    }
}
