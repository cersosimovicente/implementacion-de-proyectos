package net.laboratorioderedes;

public class Product {
    private Long idProduct;
    private String name;
    private Double price;
    private static Long countProduct;

    public Product(Long idProduct, String name, Double price) {
        //this.idProduct = ++countProduct;  // En esta version no es necesario el contador, ya que la base de
                                            // datos tiene el campo idProduct en auto_increment
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}