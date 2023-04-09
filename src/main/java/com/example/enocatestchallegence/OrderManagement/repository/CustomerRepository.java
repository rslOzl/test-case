package com.example.enocatestchallegence.OrderManagement.repository;

import com.example.enocatestchallegence.OrderManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    List<Customer> findAllCustomersByCustomerAgeIs(Integer customerAge);

    @Query(value =
            "SELECT * " +
                    "FROM customers c " +
                    "WHERE c.age > ?1 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllCustomersByCustomerAgeGreaterThan(Integer customerAge);

    @Query(value =
            "SELECT * " +
                    "FROM customers c " +
                    "WHERE c.name LIKE %?1% AND c.age >= ?2 AND c.is_deleted = FALSE",
            nativeQuery = true)
    List<Customer> findAllByFilter(String name, Integer age);


    @Query(value =
            "SELECT     name\n" +
                    "FROM       customers\n" +
                    "LEFT JOIN  Orders ON Customers.id = Orders.id\n" +
                    "WHERE      Orders.id IS NULL;",
            nativeQuery = true)
    List<Customer> findOther();



}