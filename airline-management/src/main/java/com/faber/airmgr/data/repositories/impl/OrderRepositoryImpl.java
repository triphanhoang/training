package com.faber.airmgr.data.repositories.impl;

import com.faber.airmgr.data.entities.OrderEntity;
import com.faber.airmgr.data.repositories.OrderRepository;
import com.faber.airmgr.data.repositories.base.BaseRepository;
import com.faber.airmgr.data.repositories.base.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<OrderEntity> implements OrderRepository {
    public OrderRepositoryImpl(HikariDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected OrderEntity extractResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public OrderEntity save(OrderEntity entity) {
        return null;
    }

    @Override
    public List<OrderEntity> findAll() {
        return null;
    }
}
