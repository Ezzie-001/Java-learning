import java.util.Random;
import java.util.Scanner;

/*
 * CAPSTONE PROBLEM: Campus Bookshop
 *
 * Build a small shop program that can:
 *   1. Show books and the number of copies in stock.
 *   2. Sell a book to a customer and print a receipt.
 *   3. Search completed orders by customer name.
 *   4. Show how many copies of each book were sold on each of three days.
 *
 * This solution brings together Scanner, variables, if/else, switch, loops,
 * methods, strings, arrays, a 2D array, objects, overloaded constructors,
 * overloaded methods, Random, Math.round, and printf.
 */
public class CampusBookshop {
    static final Scanner input = new Scanner(System.in);
    static final Random random = new Random();

    static final String[] DAYS = {"Monday", "Tuesday", "Wednesday"};

    // An array of objects: each position holds one Product object.
    static final Product[] inventory = {
        new Product("Things Fall Apart", 12.50, 8),
        new Product("Purple Hibiscus", 14.00, 6),
        new Product("The Alchemist", 10.00), // Uses the overloaded constructor (default stock: 10)
        new Product("Born a Crime", 16.50, 5)
    };

    // sales[book number][day number] stores copies sold.
    static final int[][] sales = new int[inventory.length][DAYS.length];

    // Keep completed Order objects in an array with room for 30 orders.
    static final Order[] orders = new Order[30];
    static int orderCount = 0;

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Choose an option: ", 0, 4);

            switch (choice) {
                case 1 -> showInventory();
                case 2 -> placeOrder();
                case 3 -> searchOrders();
                case 4 -> printSalesReport();
                case 0 -> System.out.println("Thanks for visiting the Campus Bookshop!");
                default -> System.out.println("Choose a number from 0 to 4.");
            }
        } while (choice != 0);

        input.close();
    }

    static void printMenu() {
        System.out.println("\n======= CAMPUS BOOKSHOP =======");
        System.out.println("1) Show books");
        System.out.println("2) Buy a book");
        System.out.println("3) Search orders by customer");
        System.out.println("4) Sales report");
        System.out.println("0) Quit");
    }

    static int readInt(String prompt, int minimum, int maximum) {
        int number;
        do {
            System.out.print(prompt);
            number = input.nextInt();
            input.nextLine(); // Consume the Enter key after nextInt().

            if (number < minimum || number > maximum) {
                System.out.println("Enter a number from " + minimum + " to " + maximum + ".");
            }
        } while (number < minimum || number > maximum);

        return number;
    }

    static void showInventory() {
        System.out.println("\n--- Books in stock ---");
        System.out.printf("%-4s %-22s %10s %8s%n", "No.", "Title", "Price", "Copies");

        for (int i = 0; i < inventory.length; i++) {
            inventory[i].printRow(i + 1);
        }
    }

    static void placeOrder() {
        if (orderCount == orders.length) {
            System.out.println("The order list is full.");
            return;
        }

        System.out.print("Customer name: ");
        String customerName = input.nextLine().trim();
        while (customerName.isEmpty()) {
            System.out.print("Name is required. Enter the customer name: ");
            customerName = input.nextLine().trim();
        }

        showInventory();
        int productNumber = readInt("Choose a book number: ", 1, inventory.length);
        int productIndex = productNumber - 1;
        Product product = inventory[productIndex];

        if (product.getStock() == 0) {
            System.out.println("Sorry, that book is out of stock.");
            return;
        }

        int quantity = readInt("How many copies? ", 1, product.getStock());
        int dayNumber = readInt("Which sales day? (1=Mon, 2=Tue, 3=Wed): ", 1, DAYS.length);
        boolean isStudent = readYesNo("Is the customer a student? (yes/no): ");

        int orderNumber = random.nextInt(90000) + 10000;
        Order order = new Order(orderNumber, customerName, product, quantity,
                isStudent, dayNumber - 1);

        product.sell(quantity);
        sales[productIndex][dayNumber - 1] += quantity;
        orders[orderCount] = order;
        orderCount++;

        order.printReceipt();
    }

    static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String answer = input.nextLine().trim();

            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Please enter yes or no.");
        }
    }

    static void searchOrders() {
        if (orderCount == 0) {
            System.out.println("There are no orders yet.");
            return;
        }

        System.out.print("Enter the customer name to search for: ");
        String searchName = input.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < orderCount; i++) {
            if (orders[i].getCustomerName().equalsIgnoreCase(searchName)) {
                orders[i].printShortSummary();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No orders found for \"" + searchName + "\".");
        }
    }

    static void printSalesReport() {
        System.out.println("\n--- Copies sold by book and day ---");
        System.out.printf("%-22s", "Book");
        for (String day : DAYS) {
            System.out.printf("%10s", day.substring(0, 3));
        }
        System.out.printf("%10s%n", "Total");

        int bestBookIndex = 0;
        int bestBookTotal = -1;

        // Row totals: one row represents one book.
        for (int book = 0; book < sales.length; book++) {
            int rowTotal = 0;
            System.out.printf("%-22s", inventory[book].getName());

            for (int day = 0; day < sales[book].length; day++) {
                System.out.printf("%10d", sales[book][day]);
                rowTotal += sales[book][day];
            }

            System.out.printf("%10d%n", rowTotal);
            if (rowTotal > bestBookTotal) {
                bestBookTotal = rowTotal;
                bestBookIndex = book;
            }
        }

        // Column totals: one column represents one day.
        System.out.printf("%-22s", "Day total");
        for (int day = 0; day < DAYS.length; day++) {
            int columnTotal = 0;
            for (int book = 0; book < sales.length; book++) {
                columnTotal += sales[book][day];
            }
            System.out.printf("%10d", columnTotal);
        }
        System.out.println();

        if (bestBookTotal == 0) {
            System.out.println("No books have been sold yet.");
        } else {
            System.out.println("Best-selling book: " + inventory[bestBookIndex].getName()
                    + " (" + bestBookTotal + " copies)");
        }
    }

    // Overloaded methods: the two-argument version reuses the main calculation.
    static double calculateTotal(double unitPrice, int quantity) {
        return calculateTotal(unitPrice, quantity, false);
    }

    static double calculateTotal(double unitPrice, int quantity, boolean isStudent) {
        double total = unitPrice * quantity;

        if (quantity >= 10) {
            total *= 0.90; // 10% bulk discount
        } else if (quantity >= 5) {
            total *= 0.95; // 5% bulk discount
        }

        if (isStudent) {
            total *= 0.95; // Extra 5% student discount
        }

        return Math.round(total * 100.0) / 100.0;
    }
}

