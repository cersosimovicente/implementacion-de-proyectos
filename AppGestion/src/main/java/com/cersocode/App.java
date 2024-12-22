package com.cersocode;

import com.cersocode.controller.ProductController;
import com.cersocode.model.DataSourceStrategy;
import com.cersocode.repository.FileDataSource;
import com.cersocode.repository.InMemoryDataSource;
import com.cersocode.repository.MysqlDataSource;
import com.cersocode.view.ProductView;
import com.sun.tools.javac.Main;

import java.io.IOException;
import java.util.Properties;


public class App
{
    public static void main( String[] args ) throws IOException {
//        DataSourceStrategy dataSource = new FileDataSource();

//        Product product = new Product(1, "Laptop", 999.99);
//        dataSource.save(product);
//        Product product2 = new Product(2, "Smartphone", 499.99);
//        dataSource.save(product2);
//        System.out.println( dataSource.findById(1));
//
//        //dataSource.delete(1);
//        dataSource.update(new Product(2, "Smartphone", 699.99));
//        dataSource.findAll().forEach(System.out::println);

        Properties properties = new Properties();
        properties.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));

        String source = properties.getProperty("data.source");
        DataSourceStrategy dataSource = switch (source) {
            case "FileDataSource" -> new FileDataSource();
            case "MysqlDataSource" -> new MysqlDataSource();
            default -> new InMemoryDataSource();
        };

        ProductController controller = new ProductController(dataSource);
        ProductView view = new ProductView(controller);
        view.showMenu();
    }


}
