package com.cersocode.repository;

import com.cersocode.model.DataSourceStrategy;
import com.cersocode.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.lines;

public class FileDataSource implements DataSourceStrategy {
    private final Path filePath = Paths.get("products.txt");

    public FileDataSource() {
        // Initialize the file path or perform any necessary setup
        try {
            if(!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        }catch (IOException e) {
            throw new RuntimeException("Error creating file: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(Product product) {
        try{
            Files.writeString(filePath, product.getId() + "," + product.getName() +","+ product.getPrice() + "\n",
                    StandardOpenOption.APPEND);
        }catch(IOException e){
            throw new RuntimeException("Error saving product: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findById(int id) {
        return findAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll() {
        try{
        return lines(filePath)
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Product(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                })
                .collect(Collectors.toList());
        }catch(IOException e){
            throw new RuntimeException("Error reading products: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        List<String> updatedLines = findAll().stream()
                .map(p -> p.getId() == product.getId() ? product : p)
                .map(p -> p.getId() + ", " + p.getName() + ", " + p.getPrice())
                .collect(Collectors.toList());

        try {
            Files.write(filePath, updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }

    }

    @Override
    public void delete(int id) {
        List<String> updatedLines = findAll().stream()
                .filter(p -> p.getId() != id)
                .map(p -> p.getId() + ", " + p.getName() + ", " + p.getPrice())
                .collect(Collectors.toList());

        try {
            Files.write(filePath, updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting product: " + e.getMessage(), e);
        }

    }
}
