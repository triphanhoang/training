package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.AirPortEntity;
import com.faber.airmgr.data.repositories.AirPortRepository;
import com.faber.airmgr.data.repositories.base.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AirPortRepositoryImpl extends BaseRepositoryImpl<AirPortEntity> implements AirPortRepository {

    public AirPortRepositoryImpl(HikariDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected AirPortEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return AirPortEntity
                .builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .city(resultSet.getString(3))
                .build();
    }

    @Override
    public List<AirPortEntity> findAll() throws SQLException {
        Connection connection = this.getConnection();
        List<AirPortEntity> results = new ArrayList<>();
        try {
            final String query = "SELECT * FROM air_port";
            ResultSet resultSet = executeQuery(connection, query);
            while (resultSet.next()) {
                results.add(extractResultSet(resultSet));
            }

        } catch (Exception ex) {

        } finally {
            connection.close();
        }
        return results;
    }

    @Override
    public Optional<AirPortEntity> findById(Long id) throws SQLException {
        Connection connection = this.getConnection();
        try {
            final String query = "SELECT * FROM air_port WHERE id = ?";
            PreparedStatement statement = createStatement(connection, query);
            statement.setLong(1, id);
            ResultSet resultSet = executeQuery(statement);
            while (resultSet.next()) {
                return Optional.of(extractResultSet(resultSet));
            }

        } catch (Exception ex) {

        } finally {
            connection.close();
        }

        return Optional.empty();
    }
}
