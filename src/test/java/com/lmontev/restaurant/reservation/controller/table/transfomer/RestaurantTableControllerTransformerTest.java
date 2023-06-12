package com.lmontev.restaurant.reservation.controller.table.transfomer;

import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRQDTO;
import com.lmontev.restaurant.reservation.api.table.dto.RestaurantTableRSDTO;
import com.lmontev.restaurant.reservation.controller.table.transformer.impl.RestaurantTableControllerTransformerImpl;
import com.lmontev.restaurant.reservation.controller.table.transformer.impl.mapper.RestaurantTableControllerMapper;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RestaurantTableControllerTransformerTest {

    @Spy
    private RestaurantTableControllerMapper mapper = Mappers.getMapper(RestaurantTableControllerMapper.class);

    @InjectMocks
    private RestaurantTableControllerTransformerImpl transformer;

    @Test
    public void shouldTransformToRSDTO(){
        //given
        final RestaurantTableODTO restaurantTableODTO = Instancio.of(RestaurantTableODTO.class).create();

        //when
        final RestaurantTableRSDTO actual = transformer.toRSDTO(restaurantTableODTO);

        //then
        verify(mapper).toRSDTO(restaurantTableODTO);
        assertThat(restaurantTableODTO.getId(), is(actual.getId()));
        assertThat(restaurantTableODTO.getMaxDiners(), is(actual.getMaxDiners()));
    }

    @Test
    public void shouldTransformToIDTO(){
        //given
        final RestaurantTableRQDTO restaurantTableRQDTO = Instancio.of(RestaurantTableRQDTO.class).create();

        //when
        final RestaurantTableIDTO actual = transformer.toIDTO(restaurantTableRQDTO);

        //then
        verify(mapper).toIDTO(restaurantTableRQDTO);
        assertThat(restaurantTableRQDTO.getId(), is(actual.getId()));
        assertThat(restaurantTableRQDTO.getMaxDiners(), is(actual.getMaxDiners()));
    }
}
