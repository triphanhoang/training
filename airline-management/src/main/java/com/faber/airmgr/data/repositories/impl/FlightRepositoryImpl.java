package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.FlightEntity;
import com.faber.airmgr.data.repositories.AirPortRepository;
import com.faber.airmgr.data.repositories.FlightRepository;
import com.faber.airmgr.data.repositories.base.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepositoryImpl extends BaseRepositoryImpl<FlightEntity> implements FlightRepository {

    private final AirPortRepository airPortRepository;

    public FlightRepositoryImpl(HikariDataSource dataSource, AirPortRepository airPortRepository) {
        super(dataSource);
        this.airPortRepository = airPortRepository;
    }

    @Override
    public List<FlightEntity> filter(Long fromPort, Long toPort, Date departureDate, Date arrivalDate) throws SQLException {
        final String query = "SELECT * FROM flight AS f WHERE f.departure_port_id = ? AND f.arrival_port_id = ? AND DAY(f.departure_time) = DAY(?) AND (? IS NULL OR DAY(f.arrival_time) = DAY(?))";
        Connection connection = this.getConnection();
        List<FlightEntity> results = new ArrayList<>();
        try {
            PreparedStatement statement = createStatement(connection, query);

            statement.setLong(1, fromPort);
            statement.setLong(2, toPort);
            statement.setDate(3, convertUtilToSql(departureDate));
            statement.setDate(4, convertUtilToSql(arrivalDate));
            statement.setDate(5, convertUtilToSql(arrivalDate));

            ResultSet resultSet = executeQuery(statement);

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
    public List<FlightEntity> findAll() throws SQLException {
        final String query = "SELECT * FROM flight";
        Connection connection = this.getConnection();
        List<FlightEntity> results = new ArrayList<>();
        try {
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

    private Optional<FlightEntity> findById(Long id, Connection connection) throws SQLException {
        final String query = "SELECT * FROM flight WHERE id = ?";
        List<FlightEntity> results = new ArrayList<>();
        try {
            if (connection == null) {
                connection = getConnection();
            }

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

    @Override
    public Optional<FlightEntity> findById(Long id) throws SQLException {
        return findById(id, null);
    }

    @Override
    public FlightEntity save(FlightEntity entity) throws SQLException {
        final String query = "INSERT INTO flight VALUES(?, ?, ?, ?)";
        Connection connection = null;
        try {
            PreparedStatement statement = createStatement(connection, query);
            statement.setLong(1, entity.getDeparturePort().getId());
            statement.setLong(2, entity.getArrivalPort().getId());
            statement.setDate(3, convertUtilToSql(entity.getDepartureTime()));
            statement.setDate(4, convertUtilToSql(entity.getArrivalTime()));
            statement.setLong(4, entity.getPrice());
            entity.setId(new Long(statement.executeUpdate()));
        } catch (Exception ex) {
            connection.rollback();
        } finally {
            connection.close();
        }
        return entity;
    }

    @Override
    protected FlightEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return FlightEntity
                .builder()
                .id(resultSet.getLong(1))
                .departurePort(airPortRepository.findById(resultSet.getLong(2)).orElse(null))
                .arrivalPort(airPortRepository.findById(resultSet.getLong(3)).orElse(null))
                .departureTime(resultSet.getDate(4))
                .arrivalTime(resultSet.getDate(5))
                .price(resultSet.getLong(6))
                .build();
    }
}
