/*
 * WORKED EXAMPLE (2D ARRAYS): Class Gradebook
 *
 * A teacher keeps every student's test scores in one table. The program:
 *   1. Stores the scores in a 2D array               -> int[][] (rows x columns)
 *   2. Prints the gradebook as a neat table          -> nested for loops + printf
 *   3. Works out each STUDENT's total and average    -> walk ACROSS a row
 *   4. Works out each SUBJECT's average              -> walk DOWN a column (loops swapped!)
 *   5. Finds the top student                         -> "best so far" variable
 *   6. Counts every failed score (below 50)          -> scan the whole grid
 *
 * ANALOGY: the 2D array is the teacher's paper gradebook.
 *      scores           = the whole page
 *      scores[r]        = one student's line on the page   (a row)
 *      scores[r][c]     = one box on that line             (row r, column c)
 *
 *                  col 0   col 1    col 2
 *                  Math    English  Science
 *      row 0  Ada   78      85       90
 *      row 1  Ben   62      70       58
 *      row 2  Chi   95      88       92
 *      row 3  Dami  55      49       61
 *
 *      scores.length      = number of ROWS    = 4 (students)
 *      scores[0].length   = number of COLUMNS = 3 (subjects)
 *
 * GOLDEN RULE: always write it as  scores[row][column]  (row first, like "R before C" in the alphabet... almost!)
 */
public class ClassGradebook {
    public static void main(String[] args) {

        // ---------- 1. THE DATA ----------
        // names[r] belongs to row r of scores, and subjects[c] belongs to column c.
        String[] names    = {"Ada", "Ben", "Chi", "Dami"};
        String[] subjects = {"Math", "English", "Science"};

        int[][] scores = {
            {78, 85, 90},  // Ada
            {62, 70, 58},   // Ben
            {95, 88, 92},   // Chi
            {55, 49, 61}    // Dami
        };

        // ---------- 2. PRINT THE TABLE ----------
        // Outer loop = one student's line. Inner loop = each box on that line.
        System.out.printf("%-8s", "Student");
        for (int c = 0; c < subjects.length; c++) {
            System.out.printf("%9s", subjects[c]);
        }
        System.out.println();

        for (int r = 0; r < scores.length; r++) {
            System.out.printf("%-8s", names[r]);
            for (int c = 0; c < scores[r].length; c++) {
                System.out.printf("%9d", scores[r][c]);
            }

            System.out.println();   // new line AFTER the inner loop finishes the row
        } 
        System.out.println();

        // ---------- 3. EACH STUDENT'S TOTAL + AVERAGE (walk ACROSS a row) ----------
        // studentTotal = a sticky note for ONE student. It must be wiped (set to 0)
        // at the start of every new row, so it lives INSIDE the outer loop.
        //
        // TRACE for row 0 (Ada):
        //      c | scores[0][c] | studentTotal22
        //      0 |     78       |     78
        //      1 |     85       |    163
        //      2 |     90       |    253      -> average = 253 / 3 = 84.33
        System.out.println("--- Student averages ---");
        for (int r = 0; r < scores.length; r++) {
            int studentTotal = 0;
            for (int c = 0; c < scores[r].length; c++) {
                studentTotal += scores[r][c];
            }
            // (double) matters! 253 / 3 with two ints = 84, the .33 is thrown away.
            double average = (double) studentTotal / scores[r].length;
            System.out.printf("%-5s total: %3d   average: %.2f\n", names[r], studentTotal, average);
        }
        System.out.println();

        // ---------- 4. EACH SUBJECT'S AVERAGE (walk DOWN a column) ----------
        // Now the OUTER loop is the column and the INNER loop is the row.
        // Think: "pick one subject, then go down the page asking every student."22222222222
        // The ACCESS is still scores[r][c] -- only the loop ORDER changed.
        //
        // TRACE for column 0 (Math):
        //      r | scores[r][0] | subjectTotal
        //      0 |     78       |     78
        //      1 |     62       |    140
        //      2 |     95       |    235
        //      3 |     55       |    290      -> average = 290 / 4 = 72.50
        System.out.println("--- Subject averages ---");
        for (int c = 0; c < scores[0].length; c++) {      // scores[0].length = number of columns
            int subjectTotal = 0;
            for (int r = 0; r < scores.length; r++) {     // scores.length = number of rows
                subjectTotal += scores[r][c];
            }
            double average = (double) subjectTotal / scores.length;
            System.out.printf("%-8s average: %.2f\n", subjects[c], average);
        }
        System.out.println();

        // ---------- 5. TOP STUDENT ("best so far") ----------
        // Like holding the trophy: whoever beats the current holder takes it.
        // bestRow remembers WHERE the winner is, so we can look up their name later.
        int bestTotal = -1;   // lower than any real total, so the first student always takes the trophy
        int bestRow = -1;

        for (int r = 0; r < scores.length; r++) {
            int total = 0;
            for (int c = 0; c < scores[r].length; c++) {
                total += scores[r][c];
            }
            if (total > bestTotal) {   // > (not >=) means on a tie, the FIRST student keeps the trophy
                bestTotal = total;
                bestRow = r;
            }
        }
        System.out.println("Top student: " + names[bestRow] + " with " + bestTotal + " marks");

        // ---------- 6. COUNT FAILED SCORES (scan every box) ----------
        // Direction doesn't matter here: we just need to visit every box once.
        int fails = 0;
        for (int r = 0; r < scores.length; r++) {
            for (int c = 0; c < scores[r].length; c++) {
                if (scores[r][c] < 50) {
                    fails++;
                    System.out.println("  Fail: " + names[r] + " in " + subjects[c] + " (" + scores[r][c] + ")");
                }
            }
        }
        System.out.println("Total failed scores: " + fails);

        /*
         * EXPECTED OUTPUT:
         *
         * Student      Math  English  Science
         * Ada            78       85       90
         * Ben            62       70       58
         * Chi            95       88       92
         * Dami           55       49       61
         *
         * --- Student averages ---
         * Ada   total: 253   average: 84.33
         * Ben   total: 190   average: 63.33
         * Chi   total: 275   average: 91.67
         * Dami  total: 165   average: 55.00
         *
         * --- Subject averages ---
         * Math     average: 72.50
         * English  average: 73.00
         * Science  average: 75.25
         *
         * Top student: Chi with 275 marks
         *   Fail: Dami in English (49)
         * Total failed scores: 1
         *
         * TRY THESE (predict first, then change the code and run):
         *   a) Move "int studentTotal = 0;" ABOVE the outer loop in section 3. What does Ben's total become?
         *   b) Remove "(double)" in section 3. What does Ada's average print?
         *   c) Change "<" to "<=" in any loop. Which error do you get, and which index caused it?
         *   d) Change Ben's English score to 95. Does the top student change? Work it out on paper first.
         * 
         */
    }
}
