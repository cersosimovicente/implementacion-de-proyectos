package com.cersocode.repository;

import com.cersocode.model.DataSourceStrategy;
import com.cersocode.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataSource implements DataSourceStrategy {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        return findAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void update(Product product) {
        Product productToUpdate = findById(product.getId());
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}