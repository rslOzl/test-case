package com.example.enocatestchallegence.OrderManagement.service;

import com.example.enocatestchallegence.OrderManagement.model.Order;
import com.example.enocatestchallegence.OrderManagement.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders() {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get orders!");
        }
    }
}