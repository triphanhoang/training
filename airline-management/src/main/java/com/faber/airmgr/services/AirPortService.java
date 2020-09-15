package com.faber.airmgr.services;

import com.faber.airmgr.data.entities.AirPortEntity;

import java.util.List;


public interface AirPortService {
    List<AirPortEntity> findAll();
}
