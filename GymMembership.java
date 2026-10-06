import java.util.Random;
import java.util.Scanner;

/*
 * WORKED EXAMPLE: FitZone Gym Membership Desk
 *
 * A new member walks up to the front desk to sign up. The program:
 *   1. Asks for the member's details and won't accept bad answers
 *                                                  -> Scanner, while, do-while, the nextLine() trap
 *   2. Splits their email into username and domain -> if / else, contains(), indexOf(), substring()
 *   3. Sets the monthly price by plan              -> enhanced switch
 *   4. Gives a discount for signing up longer      -> if / else if / else
 *   5. Gives an extra discount by student / age    -> nested if
 *   6. Works out the totals                        -> Math.max(), Math.round(), ternary operator
 *   7. Gives a member number and a lucky draw      -> Random
 *   8. Prints a membership receipt                 -> printf (specifiers, precision, flags, width)
 *
 * SAMPLE RUN (member number and lucky draw are random, so yours will differ):
 *      Enter your name:
 *      Name can't be empty.
 *      Enter your name: Tolu
 *      Enter your email: tolu.ade@gmail.com
 *      Enter your age: 17
 *      How many months? (1-24): 30
 *      How many months? (1-24): 12
 *      Which plan? (basic / standard / premium): Premium
 *      Are you a student? (true/false): true
 */
public class GymMembership {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // ---------- 1. VARIABLES + SCANNER + LOOPS ----------
        String name = "";          // starts empty so the while loop runs at least once
        String email;
        String plan;
        int age;
        int months;
        boolean isStudent;
        double basePrice;          // the price from the menu - never changes after the switch
        double price;              // the price after discounts - this one changes

