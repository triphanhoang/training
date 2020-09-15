package com.faber.airmgr.data.repositories;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends BaseRepository {
    List<FlightEntity> filter(Long fromPort, Long toPort, Date departureDate, Date arrivalDate) throws SQLException;

    List<FlightEntity> findAll() throws SQLException;

    Optional<FlightEntity> findById(Long id) throws SQLException;

    FlightEntity save(FlightEntity entity) throws SQLException;
}
