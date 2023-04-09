package com.example.enocatestchallegence.OrderManagement.service;

import com.example.enocatestchallegence.OrderManagement.dto.CustomerInDTO;
import com.example.enocatestchallegence.OrderManagement.model.Customer;
import com.example.enocatestchallegence.OrderManagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomers() {
        try {
            return customerRepository.findAll()
                    .stream()
                    .filter(customer -> !customer.getIsDeleted())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get all the customers!");
        }
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Customer customer = getCustomer(id);

        if (customer.getIsDeleted())
            return null;
        return customer;
    }

    @Override
    public Customer insertCustomer(CustomerInDTO inDTO) {
        Customer customer = new Customer();

        if (inDTO.getName() != null)
            customer.setCustomerName(inDTO.getName());

        if (inDTO.getAge() != null)
            customer.setCustomerAge(inDTO.getAge());

        return saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Integer id, CustomerInDTO inDTO) {

        Customer customer = getCustomer(id);
        if (customer == null)
            throw new RuntimeException("Customer not found with id = " + id);

        boolean updated = false;

        if (inDTO.getName() != null && !inDTO.getName().equals(customer.getCustomerName())) {
            customer.setCustomerName(inDTO.getName());
            updated = true;
        }

        if (inDTO.getAge() != null && !inDTO.getAge().equals(customer.getCustomerAge())) {
            customer.setCustomerAge(inDTO.getAge());
            updated = true;
        }

        if (updated)
            saveCustomer(customer);
    }


    @Override
    public void deleteCustomer(Integer id) {

        Customer customer =getCustomer(id);
        if (customer == null)
            throw new RuntimeException("Customer not found with id = " + id);

        customer.setIsDeleted(true);
        saveCustomer(customer);
    }

    @Override
    public List<Customer> getCustomersByFilter(String name, Integer age) {

        if (age == null)
            age = 0;

        if (name == null)
            name = "";

        try {
            return customerRepository.findAllByFilter(name, age);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get customers with filter!");
        }
    }



    @Override
    public List<Customer> findOther() {
        try {
            return customerRepository.findOther();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get the customers!");
        }
    }

    private Customer getCustomer(Integer id) {
        Optional<Customer> optionalCustomer;
        try {
            optionalCustomer = customerRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to get customer with id = " + id);
        }

        return optionalCustomer.orElse(null);
    }


    private Customer saveCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to save customer!");
        }
    }
}