
import java.util.Scanner;

public class FoodProduct extends Product {

    String expiryDate;

    FoodProduct(String productId, String name, double price,
                int quantityInStock, String expiryDate) {

        super(productId, name, price, quantityInStock);
        this.expiryDate = expiryDate;
    }

    @Override
    void printDetails() {
        super.printDetails();
        System.out.println("Expiry date: " + expiryDate);
    }

    @Override
    void updateExtraDetails(Scanner input) {
        System.out.print("New expiry date: ");
        expiryDate = input.nextLine();

        while (expiryDate.isEmpty()) {
            System.out.print("Enter an expiry date: ");
            expiryDate = input.nextLine();
        }
    }
}
