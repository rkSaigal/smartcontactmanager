package com.smart.repository;

import com.smart.entities.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyOrderRepository extends JpaRepository<MyOrder,Long> {

    public MyOrder findByOrderId(String orderId);
}
