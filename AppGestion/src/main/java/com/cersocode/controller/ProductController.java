package com.cersocode.controller;

import com.cersocode.model.DataSourceStrategy;
import com.cersocode.model.Product;

public class ProductController {
    private final DataSourceStrategy dataSource;


    public ProductController(DataSourceStrategy dataSource) {
        this.dataSource = dataSource;
    }

    public void saveProduct(Product product) {
        dataSource.save(product);
    }
    public void createProduct(String name, double price) {
        dataSource.save(new Product(name, price));
    }

    public void getAllProducts() {
        dataSource.findAll().forEach(System.out::println);
    }

    public Product getProduct(int id) {
        return dataSource.findById(id);
    }

    public void deleteProduct(int id) {
        dataSource.delete(id);
    }
    public void updateProduct(Product product) {
        dataSource.update(product);
    }

    public void updateProduct(int id, String name, double price) {
        dataSource.update(new Product(id, name, price));
    }
}
