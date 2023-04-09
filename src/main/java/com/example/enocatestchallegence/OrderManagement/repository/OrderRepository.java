package com.example.enocatestchallegence.OrderManagement.repository;

import com.example.enocatestchallegence.OrderManagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}