package com.lmontev.restaurant.reservation.service.manager.hours.transformer;

import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.impl.HoursManagerTransformerImpl;
import com.lmontev.restaurant.reservation.service.manager.hours.transformer.impl.mapper.HoursManagerMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HoursManagerTransformerTest {

    @Spy
    private HoursManagerMapper mapper = Mappers.getMapper(HoursManagerMapper.class);

    @InjectMocks
    private HoursManagerTransformerImpl transformer;

    @Test
    public void shouldTransformToODTO(){
        //given
        final HourIntegrationIDTO hourIntegrationIDTO = Instancio.of(HourIntegrationIDTO.class).create();

        //when
        final HourManagerODTO actual = transformer.toODTO(hourIntegrationIDTO);

        //then
        verify(mapper).toODTO(hourIntegrationIDTO);
        assertThat(actual, notNullValue());
        assertThat(hourIntegrationIDTO.getHour(), is(actual.getHour()));
    }
}
