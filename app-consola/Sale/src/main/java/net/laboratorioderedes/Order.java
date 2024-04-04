package net.laboratorioderedes;

public class Order {
    private final int idOrder;
    private Product products[];
    private static int countOrders;
    private int countProducts;
    private static final int maxProducts = 10;

    public Order(){
        this.idOrder = ++countOrders;
        products = new Product[maxProducts];
    }
    public void addProduct(Product product){
        if(countProducts < maxProducts){
            products[countProducts] = product;
            countProducts++;
        }else {
            System.out.println("No se pueden agregar mas productos " + maxProducts);
        }
    }

    public double calculateTotal(){
        double total = 0;
        for(int i = 0; i < countProducts; i++){
            total += products[i].getPrice();
        }
        return total;
    }

    public void showOrders(){
        System.out.println("Orden #" + idOrder);
        System.out.println("Total de la orden # " + idOrder + ": $" + calculateTotal());
        System.out.println("Productos de la orden # " + idOrder);
        for(int i = 0; i < countProducts; i++){
            System.out.println(products[i]);
        }
    }

}
