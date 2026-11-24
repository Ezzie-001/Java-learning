
import java.util.Scanner;
import java.io.*;

public class ProductInventorySystem {

    static Scanner input = new Scanner(System.in);

    static Product[] products = new Product[100];

    static int productCount = 0;
    static double totalRevenue = 0;

    public static void main(String[] args) {

        loadInventory();

        int choice;

        do {
            showMenu();

            System.out.print("Choose an option: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    showProducts();
                    break;

                case 3:
                    searchProduct();
                    break;

                case 4:
                    updateProduct();
                    break;

                case 5:
                    makeSale();
                    break;

                case 6:
                    showReport();
                    break;

                case 7:
                    saveInventory();
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 7);
    }

    static void showMenu() {
        System.out.println("\nPRODUCT INVENTORY SYSTEM");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Make Sale");
        System.out.println("6. Business Report");
        System.out.println("7. Exit");
    }

    static void addProduct() {

        if (productCount == products.length) {
            System.out.println("Inventory is full.");
            return;
        }

        System.out.println("1. Standard");
        System.out.println("2. Electronic");
        System.out.println("3. Food");

        System.out.print("Choose product type: ");
        int type = input.nextInt();
        input.nextLine();

        if (type < 1 || type > 3) {
            System.out.println("Invalid product type.");
            return;
        }

        System.out.print("Product ID: ");
        String id = input.nextLine();

        if (findProduct(id) != -1) {
            System.out.println("Product already exists.");
            return;
        }

        System.out.print("Product name: ");
        String name = input.nextLine();

        System.out.print("Price: ");
        double price = input.nextDouble();

        System.out.print("Quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        if (price <= 0 || quantity < 0) {
            System.out.println("Invalid price or quantity.");
            return;
        }

        if (type == 1) {
            products[productCount] =
                    new Product(id, name, price, quantity);
        }

        else if (type == 2) {
            System.out.print("Warranty in months: ");
            int warranty = input.nextInt();
            input.nextLine();

            products[productCount] =
                    new ElectronicProduct(id, name, price, quantity, warranty);
        }

        else {
            System.out.print("Expiry date: ");
            String expiry = input.nextLine();

            products[productCount] =
                    new FoodProduct(id, name, price, quantity, expiry);
        }

        productCount++;

        saveInventory();

        System.out.println("Product added successfully.");
    }

    static void showProducts() {

        if (productCount == 0) {
            System.out.println("No products available.");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            products[i].printDetails();
            System.out.println();
        }
    }

    static void searchProduct() {

        System.out.print("Enter product ID: ");
        String id = input.nextLine();

        int index = findProduct(id);

        if (index == -1) {
            System.out.println("Product not found.");
        } else {
            products[index].printDetails();
        }
    }

    static void updateProduct() {

        System.out.print("Enter product ID: ");
        String id = input.nextLine();

        int index = findProduct(id);

        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        Product product = products[index];

        System.out.print("New name: ");
        product.name = input.nextLine();

        System.out.print("New price: ");
        product.price = input.nextDouble();

        System.out.print("New quantity: ");
        product.quantityInStock = input.nextInt();
        input.nextLine();

        product.updateExtraDetails(input);

        saveInventory();

        System.out.println("Product updated.");
    }

    static void makeSale() {

        System.out.print("Enter product ID: ");
        String id = input.nextLine();

        int index = findProduct(id);

        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        Product product = products[index];

        System.out.print("Quantity to purchase: ");
        int quantity = input.nextInt();
        input.nextLine();

        if (quantity <= 0 || quantity > product.quantityInStock) {
            System.out.println("Invalid quantity or insufficient stock.");
            return;
        }

        double total = product.price * quantity;

        product.quantityInStock -= quantity;
        totalRevenue += total;

        saveInventory();

        System.out.println("Sale successful.");
        System.out.println("Total: $" + total);
    }

    static void showReport() {

        int totalQuantity = 0;

        for (int i = 0; i < productCount; i++) {
            totalQuantity += products[i].quantityInStock;
        }

        System.out.println("\nBUSINESS REPORT");
        System.out.println("Registered products: " + productCount);
        System.out.println("Total stock: " + totalQuantity);
        System.out.println("Total revenue: $" + totalRevenue);

        System.out.print("Enter low stock threshold: ");
        int threshold = input.nextInt();
        input.nextLine();

        for (int i = 0; i < productCount; i++) {

            if (products[i].quantityInStock < threshold) {
                products[i].printDetails();
                System.out.println();
            }
        }
    }

    static int findProduct(String id) {

        for (int i = 0; i < productCount; i++) {

            if (products[i].productId.equalsIgnoreCase(id)) {
                return i;
            }
        }

        return -1;
    }

    static void loadInventory() {

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader("inventory.txt"));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\t", -1);

                if (parts[0].equals("REPORT")) {
                    totalRevenue = Double.parseDouble(parts[1]);
                }

                else if (parts[0].equals("PRODUCT")) {

                    String id = parts[1];
                    String name = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);

                    if (parts[5].equals("STANDARD")) {
                        products[productCount++] =
                                new Product(id, name, price, quantity);
                    }

                    else if (parts[5].equals("ELECTRONIC")) {
                        int warranty = Integer.parseInt(parts[6]);

                        products[productCount++] =
                                new ElectronicProduct(id, name, price, quantity, warranty);
                    }

                    else if (parts[5].equals("FOOD")) {
                        products[productCount++] =
                                new FoodProduct(id, name, price, quantity, parts[6]);
                    }
                }
            }

            reader.close();

            System.out.println("Inventory loaded.");

        } catch (IOException e) {
            System.out.println("No saved inventory found.");
        }
    }

    static void saveInventory() {

        try {
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter("inventory.txt"));

            writer.write("REPORT\t" + totalRevenue);
            writer.newLine();

            for (int i = 0; i < productCount; i++) {

                Product product = products[i];

                String type = "STANDARD";
                String extra = "";

                if (product instanceof ElectronicProduct) {
                    type = "ELECTRONIC";
                    extra = String.valueOf(
                            ((ElectronicProduct) product).warrantyMonths);
                }

                else if (product instanceof FoodProduct) {
                    type = "FOOD";
                    extra = ((FoodProduct) product).expiryDate;
                }

                writer.write("PRODUCT\t" + product.productId + "\t"
                        + product.name + "\t" + product.price + "\t"
                        + product.quantityInStock + "\t" + type + "\t" + extra);

                writer.newLine();
            }

            writer.close();

            System.out.println("Inventory saved.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }
}
