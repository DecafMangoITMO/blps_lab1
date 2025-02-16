package com.alwxdecaf.blps_lab.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alwxdecaf.blps_lab.order.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findALlByCustomerId(long customerId);

}