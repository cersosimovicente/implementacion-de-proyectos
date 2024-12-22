package com.cersocode.model;

import java.util.List;

public interface DataSourceStrategy {
    void save(Product product);
    Product findById(int id);
    List<Product> findAll();
    void update(Product product);
    void delete(int id);
}
