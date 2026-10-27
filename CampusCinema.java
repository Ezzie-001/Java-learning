import java.util.Random;
import java.util.Scanner;

public class CampusCinema {
    static final int ROWS = 3;
    static final int COLUMNS = 4;

    // false = available, true = reserved
    static boolean[][] seats = new boolean[ROWS][COLUMNS];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter your name: ");
        String name = input.nextLine().trim();

        while (name.isEmpty()) {
            System.out.print("Name is required. Enter your name: ");
            name = input.nextLine().trim();
        }

        System.out.print("Enter your email: ");
        String email = input.nextLine().trim();

        if (!email.contains("@")) {
            System.out.println("Email must contain @. Continuing without an email.");
            email = "";
        }

        System.out.print("Enter your age: ");
        int age = Integer.parseInt(input.nextLine());

        System.out.print("Choose ticket type (standard, student, vip): ");
        String ticketType = input.nextLine().toLowerCase();

        double basePrice;
        switch (ticketType) {
            case "standard" -> basePrice = 10.00;
            case "student" -> basePrice = 7.50;
            case "vip" -> basePrice = 15.00;
            default -> basePrice = 0.00;
        }

        if (basePrice == 0.00) {
            System.out.println("That ticket type is not available.");
            input.close();
            return;
        }

        double discountRate = 0.0;
        if (age < 13) {
            discountRate = 0.50;
        } else if (ticketType.equals("student")) {
            discountRate = 0.10;
        }

        printSeatMap();

        int row;
        int column;
        while (true) {
            System.out.print("Choose a row (1-" + ROWS + "): ");
            row = Integer.parseInt(input.nextLine()) - 1;

            System.out.print("Choose a seat (1-" + COLUMNS + "): ");
            column = Integer.parseInt(input.nextLine()) - 1;

            boolean validPosition = row >= 0 && row < ROWS
                    && column >= 0 && column < COLUMNS;

            if (!validPosition) {
                System.out.println("That seat is outside the seating area.");
            } else if (seats[row][column]) {
                System.out.println("That seat is taken. Choose another.");
            } else {
                seats[row][column] = true;
                break;
            }
        }

        String emailDisplay = "Not provided";
        if (!email.isEmpty()) {
            int atPosition = email.indexOf("@");
            String username = email.substring(0, atPosition);
            String domain = email.substring(atPosition + 1);
            emailDisplay = username + " at " + domain;
        }

        int bookingNumber = random.nextInt(10000, 100000);
        Ticket ticket = new Ticket(bookingNumber, name, email, ticketType,
                age, row, column, basePrice, discountRate);

        double total = Math.round(ticket.calculatePrice() * 100.0) / 100.0;

        System.out.println("\n======= CINEMA RECEIPT =======");
        System.out.printf("Booking No. : %05d%n", ticket.bookingNumber);
        System.out.printf("Customer    : %-15s%n", ticket.customerName);
        System.out.printf("Email       : %s%n", emailDisplay);
        System.out.printf("Ticket      : %s%n", ticket.ticketType);
        System.out.printf("Age         : %d%n", ticket.age);
        System.out.printf("Seat        : Row %d, Seat %d%n", ticket.row + 1, ticket.column + 1);
        System.out.printf("Base price  : $%.2f%n", ticket.basePrice);
        System.out.printf("Discount    : %.0f%%%n", ticket.discountRate * 100);
        System.out.printf("TOTAL       : $%,.2f%n", total);
        System.out.println("==============================");

        printSeatMap();
        input.close();
    }

    static void printSeatMap() {
        System.out.println("\nSeat map (O = available, X = taken):");
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                String marker = seats[row][column] ? "X" : "O";
                System.out.print(marker + " ");
            }
            System.out.println();
        }
    }

    private static class Ticket {
        private final int bookingNumber;
        private final String customerName;
        @SuppressWarnings("unused")
        private final String email;
        private final String ticketType;
        private final int age;
        private final int row;
        private final int column;
        private final double basePrice;
        private final double discountRate;

        private Ticket(int bookingNumber, String customerName, String email,
                       String ticketType, int age, int row, int column,
                       double basePrice, double discountRate) {
            this.bookingNumber = bookingNumber;
            this.customerName = customerName;
            this.email = email;
            this.ticketType = ticketType;
            this.age = age;
            this.row = row;
            this.column = column;
            this.basePrice = basePrice;
            this.discountRate = discountRate;
        }

        private double calculatePrice() {
            return basePrice * (1.0 - discountRate);
        }
    }
}