        // WHILE: keep asking until they type something
        while (name.isEmpty()) {
            System.out.print("Enter your name: ");
            name = input.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name can't be empty.");
            } else{
                System.out.println("Welcome to Royal gym " + name);
            }
        }

        System.out.print("Enter your email: ");
        email = input.nextLine();

        // WHILE: ask once, then keep asking while the answer is impossible
        System.out.print("Enter your age: ");
        age = input.nextInt();
        while (age < 0 || age > 120) {
            System.out.println("That's not a real age.");
            System.out.print("Enter your age: ");
            age = input.nextInt();
        }

        // DO-WHILE: always asks at least once, repeats while the number is out of range
        do {
            System.out.print("How many months? (1-24): ");
            months = input.nextInt();
        } while (months < 1 || months > 24);

        input.nextLine();          // THE TRAP: nextInt() left the Enter key behind.
                                   // Without this line, the plan question below gets
                                   // skipped and plan becomes "" (empty).

        System.out.print("Which plan? (basic / standard / premium): ");
        plan = input.nextLine();

        System.out.print("Are you a student? (true/false): ");
        isStudent = input.nextBoolean();   // no trap here: nextBoolean() skips the leftover Enter

        System.out.println("Welcome to FitZone, " + name + "!");

        // ---------- 2. IF / ELSE + SUBSTRING ----------
        // "tolu.ade@gmail.com"
        //  indexOf("@") = 8  ->  substring(0, 8) = "tolu.ade"   (stops BEFORE index 8)
        //                       substring(9)    = "gmail.com"  (from index 9 to the end)
        String username;
        if (email.contains("@")) {
            username = email.substring(0, email.indexOf("@"));
            String domain = email.substring(email.indexOf("@") + 1);
            System.out.println("Login username: " + username + " (we'll email you at " + domain + ")");
        }
        else {
            username = name;       // no valid email, so fall back to their name
            System.out.println("No valid email, your login username is your name.");
        }

        // ---------- 3. ENHANCED SWITCH ----------
        // Each case must ASSIGN the price (basePrice = ...), not just print it.
        // The cases are lowercase because we compare against plan.toLowerCase().
        switch (plan.toLowerCase()) {
            case "basic" -> basePrice = 15.00;
            case "standard" -> basePrice = 25.00;
            case "premium" -> basePrice = 40.00;
            default -> basePrice = 0;      // 0 means "not a real plan" - we check this in step 8
        }

        price = basePrice;                 // start from the menu price, then apply discounts to price

        // ---------- 4. IF / ELSE IF / ELSE ----------
        // To take X% off, multiply by (1 - X%):  20% off -> * 0.80
        // Use >= so the boundary numbers (12, 6, 3) are included.
        // Each else if only runs if the ones above were false, so no "&& months < 12" needed.
        if (months >= 12) {
            System.out.println("12+ months: 20% off");
            price *= 0.80;
        }
        else if (months >= 6) {
            System.out.println("6-11 months: 10% off");
            price *= 0.90;
        }
        else if (months >= 3) {
            System.out.println("3-5 months: 5% off");
            price *= 0.95;
        }
        else {
            System.out.println("Under 3 months: no length discount.");
        }

        // ---------- 5. NESTED IF ----------
        if (isStudent) {
            if (age < 18) {
                System.out.println("Student under 18: extra 15% off");
                price *= 0.85;
            }
            else {
                System.out.println("Student: extra 10% off");
                price *= 0.90;
            }
        }
        else {
            if (age >= 60) {
                System.out.println("Senior: extra 25% off");
                price *= 0.75;
            }
            else {
                System.out.println("No extra discount.");
            }
        }

        // ---------- 6. MATH + TERNARY ----------
        // Math.max picks the bigger number: the gym never charges less than 10.00 a month.
        // (Only matters for basic plans with lots of discounts - try basic, 12 months, age 70.)
        // We skip this for a fake plan, otherwise "yoga" would cost 10.00 instead of 0.
        if (basePrice != 0) {
            price = Math.max(price, 10.00);
        }

        double total = price * months;
        double saved = (basePrice - price) * months;   // saving on the WHOLE membership, not one month

        // 1 reward point for every 5.00 spent, rounded to the nearest whole point
        long points = Math.round(total / 5);

        // TERNARY: variable = (condition) ? valueIfTrue : valueIfFalse;
        String memberType = isStudent ? "Student" : "Standard";
        String lockerIncluded = (months >= 6) ? "Yes" : "No";

        // ---------- 7. RANDOM ----------
        int memberNumber = random.nextInt(1, 1000);    // 1 to 999 (the 1000 is NOT included)
        int luckyDraw = random.nextInt(1, 11);         // 1 to 10

        // ---------- 8. PRINTF RECEIPT ----------
        if (basePrice == 0) {
            System.out.println(plan + " is not one of our plans. No membership created.");
        }
        else {
            System.out.println();
            System.out.println("======= FITZONE MEMBERSHIP =======");
            System.out.printf("Member No.   : %05d\n", memberNumber);         // zero padding -> 00128
            System.out.printf("Name         : %-15s|\n", name);              // left-justified, width 15
            System.out.printf("Username     : %s\n", username);
            System.out.printf("Plan         : %s (%c)\n", plan, plan.charAt(0)); // %c needs ONE char
            System.out.printf("Member type  : %s\n", memberType);
            System.out.printf("Age          : %d\n", age);
            System.out.printf("Student      : %b\n", isStudent);
            System.out.printf("Locker       : %s\n", lockerIncluded);
            System.out.printf("Months       : %8d\n", months);               // right-justified, width 8
            System.out.printf("Menu price   : $%8.2f\n", basePrice);         // %f needs a decimal number
            System.out.printf("Your price   : $%8.2f\n", price);
            System.out.printf("You saved    : $%(8.2f\n", -saved);           // negative shown in ( )
            System.out.printf("TOTAL        : $%,8.2f\n", total);            // comma grouping
            System.out.printf("Points earned: %8d\n", points);
            System.out.println("==================================");

            if (luckyDraw == 7) {
                System.out.println("You drew a 7 - you win a free FitZone gym bag!");
            }
            else {
                System.out.println("Lucky draw: you drew " + luckyDraw + ". Better luck next time!");
            }
        }

        input.close();
    }
}
