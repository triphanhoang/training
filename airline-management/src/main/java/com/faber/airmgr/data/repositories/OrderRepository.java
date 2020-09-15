package com.faber.airmgr.data.repositories;

import com.faber.airmgr.data.entities.OrderEntity;
import com.faber.airmgr.data.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository {
    OrderEntity save(OrderEntity entity);

    List<OrderEntity> findAll();
}
