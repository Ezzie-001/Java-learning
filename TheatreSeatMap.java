import java.util.Scanner;

/*
 * WORKED EXAMPLE (NESTED LOOPS): Grand Theatre Box Office
 *
 * The box office needs a screen that shows every seat in the hall, which ones are
 * booked, and how much money has been made. The program:
 *   1. Asks for the size of the hall                 -> do-while (input checking)
 *   2. Draws the seat map row by row                 -> NESTED FOR LOOPS
 *        - outer loop = one row      (runs once per row)
 *        - inner loop = one seat     (runs once per seat, for EVERY row)
 *   3. Skips the broken seat                         -> continue
 *   4. Counts booked seats per row and in total      -> counters inside / outside the inner loop
 *   5. Finds the first free seat for a walk-in       -> break (and why ONE break isn't enough)
 *   6. Prints the snack bar cup tower                -> inner loop that depends on the outer loop
 *
 * HOW A NESTED LOOP RUNS (3 rows, 4 seats):
 *      row 1 -> seat 1, seat 2, seat 3, seat 4     (inner loop finishes completely)
 *      row 2 -> seat 1, seat 2, seat 3, seat 4     (inner loop starts again from 1)
 *      row 3 -> seat 1, seat 2, seat 3, seat 4
 *      Total times the inner body runs = rows x seats = 3 x 4 = 12
 *
 * SAMPLE RUN:
 *      How many rows? (1-8): 5
 *      How many seats per row? (1-10): 8
 *      How tall is the cup tower? 4
 */
public class TheatreSeatMap {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ---------- 1. SIZE OF THE HALL ----------
        int rows;
        int seatsPerRow;

        do {
            System.out.print("How many rows? (1-8): ");
            rows = input.nextInt();
        } while (rows < 1 || rows > 8);

        do {
            System.out.print("How many seats per row? (1-10): ");
            seatsPerRow = input.nextInt();
        } while (seatsPerRow < 1 || seatsPerRow > 10);

        // ---------- 2-4. SEAT MAP (NESTED LOOPS) ----------
        // Pretend the booking system says a seat is booked when (row x seat) divides by 4.
        // The last 2 rows are VIP: 15.00 a ticket. Every other row is 8.00.
        //
        // Key:  01 = free seat    XX = booked    -- = broken

        int totalBooked = 0;       // counts across the WHOLE hall, so it lives OUTSIDE both loops
        int totalFree = 0;
        double revenue = 0;

        System.out.println();
        System.out.println("              [  S C R E E N  ]");

        for (int row = 1; row <= rows; row++) {                // OUTER: once per row

            int bookedInRow = 0;   // declared INSIDE the outer loop, so it resets to 0 for each new row
            double ticketPrice = (row > rows - 2) ? 15.00 : 8.00;   // ternary: VIP rows cost more

            System.out.printf("Row %d: ", row);

            for (int seat = 1; seat <= seatsPerRow; seat++) {  // INNER: once per seat in this row

                // CONTINUE: row 2, seat 3 is broken. Print it, then skip the rest of
                // this inner loop body so it isn't counted as free or booked.
                if (row == 2 && seat == 3) {
                    System.out.print(" -- ");
                    continue;
                }

                if ((row * seat) % 4 == 0) {
                    System.out.print(" XX ");
                    bookedInRow++;
                    revenue += ticketPrice;
                }
                else {
                    System.out.printf(" %02d ", seat);         // %02d -> 1 shows as 01
                    totalFree++;
                }
            }
            // We're back in the OUTER loop: the inner loop has finished this whole row.
            totalBooked += bookedInRow;
            System.out.printf("  | %d booked%s\n", bookedInRow, (ticketPrice == 15.00) ? "  (VIP)" : "");
        }

        System.out.println();
        System.out.printf("Booked seats : %d\n", totalBooked);
        System.out.printf("Free seats   : %d\n", totalFree);
        System.out.printf("Revenue      : $%,.2f\n", revenue);

        // ---------- 5. BREAK: FIRST FREE SEAT ----------
        // A walk-in customer wants the free seat closest to the screen.
        // IMPORTANT: break only stops the loop it is directly inside (the INNER loop).
        // The outer loop would keep going and find a seat in every row.
        // So we use a boolean "found" and break the outer loop too.

        boolean found = false;
        int freeRow = 0;
        int freeSeat = 0;

        for (int row = 1; row <= rows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                boolean broken = (row == 2 && seat == 3);
                boolean booked = (row * seat) % 4 == 0;

                if (!broken && !booked) {
                    found = true;
                    freeRow = row;
                    freeSeat = seat;
                    break;                 // stops the INNER loop only
                }
            }
            if (found) {
                break;                     // now stop the OUTER loop as well
            }
        }

        if (found) {
            System.out.printf("Walk-in customer: please take Row %d, Seat %02d\n", freeRow, freeSeat);
        }
        else {
            System.out.println("Sorry, the show is sold out.");
        }

        // ---------- 6. CUP TOWER (inner loop depends on the outer loop) ----------
        // The snack bar stacks popcorn cups: level 1 has 1 cup, level 2 has 2 cups...
        // The inner loop runs "level" times, so it gets longer each time round.

        System.out.println();
        System.out.print("How tall is the cup tower? ");
        int height = input.nextInt();
        int totalCups = 0;

        for (int level = 1; level <= height; level++) {
            for (int cup = 1; cup <= level; cup++) {      // <= level, NOT <= height
                System.out.print("[U]");
                totalCups++;
            }
            System.out.println();                         // new line after each level
        }
        System.out.println("Cups needed: " + totalCups);

        input.close();
    }
}
