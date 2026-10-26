import java.util.Random;
import java.util.Scanner;

/*
 * WORKED EXAMPLE (EVERYTHING SO FAR + OBJECTS): Sunrise Community Library Desk
 *
 * A librarian uses this program at the front desk. It:
 *   1. Builds the books as OBJECTS                -> class, fields, overloaded constructors, this
 *   2. Keeps all the books on one "shelf"         -> an ARRAY OF OBJECTS
 *   3. Runs a menu until the librarian quits      -> do-while + enhanced switch + Scanner
 *   4. Searches the shelf for a title             -> for loop + "found" pattern + return
 *   5. Lends and takes back books                 -> object methods that return boolean
 *   6. Charges a late fee                         -> overloaded static methods, Math.min, percentages
 *   7. Prints a weekly report                     -> 2D array, row totals, column totals, "best so far"
 *   8. Gives each visitor a ticket number         -> Random
 *   9. Prints neatly                              -> printf + ternary
 *
 * ANALOGY:
 *   class LibraryBook   = the blank catalogue card template (title: ___ author: ___ copies: ___)
 *   new LibraryBook(..) = filling in one real card for one real book
 *   shelf[]             = the card drawer holding every filled-in card
 *   shelf[i].copiesIn   = what is written in the "copies on shelf" box of card number i
 *
 * SAMPLE RUN (ticket number is random):
 *   Welcome! Your ticket number is 412
 *   1) List books  2) Borrow  3) Return  4) Weekly report  0) Quit
 *   Choose: 2
 *   Title: purple hibiscus
 *   Enjoy "Purple Hibiscus"! 0 copies left.
 *   Choose: 2
 *   Title: Purple Hibiscus
 *   Sorry, all copies of "Purple Hibiscus" are out.
 *   Choose: 3
 *   Title: Purple Hibiscus
 *   How many days late? 30
 *   Are you a student? (true/false): true
 *   Thanks for returning "Purple Hibiscus".
 *   Late fee: N800.00
 *   Choose: 0
 */
public class LibraryDesk {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // ---------- 1 + 2. OBJECTS ON A SHELF ----------
        // Each "new LibraryBook(...)" picks a DIFFERENT constructor depending on how many
        // arguments you pass. Java matches the call to the constructor whose parameter list fits.
        LibraryBook[] shelf = {
            new LibraryBook("Things Fall Apart", "Chinua Achebe", 3),   // 3-argument constructor
            new LibraryBook("Purple Hibiscus", "Chimamanda Adichie", 1), // 3-argument constructor
            new LibraryBook("The Alchemist", "Paulo Coelho"),            // 2-argument -> 1 copy
            new LibraryBook("Mystery Donation")                          // 1-argument -> unknown author, 1 copy
        };

        // ---------- 8. RANDOM ----------
        // nextInt(900) gives 0..899, so + 100 gives 100..999 (always 3 digits).
        int ticket = random.nextInt(900) + 100;
        System.out.println("Welcome! Your ticket number is " + ticket);

