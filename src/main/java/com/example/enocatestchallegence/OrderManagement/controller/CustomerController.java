package com.example.enocatestchallegence.OrderManagement.controller;


import com.example.enocatestchallegence.OrderManagement.dto.CustomerInDTO;
import com.example.enocatestchallegence.OrderManagement.model.Customer;
import com.example.enocatestchallegence.OrderManagement.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
@RequestMapping("/api/customers")
public class CustomerController {

    final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/by-filter")
    public ResponseEntity<?> getCustomersByFilter(@RequestBody CustomerInDTO inDTO) {

        List<Customer> customers = customerService.getCustomersByFilter(inDTO.getName(), inDTO.getAge());
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> getCustomerResponseEntity(@PathVariable Integer id) {

        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customer);
    }


    @PostMapping
    public ResponseEntity<?> insertCustomer(@RequestBody CustomerInDTO dto) {

        Customer customer = customerService.insertCustomer(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("CustomerId", customer.getId().toString())
                .build();
    }


    @PutMapping({"/{id}"})
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerInDTO customerInDTO) {

        customerService.updateCustomer(id, customerInDTO);

        return ResponseEntity.accepted().build();
    }


    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/find-others")
    public ResponseEntity<?> findOther() {

        List<Customer> customers = customerService.findOther();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }
}