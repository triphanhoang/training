package com.faber.airmgr.data.repositories;

import com.faber.airmgr.data.entities.AirPortEntity;
import com.faber.airmgr.data.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
public interface AirPortRepository extends BaseRepository {
    List<AirPortEntity> findAll() throws SQLException;

    Optional<AirPortEntity> findById(Long id) throws SQLException;
}
