package com.example.enocatestchallegence.OrderManagement.service;

import com.example.enocatestchallegence.OrderManagement.dto.CustomerInDTO;
import com.example.enocatestchallegence.OrderManagement.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomerById(Integer id);

    Customer insertCustomer(CustomerInDTO dto);

    void updateCustomer(Integer id, CustomerInDTO customer);

    void deleteCustomer(Integer id);

    List<Customer> getCustomersByFilter(String name, Integer age);

    List<Customer> findOther();
}