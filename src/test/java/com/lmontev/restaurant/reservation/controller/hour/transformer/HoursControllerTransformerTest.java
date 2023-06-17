package com.lmontev.restaurant.reservation.controller.hour.transformer;

import com.lmontev.restaurant.reservation.api.hours.dto.request.HourRQDTO;
import com.lmontev.restaurant.reservation.api.hours.dto.response.HourRSDTO;
import com.lmontev.restaurant.reservation.controller.hours.transformer.impl.HoursControllerTransformerImpl;
import com.lmontev.restaurant.reservation.controller.hours.transformer.impl.mapper.HoursControllerMapper;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.input.HourManagerIDTO;
import com.lmontev.restaurant.reservation.service.manager.hours.dto.output.HourManagerODTO;
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
public class HoursControllerTransformerTest {

    @Spy
    private HoursControllerMapper mapper = Mappers.getMapper(HoursControllerMapper.class);

    @InjectMocks
    private HoursControllerTransformerImpl transformer;

    @Test
    public void shouldTransformToHourManagerIDTO(){
        //given
        final HourRQDTO request = Instancio.of(HourRQDTO.class).create();

        //when
        final HourManagerIDTO actual = transformer.toHourManagerIDTO(request);

        //then
        verify(mapper).toHourManagerIDTO(request);
        assertThat(request.getHour(), is(actual.getHour()));
    }

    @Test
    public void shouldTransformToourRSDTO(){
        //given
        final HourManagerODTO odto = Instancio.of(HourManagerODTO.class).create();

        //when
        final HourRSDTO actual = transformer.toHourRSDTO(odto);

        //then
        verify(mapper).toHourRSDTO(odto);
        assertThat(odto.getHour(), is(actual.getHour()));
    }
}
