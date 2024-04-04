package net.laboratorioderedes;

public class Main {
    public static void main(String[] args) {

        //Creamos varios objetos Producto
        Product product1 = new Product("Camisa", 50.00);
        Product product2 = new Product("Pantalon", 100.00);
        Product product3 = new Product("Corbata", 30.00);


        //Creamos un objeto Order
        Order order = new Order();

        //Agregamos los productos a la orden
        order.addProduct(product1);
        order.addProduct(product2);
        order.addProduct(product3);

        //Imprimimos  la orden
        order.showOrders();

        //Creamos la orden2
        Order order2 = new Order();
        //Creamos nuevos productos
        Product product4 = new Product("zapatos", 500.00);
        Product product5 = new Product("Blusa", 100.00);

        //Agregamos productos a la orden 2
        order2.addProduct(product4);
        order2.addProduct(product5);
        order2.addProduct(product3);
        order2.addProduct(product1);

        //Imprimimos la orden 2
        order2.showOrders();
    }
}