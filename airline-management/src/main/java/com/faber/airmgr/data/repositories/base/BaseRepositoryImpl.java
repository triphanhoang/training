package com.faber.airmgr.data.repositories.base;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public abstract class BaseRepositoryImpl<TEntity> {

    private HikariDataSource dataSource;

    public BaseRepositoryImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }


    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    protected ResultSet executeQuery(Connection connection, String query) throws SQLException {
        log.info("Execute query: {}", query);
        return connection.prepareStatement(query).executeQuery();
    }

    protected PreparedStatement createStatement(Connection connection, String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    protected ResultSet executeQuery(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    protected void executeUpdate(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }

    protected java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    protected abstract TEntity extractResultSet(ResultSet resultSet) throws SQLException;
}
