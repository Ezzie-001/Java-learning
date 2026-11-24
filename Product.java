
import java.util.Scanner;

public class Product {

    String productId;
    String name;
    double price;
    int quantityInStock;

    Product() {
    }

    Product(String productId, String name, double price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    void printDetails() {
        System.out.println("ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Quantity: " + quantityInStock);
    }

    void updateExtraDetails(Scanner input) {
    }
}
