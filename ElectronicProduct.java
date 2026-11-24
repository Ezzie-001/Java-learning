
import java.util.Scanner;

public class ElectronicProduct extends Product {

    int warrantyMonths;

    ElectronicProduct(String productId, String name, double price,
                      int quantityInStock, int warrantyMonths) {

        super(productId, name, price, quantityInStock);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    void printDetails() {
        super.printDetails();
        System.out.println("Warranty: " + warrantyMonths + " months");
    }

    @Override
    void updateExtraDetails(Scanner input) {
        System.out.print("New warranty period: ");
        warrantyMonths = input.nextInt();
        input.nextLine();

        while (warrantyMonths < 0) {
            System.out.print("Enter zero or a positive number: ");
            warrantyMonths = input.nextInt();
            input.nextLine();
        }
    }
}
