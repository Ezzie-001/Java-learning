import java.util.Scanner;

/*
 * CLASSWORK (NESTED LOOPS): CityPark Multi-Storey Car Park
 * ---------------------------------------------------------
 * CityPark wants a screen at the entrance that shows every parking space on every
 * floor, how many cars are parked, how much money has been made, and where the
 * next car should go.
 *
 * TheatreSeatMap.java is the worked example - every step here has a matching step there.
 * Write the steps as // comments first, then fill them in ONE at a time and run after each.
 *
 * STEP 1 - Use DO-WHILE loops to ask for:
 *      - the number of floors           (keep asking until it is 1 to 5)
 *      - the number of spaces per floor (keep asking until it is 1 to 10)
 *
 * STEP 2 - Use NESTED FOR LOOPS to draw the car park:
 *      - OUTER loop: one floor at a time (floor 1 up to the last floor)
 *      - INNER loop: one space at a time (space 1 up to the last space)
 *      - Start every floor's line with "Floor <number>: "
 *
 *      What to print for each space (check them IN THIS ORDER):
 *          a) Floor 2, space 4 is a concrete PILLAR  -> print [##] and use CONTINUE
 *                                                       (a pillar is not free OR parked)
 *          b) If (floor + space) % 3 == 0 a car is parked there -> print [XX]
 *          c) Otherwise, if it is space 1, it is an electric charging bay -> print [EV]
 *          d) Otherwise it is a normal free space -> print the space number with %02d in [ ]
 *                                                    e.g. [03]
 *
 * STEP 3 - Count while you loop:
 *      - parked cars on THIS floor (must reset to 0 for every new floor)
 *        -> print it at the end of each floor's line: "  2 parked"
 *      - total parked cars in the whole car park
 *      - total free spaces in the whole car park ([EV] counts as free)
 *      - earnings: every parked car on floor 1 paid 5.00, on any other floor 3.00
 *        (use the TERNARY operator to pick the fee for each floor)
 *
 *      After the loops, print with PRINTF:
 *          Cars parked : <%d>
 *          Free spaces : <%d>
 *          Earnings    : $<%,.2f>
 *
 * STEP 4 - BREAK: find the next free space for a new car.
 *      - Search floor 1 first, then floor 2, and so on.
 *      - Skip the pillar, skip parked spaces, and skip the [EV] bay (normal cars can't use it).
 *      - Stop searching as soon as you find one.
 *        HINT: break only stops the INNER loop. How do you stop the OUTER one too?
 *      - Print "Next car: go to Floor <f>, Space <s>"   or   "Garage full!" if there is none.
 *
 * STEP 5 - The ramp sign. Use nested loops to print a number staircase with as many
 *          lines as there are floors. Line 1 counts to 1, line 2 counts to 2, and so on:
 *              1
 *              1 2
 *              1 2 3
 *              1 2 3 4
 *          HINT: the inner loop's limit is the OUTER loop's variable, not a fixed number.
 *
 * EXPECTED OUTPUT (4 floors, 7 spaces):
 *      How many floors? (1-5): 4
 *      How many spaces per floor? (1-10): 7
 *
 *      Floor 1: [EV][XX][03][04][XX][06][07]  2 parked
 *      Floor 2: [XX][02][03][##][05][06][XX]  2 parked
 *      Floor 3: [EV][02][XX][04][05][XX][07]  2 parked
 *      Floor 4: [EV][XX][03][04][XX][06][07]  2 parked
 *
 *      Cars parked : 8
 *      Free spaces : 19
 *      Earnings    : $28.00
 *      Next car: go to Floor 1, Space 3
 *
 *      1
 *      1 2
 *      1 2 3
 *      1 2 3 4
 *
 *   (Check: earnings = 2 cars on floor 1 x 5.00 + 6 cars on floors 2-4 x 3.00 = 10 + 18 = 28.00
 *           free = 28 spaces - 8 parked - 1 pillar = 19)
 *
 * MORE TEST CASES:
 *      Floors 9               -> asks again (must be 1 to 5)
 *      1 floor, 2 spaces      -> Floor 1: [EV][XX]  1 parked   ...   Earnings $5.00, "Garage full!"
 *                                (space 1 is EV and space 2 is parked, so no normal space is free)
 *
 * BONUS:
 *      - Ask "Show the car park again? (yes/no)" at the end and wrap the WHOLE program in a
 *        do-while loop so it repeats when they type yes.
 *      - Print the ramp sign the other way up (4 numbers first, then 3, 2, 1).
 */
public class ParkingGarage {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Write your code here


        input.close();
    }
}
