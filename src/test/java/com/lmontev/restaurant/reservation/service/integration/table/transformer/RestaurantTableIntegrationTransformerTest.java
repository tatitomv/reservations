package com.lmontev.restaurant.reservation.service.integration.table.transformer;

import com.lmontev.restaurant.reservation.entities.table.RestaurantTableMO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.input.RestaurantTableIDTO;
import com.lmontev.restaurant.reservation.service.integration.table.dto.output.RestaurantTableODTO;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.impl.RestaurantTableIntegrationTransformerImpl;
import com.lmontev.restaurant.reservation.service.integration.table.transformer.impl.mapper.RestaurantTableIntegrationMapper;
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
public class RestaurantTableIntegrationTransformerTest {

    @Spy
    private RestaurantTableIntegrationMapper mapper = Mappers.getMapper(RestaurantTableIntegrationMapper.class);

    @InjectMocks
    private RestaurantTableIntegrationTransformerImpl transformer;

    @Test
    public void shouldTransformToRestaurantTableODTO(){
        //given
        final RestaurantTableMO restaurantTableMO = Instancio.of(RestaurantTableMO.class).create();

        //when
        final RestaurantTableODTO actual = transformer.toRestaurantTableODTO(restaurantTableMO);

        //then
        verify(mapper).toRestaurantTableODTO(restaurantTableMO);
        assertThat(actual, notNullValue());
        assertThat(restaurantTableMO.getId(), is(actual.getId()));
        assertThat(restaurantTableMO.getMaxDiners(), is(actual.getMaxDiners()));
    }

    @Test
    public void shouldTransformToRestaurantTableMO(){
        //given
        final RestaurantTableIDTO restaurantTableIDTO = Instancio.of(RestaurantTableIDTO.class).create();

        //when
        final RestaurantTableMO actual = transformer.toRestaurantTableMO(restaurantTableIDTO);

        //then
        verify(mapper).toRestaurantTableMO(restaurantTableIDTO);
        assertThat(actual, notNullValue());
        assertThat(restaurantTableIDTO.getId(), is(actual.getId()));
        assertThat(restaurantTableIDTO.getMaxDiners(), is(actual.getMaxDiners()));
    }

    @Test
    public void shouldTransformToRestaurantTableIDTO(){
        //given
        final RestaurantTableMO restaurantTableMO = Instancio.of(RestaurantTableMO.class).create();

        //when
        final RestaurantTableIDTO actual = transformer.toRestaurantTableIDTO(restaurantTableMO);

        //then
        verify(mapper).toRestaurantTableIDTO(restaurantTableMO);
        assertThat(actual, notNullValue());
        assertThat(restaurantTableMO.getId(), is(actual.getId()));
        assertThat(restaurantTableMO.getMaxDiners(), is(actual.getMaxDiners()));
    }
}
