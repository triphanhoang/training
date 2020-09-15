package com.faber.airmgr.services.impl;

import com.faber.airmgr.data.entities.AirPortEntity;
import com.faber.airmgr.data.repositories.AirPortRepository;
import com.faber.airmgr.services.AirPortService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class AirPortServiceImpl implements AirPortService {

    private final AirPortRepository airPortRepository;

    public AirPortServiceImpl(AirPortRepository airPortRepository) {
        this.airPortRepository = airPortRepository;
    }

    @Override
    public List<AirPortEntity> findAll() {
        try {
            return this.airPortRepository.findAll();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}
