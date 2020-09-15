package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.AirPortEntity;
import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.repositories.AirPortRepository;
import com.faber.airmgr.data.repositories.FlightRepository;
import com.faber.airmgr.models.AddFlightModel;
import com.faber.airmgr.models.FlightFilterModel;
import com.faber.airmgr.services.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AirPortRepository airPortRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirPortRepository airPortRepository) {
        this.flightRepository = flightRepository;
        this.airPortRepository = airPortRepository;
    }

    @Override
    public List<FlightEntity> filter(FlightFilterModel model) {
        try {
            return flightRepository.filter(model.getFromAirPort(), model.getToAirPort(), model.getDepartureDate(), model.getArrivalDate());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }

    @Override
    public List<FlightEntity> findAll() {

        try {
            return flightRepository.findAll();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public void add(AddFlightModel model) {
        try {
            AirPortEntity arrivalPort = airPortRepository.findById(model.getArrivalPort()).orElseThrow(() -> new RuntimeException("ArrivalPort notfound"));
            AirPortEntity departurePort = airPortRepository.findById(model.getDeparturePort()).orElseThrow(() -> new RuntimeException("DeparturePort notfound"));

            flightRepository.save(FlightEntity
                    .builder()
                    .arrivalPort(arrivalPort)
                    .departurePort(departurePort)
                    .price(model.getPrice())
                    .arrivalTime(model.getArrivalTime())
                    .departureTime(model.getDepartureTime())
                    .build());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

    }
}
