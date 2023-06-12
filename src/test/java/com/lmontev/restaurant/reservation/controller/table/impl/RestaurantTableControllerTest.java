package com.lmontev.restaurant.reservation.controller.table.impl;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.controller.table.transformer.RestaurantTableControllerTransformer;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.table.RestaurantTableManager;
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
public class RestaurantTableControllerTest {

    @Mock
    private RestaurantTableControllerTransformer transformer;

    @Mock
    private RestaurantTableManager manager;

    @InjectMocks
    private RestaurantTableControllerImpl controller;

    @Test
    public void shouldGetTable(){
        //given
        final RestaurantTableODTO restaurantTableODTO = mock(RestaurantTableODTO.class);
        when(manager.findRestaurantTableById(1L)).thenReturn(restaurantTableODTO);
        final RestaurantTableRSDTO restaurantTableRSDTO = mock(RestaurantTableRSDTO.class);
        when(transformer.toRSDTO(restaurantTableODTO)).thenReturn(restaurantTableRSDTO);

        //when
        final RestaurantTableRSDTO actual = controller.getTable(1L);

        //then
        final InOrder inOrder = inOrder(manager,transformer);
        inOrder.verify(manager).findRestaurantTableById(1L);
        inOrder.verify(transformer).toRSDTO(restaurantTableODTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldGetAllTables(){
        //given
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(manager.findAllRestaurantTables()).thenReturn(Collections.singletonList(tableODTO));
        final RestaurantTableRSDTO tableRSDTO = mock(RestaurantTableRSDTO.class);
        when(transformer.toRSDTO(tableODTO)).thenReturn(tableRSDTO);

        //when
        final List<RestaurantTableRSDTO> actual = controller.getTables();

        //then
        final InOrder inOrder = inOrder(manager,transformer);
        inOrder.verify(manager).findAllRestaurantTables();
        inOrder.verify(transformer).toRSDTO(tableODTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAddTable(){
        //given
        final RestaurantTableRQDTO request = mock(RestaurantTableRQDTO.class);
        final RestaurantTableIDTO tableIDTO = mock(RestaurantTableIDTO.class);
        when(transformer.toIDTO(request)).thenReturn(tableIDTO);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(manager.addTable(tableIDTO)).thenReturn(tableODTO);
        final RestaurantTableRSDTO tableRSDTO = mock(RestaurantTableRSDTO.class);
        when(transformer.toRSDTO(tableODTO)).thenReturn(tableRSDTO);

        //when
        final RestaurantTableRSDTO actual = controller.addTable(request);

        //then
        final InOrder inOrder = inOrder(manager,transformer);
        inOrder.verify(transformer).toIDTO(request);
        inOrder.verify(manager).addTable(tableIDTO);
        inOrder.verify(transformer).toRSDTO(tableODTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldEditTable(){
        //given
        final RestaurantTableRQDTO request = mock(RestaurantTableRQDTO.class);
        final RestaurantTableIDTO tableIDTO = mock(RestaurantTableIDTO.class);
        when(transformer.toIDTO(request)).thenReturn(tableIDTO);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(manager.editTable(tableIDTO)).thenReturn(tableODTO);
        final RestaurantTableRSDTO tableRSDTO = mock(RestaurantTableRSDTO.class);
        when(transformer.toRSDTO(tableODTO)).thenReturn(tableRSDTO);

        //when
        final RestaurantTableRSDTO actual = controller.editTable(request);

        //then
        final InOrder inOrder = inOrder(manager,transformer);
        inOrder.verify(transformer).toIDTO(request);
        inOrder.verify(manager).editTable(tableIDTO);
        inOrder.verify(transformer).toRSDTO(tableODTO);
        inOrder.verifyNoMoreInteractions();
    }
}
