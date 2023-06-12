package com.lmontev.restaurant.reservation.service.integration.hours.transformer;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.impl.HoursIntegrationTransformerImpl;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.impl.mapper.HoursIntegrationMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HoursIntegrationTransformerTest {

    @Spy
    private HoursIntegrationMapper mapper = Mappers.getMapper(HoursIntegrationMapper.class);

    @InjectMocks
    private HoursIntegrationTransformerImpl transformer;

    @Test
    public void shouldTransformToIDTO(){
        //given
        final HoursMO hoursMO = Instancio.of(HoursMO.class).create();

        //when
        final HourIntegrationIDTO actual = transformer.toIDTO(hoursMO);

        //then
        verify(mapper).toIdto(hoursMO);
        assertThat(hoursMO.getHour(), is(actual.getHour()));
        assertThat(hoursMO.getId(), is(actual.getId()));
    }

    @Test
    public void shouldTransformToHoursMO(){
        //given
        final LocalTime time = Instancio.of(LocalTime.class).create();

        //when
        final HoursMO actual = transformer.toHoursMO(time);

        //then
        assertThat(time, is(actual.getHour()));
    }
}