class Product {
    private final String name;
    private final double price;
    private int stock;

    Product(String name, double price) {
        this(name, price, 10); // The 2-argument constructor supplies a default stock count.
    }

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }

    void sell(int quantity) {
        stock -= quantity;
    }

    void printRow(int number) {
        System.out.printf("%-4d %-22s $%9.2f %8d%n", number, name, price, stock);
    }
}

class Order {
    private final int orderNumber;
    private final String customerName;
    private final Product product;
    private final int quantity;
    private final boolean student;
    private final int dayIndex;
    private final double subtotal;
    private final double total;

    Order(int orderNumber, String customerName, Product product,
            int quantity, boolean student, int dayIndex) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.student = student;
        this.dayIndex = dayIndex;
        this.subtotal = product.getPrice() * quantity;
        this.total = CampusBookshop.calculateTotal(product.getPrice(), quantity, student);
    }

    String getCustomerName() {
        return customerName;
    }

    void printReceipt() {
        double saved = subtotal - total;

        System.out.println("\n========= BOOKSHOP RECEIPT =========");
        System.out.printf("Order No.  : %05d%n", orderNumber);
        System.out.printf("Customer   : %s%n", customerName);
        System.out.printf("Book       : %s%n", product.getName());
        System.out.printf("Quantity   : %d%n", quantity);
        System.out.printf("Day        : %s%n", CampusBookshop.DAYS[dayIndex]);
        System.out.printf("Student    : %b%n", student);
        System.out.printf("Subtotal   : $%,.2f%n", subtotal);
        System.out.printf("You saved  : $%,.2f%n", saved);
        System.out.printf("TOTAL      : $%,.2f%n", total);
        System.out.println("====================================");
    }

    void printShortSummary() {
        System.out.printf("Order %05d | %-18s | %d x %-22s | $%,.2f | %s%n",
                orderNumber, customerName, quantity, product.getName(), total,
                CampusBookshop.DAYS[dayIndex]);
    }
}
