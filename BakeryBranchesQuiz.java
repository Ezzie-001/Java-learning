/*
 * QUIZ (2D ARRAYS): Bakery Branches
 *
 * A bakery has 3 branches. Each branch records how many loaves it sold
 * from Monday to Thursday. The owner wants a short report.
 *
 *                     col 0  col 1  col 2  col 3
 *                     Mon    Tue    Wed    Thu
 *      row 0  Central  12     15      9     20
 *      row 1  North     8     10     14      6
 *      row 2  South    20      5     11     13
 *
 * THE OWNER WANTS:
 *   A. The total loaves sold by EACH BRANCH          (one line per branch)
 *   B. The total loaves sold on EACH DAY              (one line per day, all branches added)
 *   C. Which branch sold the most overall, and how many
 *   D. How many "slow days" there were: any single box below 10 loaves
 *
 * ------------------------------------------------------------------
 * STEP 1: (no code yet!)
 *   - Work out A, B, C and D by hand from the table above and write the
 *     answers here. These are your EXPECTED OUTPUTS.
 *
 *       A: Central = ___   North = ___   South = ___
 *       B: Mon = ___   Tue = ___   Wed = ___   Thu = ___
 *       C: ________ with ___ loaves
 *       D: ___ slow days
 *
 *   - In your own words: for A, which index changes fastest as you add,
 *     the row or the column? And for B?
 *
 * STEP 2: PLAN
 *   - For every variable you create, write a one-line comment saying what
 *     it means in the bakery (e.g. "the till receipt for one branch").
 *   - Decide yourself which loops you need and which way round they go.
 *
 * STEP 3: CODE, RUN, COMPARE with your paper answers.
 *
 * STEP 4: TEST (predict the output BEFORE running each one)
 *   - Change North's Thursday from 6 to 30. Does C change? Does D change?
 *   - Make two branches tie for the highest total. Which one does your
 *     program report, and why?
 *
 * LOGIC TRAPS:
 *   - Part B is the trap: you are walking DOWN a column, not across a row.
 *     What does sales.length give you? What does sales[0].length give you?
 *   - Where does each running total get reset to 0? (Think: a fresh receipt per branch.)
 *   - For C, what should "best so far" start at, and would 0 still work
 *     if sales could be negative?
 *   - < vs <= in your loop conditions.
 *
 * Stuck? Ask for a HINT on the exact part you're on, and say what you've tried.
 */
public class BakeryBranchesQuiz {
    public static void main(String[] args) {

        String[] branches = {"Central", "North", "South"};
        String[] days     = {"Mon", "Tue", "Wed", "Thu"};

        int[][] sales = {
            {12, 15,  9, 20},   // Central
            { 8, 10, 14,  6},   // North
            {20,  5, 11, 13}    // South
        };

        // ---------- A. TOTAL PER BRANCH ----------
        System.out.println("A. Total per branch:");
        for (int row = 0; row < sales.length; row++) {
            int branchTotal = 0;
            for (int col = 0; col < sales[row].length; col++) {
                branchTotal += sales[row][col];
            }
            System.out.println("  " + branches[row] + " = " + branchTotal);
        }

        // ---------- B. TOTAL PER DAY ----------
        System.out.println("B. Total per day:");
        for (int col = 0; col < sales[0].length; col++) {
            int dayTotal = 0;
            for (int row = 0; row < sales.length; row++) {
                dayTotal += sales[row][col];
            }
            System.out.println("  " + days[col] + " = " + dayTotal);
        }

        // ---------- C. BEST BRANCH ----------
        int bestTotal = Integer.MIN_VALUE;
        int bestRow = 0;
        for (int row = 0; row < sales.length; row++) {
            int branchTotal = 0;
            for (int col = 0; col < sales[row].length; col++) {
                branchTotal += sales[row][col];
            }
            if (branchTotal > bestTotal) {
                bestTotal = branchTotal;
                bestRow = row;
            }
        }
        System.out.println("C. Best branch: " + branches[bestRow] + " with " + bestTotal + " loaves");

        // ---------- D. SLOW DAYS (below 10) ----------
        int slowDays = 0;
        for (int row = 0; row < sales.length; row++) {
            for (int col = 0; col < sales[row].length; col++) {
                if (sales[row][col] < 10) {
                    slowDays++;
                }
            }
        }
        System.out.println("D. Slow days: " + slowDays);

    }
}