        // ---------- 3. THE MENU LOOP ----------
        // do-while because the menu must show AT LEAST once before we know the choice.
        int choice;
        do {
            System.out.println("1) List books  2) Borrow  3) Return  4) Weekly report  0) Quit");
            System.out.print("Choose: ");
            choice = input.nextInt();
            input.nextLine(); // THE nextLine() TRAP: eat the leftover Enter after nextInt()

            switch (choice) {
                case 1 -> {
                    for (int i = 0; i < shelf.length; i++) {
                        shelf[i].printCard(i + 1); // i + 1 so humans see 1,2,3,4 instead of 0,1,2,3
                    }
                }
                case 2 -> {
                    System.out.print("Title: ");
                    String title = input.nextLine();
                    int index = findBook(shelf, title);

                    if (index == -1) {
                        System.out.println("We don't have \"" + title + "\".");
                    } else if (shelf[index].borrow()) {   // borrow() changes the object AND reports success
                        System.out.println("Enjoy \"" + shelf[index].title + "\"! "
                                + shelf[index].copiesIn + " copies left.");
                    } else {
                        System.out.println("Sorry, all copies of \"" + shelf[index].title + "\" are out.");
                    }
                }
                case 3 -> {
                    System.out.print("Title: ");
                    String title = input.nextLine();
                    int index = findBook(shelf, title);

                    if (index == -1) {
                        System.out.println("That book isn't ours.");
                    } else if (!shelf[index].giveBack()) {
                        System.out.println("All copies are already on the shelf - check the title.");
                    } else {
                        System.out.print("How many days late? ");
                        int daysLate = input.nextInt();
                        System.out.print("Are you a student? (true/false): ");
                        boolean isStudent = input.nextBoolean();
                        input.nextLine();

                        System.out.println("Thanks for returning \"" + shelf[index].title + "\".");
                        System.out.printf("Late fee: N%.2f%n", lateFee(daysLate, isStudent));
                    }
                }
                case 4 -> weeklyReport(shelf);
                case 0 -> System.out.println("Goodbye, ticket " + ticket + "!");
                default -> System.out.println("Pick 0 to 4.");
            }
        } while (choice != 0);

        input.close();
    }

    // ---------- 4. SEARCHING ----------
    // Returns the INDEX of the book, or -1 meaning "not found" (-1 can never be a real index).
    //
    // LOGIC TRAP (compare with your arrays3.java!):
    //     for (...) {
    //         if (match) { ... }
    //         isFound = true;   <- OUTSIDE the if: runs even when there's NO match
    //         break;            <- OUTSIDE the if: the loop stops after checking only index 0
    //     }
    // Here "return i" sits INSIDE the if, so we only stop when we really find it.
    // And "return -1" sits AFTER the loop, so it only runs once EVERY book has been checked.
    static int findBook(LibraryBook[] shelf, String title) {
        for (int i = 0; i < shelf.length; i++) {
            if (shelf[i].title.equalsIgnoreCase(title)) { // ignores capitals: "purple hibiscus" still matches
                return i;
            }
        }
        return -1;
    }

    // ---------- 6. OVERLOADED METHODS + PERCENTAGES ----------
    // Same name, different parameter lists. The 1-parameter version is the "normal" fee.
    // Rule: N50 per day late, but never more than N1000 (the cap).
    static double lateFee(int daysLate) {
        double fee = daysLate * 50;
        return Math.min(fee, 1000); // Math.min picks the SMALLER: so the fee can't go above 1000
    }

    // Students get 20% OFF.
    //   20% OFF -> they pay 80% of the fee -> fee * 0.80
    //   20% OF  -> fee * 0.20 would be the DISCOUNT itself, not what they pay. Don't mix them up!
    // This version REUSES the first one instead of repeating the per-day maths.
    static double lateFee(int daysLate, boolean isStudent) {
        double fee = lateFee(daysLate);
        if (isStudent) {
            fee = fee * 0.80;
        }
        return fee;
    }
    /*
     * HAND TRACE: lateFee(30, true)
     *   step                       fee
     *   lateFee(30) -> 30 * 50     1500.0
     *   Math.min(1500, 1000)       1000.0
     *   isStudent -> * 0.80         800.0   -> printed as N800.00
     *
     * QUESTION FOR YOU: what is lateFee(4, true)? (answer at the very bottom of this file)
     * Notice the ORDER: cap first, THEN discount. If we discounted first, then capped,
     * a student 30 days late would pay min(1200, 1000) = 1000. Order changes the answer!
     */

    // ---------- 7. 2D ARRAY REPORT ----------
    static void weeklyReport(LibraryBook[] shelf) {
        // loans[book][day]: row = which book (same order as shelf), column = which day
        String[] days = {"Mon", "Tue", "Wed"};
        int[][] loans = {
            {4, 2, 5},  // Things Fall Apart
            {1, 3, 0},  // Purple Hibiscus
            {2, 2, 2},  // The Alchemist
            {0, 1, 0}   // Mystery Donation
        };

        // Header row
        System.out.printf("%-20s", "Book");
        for (int d = 0; d < days.length; d++) {
            System.out.printf("%5s", days[d]);
        }
        System.out.printf("%7s%n", "Total");

        // ROW totals: walk ACROSS a row. The total resets to 0 for EACH book,
        // so "int rowTotal = 0" goes INSIDE the outer loop (put it outside and totals keep piling up).
        int bestIndex = 0;   // "best so far" - start by assuming book 0 is the best
        int bestTotal = -1;  // -1 so that ANY real total (even 0) beats it the first time
        for (int b = 0; b < loans.length; b++) {
            int rowTotal = 0;
            System.out.printf("%-20s", shelf[b].title);
            for (int d = 0; d < loans[b].length; d++) {
                System.out.printf("%5d", loans[b][d]);
                rowTotal += loans[b][d];
            }
            System.out.printf("%7d%n", rowTotal);

            if (rowTotal > bestTotal) {
                bestTotal = rowTotal;
                bestIndex = b;
            }
        }

        // COLUMN totals: walk DOWN a column - the loops are SWAPPED (day outside, book inside).
        // The indexes inside [ ][ ] are NOT swapped: it's still loans[row][column].
        System.out.printf("%-20s", "Day total");
        for (int d = 0; d < days.length; d++) {
            int dayTotal = 0;
            for (int b = 0; b < loans.length; b++) {
                dayTotal += loans[b][d];
            }
            System.out.printf("%5d", dayTotal);
        }
        System.out.println();

        // Ternary: pick "loan" or "loans" so the sentence reads properly
        System.out.println("Most popular: " + shelf[bestIndex].title + " with " + bestTotal
                + (bestTotal == 1 ? " loan" : " loans"));
    }
}

