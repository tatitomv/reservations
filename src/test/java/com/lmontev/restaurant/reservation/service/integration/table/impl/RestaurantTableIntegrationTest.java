package com.lmontev.restaurant.reservation.service.integration.table.impl;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.exception.handler.error.BadRequestCustomException;
import com.lmontev.restaurant.reservation.exception.handler.error.NotFoundCustomException;
import com.lmontev.restaurant.reservation.repositories.table.RestaurantTableRepository;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.RestaurantTableIntegrationTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantTableIntegrationTest {

    public static final Long TABLE_ID = 1L;
    @Mock
    private RestaurantTableRepository repository;

    @Mock
    private RestaurantTableIntegrationTransformer transformer;

    @InjectMocks
    private RestaurantTableIntegrationImpl integration;

    @Test
    public void shouldNotFindTableById(){
        //given
        when(repository.findById(TABLE_ID)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundCustomException.class, () -> {
            integration.findTableById(TABLE_ID);
        });
    }

    @Test
    public void shouldFindTableById(){
        //given
        final RestaurantTableMO table = mock(RestaurantTableMO.class);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(transformer.toRestaurantTableODTO(table)).thenReturn(tableODTO);
        when(repository.findById(TABLE_ID)).thenReturn(Optional.of(table));

        //when
        final RestaurantTableODTO actual = integration.findTableById(TABLE_ID);

        //then
        final InOrder inOrder = inOrder(transformer,repository);
        inOrder.verify(repository).findById(TABLE_ID);
        inOrder.verify(transformer).toRestaurantTableODTO(table);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldFindAllRestaurantTables(){
        //given
        final RestaurantTableMO table = mock(RestaurantTableMO.class);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(transformer.toRestaurantTableODTO(table)).thenReturn(tableODTO);
        when(repository.findAll()).thenReturn(Collections.singletonList(table));

        //when
        final List<RestaurantTableODTO> actual = integration.findAllRestaurantTable();

        //then
        final InOrder inOrder = inOrder(transformer,repository);
        inOrder.verify(repository).findAll();
        inOrder.verify(transformer).toRestaurantTableODTO(table);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotAddTable(){
        //given
        final RestaurantTableIDTO idto = mock(RestaurantTableIDTO.class);
        when(idto.getMaxDiners()).thenReturn(null);

        //when
        assertThrows(BadRequestCustomException.class, () -> {
            RestaurantTableODTO actual = integration.addTable(idto);
        });
    }

    @Test
    public void shouldAddTable(){
        //given
        final RestaurantTableIDTO idto = mock(RestaurantTableIDTO.class);
        when(idto.getMaxDiners()).thenReturn(3L);
        final RestaurantTableMO tableMO = mock(RestaurantTableMO.class);
        when(transformer.toRestaurantTableMO(idto)).thenReturn(tableMO);
        when(repository.save(tableMO)).thenReturn(tableMO);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(transformer.toRestaurantTableODTO(tableMO)).thenReturn(tableODTO);

        //when
        final RestaurantTableODTO actual = integration.addTable(idto);

        //then
        final InOrder inOrder = inOrder(transformer,repository);
        inOrder.verify(transformer).toRestaurantTableMO(idto);
        inOrder.verify(repository).save(tableMO);
        inOrder.verify(transformer).toRestaurantTableODTO(tableMO);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotEditTableBadRequest(){
        //given
        final RestaurantTableIDTO idto = mock(RestaurantTableIDTO.class);
        when(idto.getId()).thenReturn(null);

        //when
        assertThrows(BadRequestCustomException.class, () -> {
            integration.editTable(idto);
        });
    }

    @Test
    public void shouldNotFoundTableToEdit(){
        //given
        final RestaurantTableIDTO idto = mock(RestaurantTableIDTO.class);
        when(idto.getId()).thenReturn(TABLE_ID);
        when(repository.findById(TABLE_ID)).thenReturn(Optional.empty());

        //when
        assertThrows(NotFoundCustomException.class, () -> {
            integration.editTable(idto);
        });
    }

    @Test
    public void shouldEditTable(){
        //given
        final RestaurantTableIDTO idto = mock(RestaurantTableIDTO.class);
        when(idto.getId()).thenReturn(TABLE_ID);
        final RestaurantTableMO tableMO = mock(RestaurantTableMO.class);
        when(repository.findById(TABLE_ID)).thenReturn(Optional.of(tableMO));
        final RestaurantTableMO tableMOForEdit = mock(RestaurantTableMO.class);
        when(transformer.toRestaurantTableMO(idto)).thenReturn(tableMOForEdit);
        final RestaurantTableMO tableMOEdited = mock(RestaurantTableMO.class);
        when(repository.save(tableMOForEdit)).thenReturn(tableMOEdited);
        final RestaurantTableODTO tableODTO = mock(RestaurantTableODTO.class);
        when(transformer.toRestaurantTableODTO(tableMOEdited)).thenReturn(tableODTO);

        //when
        final RestaurantTableODTO actual = integration.editTable(idto);

        //then
        final InOrder inOrder = inOrder(transformer,repository);
        inOrder.verify(repository).findById(TABLE_ID);
        inOrder.verify(transformer).toRestaurantTableMO(idto);
        inOrder.verify(repository).save(tableMOForEdit);
        inOrder.verify(transformer).toRestaurantTableODTO(tableMOEdited);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldFindTablesByQuantity(){
        //given
        final Long quantity = 3L;
        final RestaurantTableMO restauranTableMO = mock(RestaurantTableMO.class);
        when(repository.findByMaxDinersGreaterThanEqualOrderByMaxDinersAsc(quantity))
                .thenReturn(Collections.singletonList(restauranTableMO));
        final RestaurantTableODTO restaurantTableODTO = mock(RestaurantTableODTO.class);
        when(transformer.toRestaurantTableODTO(restauranTableMO))
                .thenReturn(restaurantTableODTO);

        //when
        final List<RestaurantTableODTO> actual = integration.findTablesByQuantity(quantity);

        //then
        final InOrder inOrder = inOrder(transformer,repository);
        inOrder.verify(repository).findByMaxDinersGreaterThanEqualOrderByMaxDinersAsc(quantity);
        inOrder.verify(transformer).toRestaurantTableODTO(restauranTableMO);
        inOrder.verifyNoMoreInteractions();
    }
}
