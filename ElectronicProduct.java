import java.util.Scanner;

public class ElectronicProduct extends Product {
    int warrantyMonths;

    ElectronicProduct(String productId, String name, double price,
                      int quantityInStock, int warrantyMonths) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.warrantyMonths = warrantyMonths;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Price: $%.2f | In stock: %d | Warranty: %d months",
                productId, name, price, quantityInStock, warrantyMonths);
    }

    void updateExtraDetails(Scanner input) {
        System.out.print("New warranty period in months: ");
        warrantyMonths = input.nextInt();
        input.nextLine();
        while (warrantyMonths < 0) {
            System.out.print("Enter zero or a positive whole number: ");
            warrantyMonths = input.nextInt();
            input.nextLine();
        }
    }
}
