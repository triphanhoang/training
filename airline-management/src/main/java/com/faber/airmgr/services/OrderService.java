package com.faber.airmgr.services;

import com.faber.airmgr.data.entities.OrderEntity;
import com.faber.airmgr.models.FlightOrderModel;

import java.util.List;

public interface OrderService {
    void doOrder(FlightOrderModel model);

    List<OrderEntity> findAll();
}
