import java.util.Scanner;

/*
 * WORKED EXAMPLE: Cinema Ticket Booth
 *
 * A cinema sells tickets. The program:
 *   1. Asks for the customer's details           -> Scanner, variables
 *   2. Checks the name isn't empty                -> if / else
 *   3. Sets the base price by day of the week     -> enhanced switch
 *   4. Works out the age group                    -> if / else if / else
 *   5. Applies discounts                          -> nested if
 *   6. Prints a neat receipt                      -> printf (specifiers, precision, flags, width)
 */
public class CinemaTicket {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ---------- 1. VARIABLES + SCANNER ----------
        String name;
        String day;
        int age;
        int tickets;
        boolean isStudent;
        double basePrice;
        int ticketNumber = 42;   // pretend this comes from the system

        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Which day are you watching? ");
        day = input.nextLine();

        System.out.print("Enter your age: ");
        age = input.nextInt();

        System.out.print("Are you a student? (true/false): ");
        isStudent = input.nextBoolean();

        System.out.print("How many tickets? ");
        tickets = input.nextInt();

        // ---------- 2. IF / ELSE ----------
        if (name.isEmpty()) {
            name = "Guest";
            System.out.println("No name entered, we'll call you Guest.");
        }
        else {
            System.out.println("Welcome, " + name + "!");
        }

        // ---------- 3. ENHANCED SWITCH ----------
        // toLowerCase() lets "MONDAY", "Monday" and "monday" all match,
        // so the cases must be written in lowercase too.
        switch (day.toLowerCase()) {
            case "monday", "tuesday", "wednesday", "thursday" -> basePrice = 8.50;
            case "friday", "saturday", "sunday" -> basePrice = 12.00;
            default -> basePrice = 0;
        }

        // ---------- 4. IF / ELSE IF / ELSE ----------
        String ageGroup;
        if (age >= 65) {
            ageGroup = "Senior";
        }
        else if (age >= 18) {
            ageGroup = "Adult";
        }
        else if (age >= 13) {
            ageGroup = "Teen";
        }
        else {
            ageGroup = "Child";
        }

        // ---------- 5. NESTED IF ----------
        double price = basePrice;

        if (ageGroup.equals("Child")) {
            System.out.println("Children get 50% off!");
            price *= 0.5;
        }
        else {
            if (isStudent) {
                if (ageGroup.equals("Senior")) {
                    System.out.println("Student + Senior discount: 30% off");
                    price *= 0.7;
                }
                else {
                    System.out.println("Student discount: 10% off");
                    price *= 0.9;
                }
            }
            else {
                if (ageGroup.equals("Senior")) {
                    System.out.println("Senior discount: 20% off");
                    price *= 0.8;
                }
                else {
                    System.out.println("No discount applied.");
                }
            }
        }

        double total = price * tickets;

        // ---------- 6. PRINTF RECEIPT ----------
        if (basePrice == 0) {
            System.out.println(day + " is not a valid day. No tickets sold.");
        }
        else {
            System.out.println();
            System.out.println("========== RECEIPT ==========");
            System.out.printf("Ticket No.   : %05d\n", ticketNumber);          // zero padding
            System.out.printf("Customer     : %-15s|\n", name);               // left-justified width
            System.out.printf("Initial      : %c\n", name.charAt(0));          // charAt(0) = first letter
            System.out.printf("Age group    : %s (%d yrs)\n", ageGroup, age);
            System.out.printf("Student      : %b\n", isStudent);
            System.out.printf("Day          : %s\n", day);
            System.out.printf("Base price   : $%8.2f\n", basePrice);           // right-justified width
            System.out.printf("Your price   : $%8.2f\n", price);
            System.out.printf("Tickets      : %8d\n", tickets);
            System.out.printf("You saved    : $%(8.2f\n", price - basePrice);  // negative in ( )
            System.out.printf("TOTAL        : $%,8.2f\n", total);              // comma grouping
            System.out.println("=============================");
        }

        input.close();
    }
}