/*
 * THE CLASS (the blank card template).
 * It's in the same file to keep everything together. Only ONE class per file may be "public",
 * and it must match the file name - so this one has no "public" in front.
 */
class LibraryBook {
    String title;
    String author;
    int totalCopies; // how many the library OWNS  (never changes)
    int copiesIn;    // how many are on the shelf right now (goes up and down)

    // Overloaded constructors: 3 ways to fill in a card.
    LibraryBook(String title) {
        this.title = title;
        this.author = "Unknown";
        this.totalCopies = 1;
        this.copiesIn = 1;
    }

    LibraryBook(String title, String author) {
        this.title = title;
        this.author = author;
        this.totalCopies = 1;
        this.copiesIn = 1;
    }

    LibraryBook(String title, String author, int totalCopies) {
        // "this.totalCopies" = the field on the card.  "totalCopies" alone = the value passed in.
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.copiesIn = totalCopies; // a brand-new book starts with every copy on the shelf
    }

    // Returns true if it worked, false if it couldn't. The CALLER decides what message to print.
    boolean borrow() {
        if (copiesIn > 0) {
            copiesIn--;
            return true;
        }
        return false;
    }

    // LOGIC TRAP: you can't give back more copies than the library owns.
    // Without this check, returning the same book twice would make copiesIn = 2 when we own 1.
    boolean giveBack() {
        if (copiesIn < totalCopies) {
            copiesIn++;
            return true;
        }
        return false;
    }

    void printCard(int number) {
        System.out.printf("%d. %-20s by %-20s %d/%d in%n", number, title, author, copiesIn, totalCopies);
    }
}

/*
 * TRY THIS AFTER READING (predict first, then run):
 *   1. Borrow "Things Fall Apart" 4 times. What prints on the 4th try, and why?
 *   2. Return "The Alchemist" without borrowing it first. Which line of code stops it?
 *   3. Change the loans table so EVERY book has 0 loans except Mystery Donation with 1.
 *      What does the "Most popular" line print now? Which part of the code chose "loan"?
 *   4. Move "int rowTotal = 0;" ABOVE the outer for loop. Predict the new Total column, then test.
 *
 * Answer to lateFee(4, true): 4 * 50 = 200 -> min(200, 1000) = 200 -> 200 * 0.80 = 160.0
 */
