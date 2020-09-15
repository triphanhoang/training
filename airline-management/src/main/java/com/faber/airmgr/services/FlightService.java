package com.faber.airmgr.services;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.models.AddFlightModel;
import com.faber.airmgr.models.FlightFilterModel;

import java.util.List;

public interface FlightService {
    List<FlightEntity> filter(FlightFilterModel model);

    List<FlightEntity> findAll();

    void add(AddFlightModel model);
}
