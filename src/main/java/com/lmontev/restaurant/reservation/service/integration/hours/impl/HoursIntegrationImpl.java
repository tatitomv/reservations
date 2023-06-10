package com.lmontev.restaurant.reservation.service.integration.hours.impl;

import com.lmontev.restaurant.reservation.entities.hours.HoursMO;
import com.lmontev.restaurant.reservation.repositories.hours.HoursRepository;
import com.lmontev.restaurant.reservation.service.integration.hours.HoursIntegration;
import com.lmontev.restaurant.reservation.service.integration.hours.dto.input.HourIntegrationIDTO;
import com.lmontev.restaurant.reservation.service.integration.hours.transformer.HoursIntegrationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HoursIntegrationImpl implements HoursIntegration {

    @Autowired
    private HoursIntegrationTransformer transformer;

    @Autowired
    private HoursRepository repository;

    @Override
    public List<LocalTime> allHoursAvailables() {
        return repository.findAll()
                .stream()
                .map(HoursMO::getHour)
                .collect(Collectors.toList());
    }

    @Override
    public HourIntegrationIDTO addHour(LocalTime hour) {
        final List<HoursMO> hours = repository.findByHour(hour);
        if(!hours.isEmpty()){
            throw new RuntimeException("Ya esta creado ese horario");
        }
        final HoursMO hoursMO = repository.save(transformer.toHoursMO(hour));
        return transformer.toIDTO(hoursMO);
    }

    @Override
    public void deleteHour(LocalTime hour) {
        List<HoursMO> hours = repository.findByHour(hour);
        if(hours.isEmpty()){
            throw new RuntimeException("No existe ese horario");
        }
        hours.forEach(hourMO -> repository.delete(hourMO));
    }
}
