package net.laboratorioderedes;

import net.laboratorioderedes.dao.ProductDao;
import net.laboratorioderedes.util.DBConnection;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);//para leer el teclado equivale a scanf o cin en C y C++

        ProductDao productDao = new ProductDao(); //objeto de acceso a datos
        Order order = new Order(); //crea una nueva orden de compra

        while (true) { //menu de opciones
            System.out.println("1. Listar productos"); //equivale a printf o cout en C y C++
            System.out.println("2. Agregar producto a la orden de compra");
            System.out.println("3. Ver orden de compra");
            System.out.println("4. Salir");
            int opcion = Integer.parseInt(scanner.nextLine()); //lee la opcion del teclado


            if (opcion == 1) {
                List<Product> products = productDao.listAllProducts();//lista todos los productos en pantalla
                for (Product product : products) {
                    System.out.println(product);
                }
            } else if (opcion == 2) {

                while (true) {
                    System.out.println("Ingrese ID del producto o 0 para terminar");
                    int id = Integer.parseInt(scanner.nextLine());
                    if (id == 0) {
                        break;
                    }
                    order.addProduct(productDao.findById(id));
                }

            } else if (opcion == 3) {
                order.showOrders();
            }else if (opcion==4){
                break;
            }
        }


    }
}