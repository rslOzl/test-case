package com.example.enocatestchallegence.OrderManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "customers")
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String customerName;

    @Column(name = "age")
    Integer customerAge;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerjoin")
    List<Order> orderList = new ArrayList<>();


    public Customer() {
    }

    public void add(Order tempOrder) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        orderList.add(tempOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(customerName, customer.customerName) && Objects.equals(customerAge, customer.customerAge) && Objects.equals(isDeleted, customer.isDeleted) && Objects.equals(orderList, customer.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerAge, isDeleted, orderList);
    }

}

