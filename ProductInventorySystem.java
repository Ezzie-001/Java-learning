import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProductInventorySystem {
    static Scanner input = new Scanner(System.in);
    static Product[] products = new Product[100]; //like basically the array of obj, products[productCount] = new Product(id, name, price, quantity);); for the standard one i did
    static int productCount = 0;
    static double totalRevenue = 0;

    public static void main(String[] args) {
        loadInventory();
        boolean isRunning = true;

        while (isRunning) {
            showMenu();
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showProducts();
                case 3 -> searchProduct();
                case 4 -> updateProduct();
                case 5 -> makeSale();
                case 6 -> showReport();
                case 7 -> {
                    saveInventory();
                    isRunning = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 7.");
            }
        }
    }

    static void showMenu() {
        System.out.print("========================================");
        System.out.println("\n Product Inventory and Sales Management ");
        System.out.println("========================================");
        System.out.println("1. Add a product");
        System.out.println("2. View all products");
        System.out.println("3. Search by product ID");
        System.out.println("4. Update a product");
        System.out.println("5. Make a sale");
        System.out.println("6. Business report");
        System.out.println("7. Exit");
    }

    static void addProduct() {
        if (productCount == products.length) {
            System.out.println("The inventory is full.");
            return;
        }

        int type;
        do {
            System.out.println("Product type: 1. Standard  2. Electronic  3. Food");
            System.out.print("Choose a product type: ");
            type = input.nextInt();
            input.nextLine();
            if (type < 1 || type > 3) {
                System.out.println("Choose 1, 2, or 3.");
            }
        } while (type < 1 || type > 3);

        System.out.print("Product ID: ");
        String id = input.nextLine().trim();
        while (id.isEmpty()) {
            System.out.print("Product ID cannot be empty. Enter it again: ");
            id = input.nextLine().trim();
        }
        if (findProduct(id) != -1) {
            System.out.println("A product with that ID already exists.");
            return;
        }

        System.out.print("Product name: ");
        String name = input.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Product name cannot be empty. Enter it again: ");
            name = input.nextLine().trim();
        }

        System.out.print("Price: $");
        double price = input.nextDouble();
        while (price <= 0) {
            System.out.print("Enter a price greater than zero: $");
            price = input.nextDouble();
        }

        System.out.print("Quantity in stock: ");
        int quantity = input.nextInt();
        input.nextLine();
        while (quantity < 0) {
            System.out.print("Enter zero or a positive quantity: ");
            quantity = input.nextInt();
            input.nextLine();
        }

        if (type == 1) {
            products[productCount] = new Product(id, name, price, quantity);
        } else if (type == 2) {
            System.out.print("Warranty period in months: ");
            int warranty = input.nextInt();
            input.nextLine();
            while (warranty < 0) {
                System.out.print("Enter zero or a positive warranty period: ");
                warranty = input.nextInt();
                input.nextLine();
            }
            products[productCount] = new ElectronicProduct(id, name, price, quantity, warranty);
        } else {
            System.out.print("Expiry date (YYYY-MM-DD): ");
            String expiryDate = input.nextLine().trim();
            while (expiryDate.isEmpty()) {
                System.out.print("Expiry date cannot be empty. Enter it again: ");
                expiryDate = input.nextLine().trim();
            }
            products[productCount] = new FoodProduct(id, name, price, quantity, expiryDate);
        }

        productCount++;
        saveInventory();
        System.out.println("Product registered successfully.");
    }

    static void showProducts() {
        if (productCount == 0) {
            System.out.println("There are no registered products.");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            products[i].printDetails();
            System.out.println();
        } 
    }

    static void searchProduct() {
        System.out.print("Enter product ID: ");
        String id = input.nextLine().trim();
        int index = findProduct(id);
        if (index == -1) {
            System.out.println("Product not found.");
        } else {
            products[index].printDetails();
            System.out.println();
        }
    }

    static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        String id = input.nextLine().trim();
        int index = findProduct(id);
        if (index == -1) {
            System.out.println("Product not found.");
            return;
        }

        Product product = products[index];
        System.out.print("New name: ");
        product.name = input.nextLine().trim();
        while (product.name.isEmpty()) {
            System.out.print("Product name cannot be empty. Enter it again: ");
            product.name = input.nextLine().trim();
        }

        System.out.print("New price: $");
        product.price = input.nextDouble();
        while (product.price <= 0) {
            System.out.print("Enter a price greater than zero: $");
            product.price = input.nextDouble();
        }

        System.out.print("New quantity in stock: ");
        product.quantityInStock = input.nextInt();
        input.nextLine();
        while (product.quantityInStock < 0) {
            System.out.print("Enter zero or a positive quantity: ");
            product.quantityInStock = input.nextInt();
            input.nextLine();
        }

        product.updateExtraDetails(input);
        saveInventory();
        System.out.println("Product updated successfully.");
    }

    static void makeSale() {
        System.out.print("Enter product ID to sell: ");
        String id = input.nextLine().trim();
        int index = findProduct(id);
        if (index == -1) {
            System.out.println("Product not found. Sale was not recorded.");
            return;
        }

        Product product = products[index]; //basicaally saying we should get the product from the array position tht is in index.
        product.printDetails();
        System.out.println();
        System.out.print("Quantity to purchase: ");
        int quantity = input.nextInt();
        input.nextLine();
        while (quantity <= 0) {
            System.out.print("Enter a quantity greater than zero: ");
            quantity = input.nextInt();
            input.nextLine();
        }

        if (quantity > product.quantityInStock) {
            System.out.println("Insufficient stock. Sale was not recorded.");
            return;
        }

        double saleTotal = product.price * quantity;
        product.quantityInStock -= quantity;
        totalRevenue += saleTotal;
        saveInventory();
        System.out.printf("Sale successful. Total: $%.2f%n", saleTotal);
    }

    static void showReport() {
        int totalQuantity = 0;
        for (int i = 0; i < productCount; i++) {
            totalQuantity += products[i].quantityInStock;
        }

        System.out.println("Business Report ");
        System.out.println("Registered products: " + productCount);
        System.out.println("Total units in stock: " + totalQuantity);
        System.out.printf("Revenue from successful sales: $%.2f%n", totalRevenue);

        System.out.print("Show products with stock below: ");
        int threshold = input.nextInt();
        input.nextLine();
        while (threshold < 0) {
            System.out.print("Enter zero or a positive threshold: ");
            threshold = input.nextInt();
            input.nextLine();
        }

        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i].quantityInStock < threshold) {
                products[i].printDetails();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products are below that threshold.");
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

    // Load saved products and sales revenue when the program starts.
    static void loadInventory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\t", -1);
                if (parts[0].equals("REPORT") && parts.length == 2) {
                    totalRevenue = Double.parseDouble(parts[1]);
                } else if (parts[0].equals("PRODUCT") && parts.length == 7
                        && productCount < products.length) {
                    String id = parts[1];
                    String name = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int quantity = Integer.parseInt(parts[4]);
                    if (parts[5].equals("ELECTRONIC")) {
                        products[productCount++] = new ElectronicProduct(id, name, price,
                                quantity, Integer.parseInt(parts[6]));
                    } else if (parts[5].equals("FOOD")) {
                        products[productCount++] = new FoodProduct(id, name, price,
                                quantity, parts[6]);
                    } else if (parts[5].equals("STANDARD")) {
                        products[productCount++] = new Product(id, name, price, quantity);
                    }
                }
            }
            System.out.println("Inventory loaded from inventory.txt.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("No saved inventory found. Starting with an empty inventory.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load inventory: " + e.getMessage());
        }
    }

    // Save products and revenue so they are available the next time the program runs.
    static void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.txt"))) {
            writer.write("REPORT\t" + totalRevenue);
            writer.newLine();
            for (int i = 0; i < productCount; i++) {
                Product product = products[i];
                String type = "STANDARD";
                String extra = "";
                if (product instanceof ElectronicProduct) {
                    type = "ELECTRONIC";
                    extra = String.valueOf(((ElectronicProduct) product).warrantyMonths);
                } else if (product instanceof FoodProduct) {
                    type = "FOOD";
                    extra = ((FoodProduct) product).expiryDate;
                }
                writer.write("PRODUCT\t" + product.productId + "\t" + product.name + "\t"
                        + product.price + "\t" + product.quantityInStock + "\t" + type
                        + "\t" + extra);
                writer.newLine();
            }
            System.out.println("Inventory saved to inventory.txt.");
        } catch (IOException e) {
            System.out.println("Could not save inventory: " + e.getMessage());
        }
    }
}
