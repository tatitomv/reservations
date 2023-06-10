package com.lmontev.restaurant.reservation.service.manager.table;

import com.lmontev.restaurant.reservation.service.integration.table.RestaurantTableIntegration;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.manager.table.impl.RestaurantTableManagerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantTableManagerTest {

    @Mock
    private RestaurantTableIntegration integration;

    @InjectMocks
    private RestaurantTableManagerImpl manager;

    @Test
    public void shouldFindRestaurantTable(){
        //given
        final RestaurantTableODTO restaurantTable = mock(RestaurantTableODTO.class);
        when(integration.findTableById(1l)).thenReturn(restaurantTable);

        //when
        final RestaurantTableODTO actual = manager.findRestaurantTableById(1l);

        //then
        assertThat(actual, notNullValue());
        final InOrder inOrder = Mockito.inOrder(integration);
        inOrder.verify(integration).findTableById(1l);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldFindAllTables(){
        //given
        final RestaurantTableODTO restaurantTable = mock(RestaurantTableODTO.class);
        when(integration.findAllRestaurantTable()).thenReturn(Collections.singletonList(restaurantTable));

        //when
        final List<RestaurantTableODTO> actual = manager.findAllRestaurantTables();

        //then
        final InOrder inOrder = Mockito.inOrder(integration);
        inOrder.verify(integration).findAllRestaurantTable();
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldAddNewTable(){
        //given
        final RestaurantTableIDTO restaurantTableIDTO = mock(RestaurantTableIDTO.class);
        final RestaurantTableODTO restaurantTableODTO = mock(RestaurantTableODTO.class);
        when(integration.addTable(restaurantTableIDTO)).thenReturn(restaurantTableODTO);

        //when
        manager.addTable(restaurantTableIDTO);

        //then
        final InOrder inOrder = Mockito.inOrder(integration);
        inOrder.verify(integration).addTable(restaurantTableIDTO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldEditTable(){
        //given
        final RestaurantTableIDTO restaurantTableIDTO = mock(RestaurantTableIDTO.class);
        final RestaurantTableODTO restaurantTableODTO = mock(RestaurantTableODTO.class);
        when(integration.editTable(restaurantTableIDTO)).thenReturn(restaurantTableODTO);

        //when
        manager.editTable(restaurantTableIDTO);

        //then
        final InOrder inOrder = Mockito.inOrder(integration);
        inOrder.verify(integration).editTable(restaurantTableIDTO);
        inOrder.verifyNoMoreInteractions();
    }
}
