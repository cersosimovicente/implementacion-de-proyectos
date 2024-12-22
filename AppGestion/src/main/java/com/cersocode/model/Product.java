package com.cersocode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.id = IdGenerator.generateId();
        this.name = name;
        this.price = price;
    }
}
