import java.util.Scanner;

public class FoodProduct extends Product {
    String expiryDate;

    FoodProduct(String productId, String name, double price,
                int quantityInStock, String expiryDate) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Price: $%.2f | In stock: %d | Expiry date: %s",
                productId, name, price, quantityInStock, expiryDate);
    }

    void updateExtraDetails(Scanner input) {
        System.out.print("New expiry date (YYYY-MM-DD): ");
        expiryDate = input.nextLine().trim();
        while (expiryDate.isEmpty()) {
            System.out.print("Expiry date cannot be empty: ");
            expiryDate = input.nextLine().trim();
        }
    }
}
