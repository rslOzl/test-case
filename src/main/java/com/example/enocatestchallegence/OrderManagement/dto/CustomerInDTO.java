package com.example.enocatestchallegence.OrderManagement.dto;

import lombok.Data;

@Data
public class CustomerInDTO {

    String name;
    Integer age;

    public CustomerInDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}