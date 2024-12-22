package com.cersocode.view;

import com.cersocode.controller.ProductController;
import com.cersocode.model.Product;

import java.util.Scanner;

public class ProductView {
    private final ProductController controller;

    public ProductView(ProductController controller) {
        this.controller = controller;
    }
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Product\n2. Find Product\n3. Delete Product\n4. Find All\n5. Update Product\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
//                    System.out.println("Enter ID:");
//                    int id = scanner.nextInt();
//                    scanner.nextLine();

                    System.out.println("Enter Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Price:");
                    double price = scanner.nextDouble();
                    controller.createProduct(name, price);
                }
                case 2 -> {
                    System.out.println("Enter ID to find:");
                    int id = scanner.nextInt();
                    Product product = controller.getProduct(id);
                    System.out.println(product != null ? product : "Product not found!");
                }
                case 3 -> {
                    System.out.println("Enter ID to delete:");
                    int id = scanner.nextInt();
                    controller.deleteProduct(id);
                }
                case 4 ->{
                    System.out.println("All Products:");
                    controller.getAllProducts();
                }
                case 5 -> {
                    System.out.println("Enter ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter new Price:");
                    double price = scanner.nextDouble();
                    controller.updateProduct(id, name, price);
                }

                case 6 -> System.exit(0);
            }
        }
    }

}
