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
        System.out.printf("ID: %s | Name: %s | Price: $%.2f | In stock: %d",
                productId, name, price, quantityInStock);
    }

    void updateExtraDetails(Scanner input) {
    }
}
