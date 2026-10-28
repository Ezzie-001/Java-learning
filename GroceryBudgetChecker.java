import java.util.Scanner;

public class GroceryBudgetChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Your grocery budget (₦): ");
        double budget = input.nextDouble();
        while (budget < 0) {
            System.out.print("Enter zero or a positive budget: ₦");
            budget = input.nextDouble();
        }

        System.out.print("Number of different items: ");
        int count = input.nextInt();
        input.nextLine(); 
        while (count <= 0) {
            System.out.print("Enter at least one item: ");
            count = input.nextInt();
            input.nextLine();
        }
        double subtotal = 0;

        System.out.println("\n=== Shopping Items ===");
        for (int i = 0; i < count; i++) {
            System.out.print("\nItem " + (i + 1) + " name: ");
            String name = input.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("Name cannot be empty. Enter the item name: ");
                name = input.nextLine().trim();
            }
            System.out.print("Price per item (₦): ");
            double price = input.nextDouble();
            while (price <= 0) {
                System.out.print("Enter a price greater than zero: ₦");
                price = input.nextDouble();
            }
            System.out.print("Quantity: ");
            int quantity = input.nextInt();
            input.nextLine();
            while (quantity <= 0) {
                System.out.print("Enter a quantity greater than zero: ");
                quantity = input.nextInt();
                input.nextLine();
            }
            double lineTotal = price * quantity;
            subtotal += lineTotal;
            System.out.printf("%s x %d = ₦%.2f%n", name, quantity, lineTotal);
        }

        System.out.print("Discount percentage (0 if none): ");
        double discountRate = input.nextDouble();
        while (discountRate < 0 || discountRate > 100) {
            System.out.print("Enter a discount from 0 to 100: ");
            discountRate = input.nextDouble();
        }
        System.out.print("Tax percentage (0 if none): ");
        double taxRate = input.nextDouble();
        while (taxRate < 0 || taxRate > 100) {
            System.out.print("Enter a tax rate from 0 to 100: ");
            taxRate = input.nextDouble();
        }
        double discount = subtotal * discountRate / 100;
        double tax = (subtotal - discount) * taxRate / 100;
        double total = subtotal - discount + tax;

        System.out.println("\n=== Shopping Receipt ===");
        System.out.printf("Subtotal:          ₦%.2f%n", subtotal);
        System.out.printf("Discount:         -₦%.2f%n", discount);
        System.out.printf("Tax:               ₦%.2f%n", tax);
        System.out.printf("Total:             ₦%.2f%n", total);

        if (total <= budget)
            System.out.printf("Within budget by:  ₦%.2f%n", budget - total);
        else
            System.out.printf("Over budget by:    ₦%.2f%n", total - budget);
        input.close();
    }
}
