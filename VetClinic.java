/*
 * CLASSWORK (EVERYTHING SO FAR + OBJECTS): Paws & Claws Vet Clinic
 *
 * You write ALL the code this time. Read LibraryDesk.java first - it uses every tool you need.
 *
 * ------------------------------------------------------------------
 * THE SCENARIO
 * ------------------------------------------------------------------
 * The clinic receptionist needs a small program. Pets arrive with different amounts of info:
 *   - some owners only give the pet's NAME
 *   - some give NAME and SPECIES
 *   - some give NAME, SPECIES, AGE and WEIGHT
 *
 * The four pets in today's records:
 *
 *      Name    Species   Age   Weight(kg)   What the owner told you
 *      Rex     dog        4     20.0        everything
 *      Misty   cat       11      4.2        everything
 *      Bolu    parrot     2      0.4        everything
 *      Tiny    ???        ?      ???        only the name
 *
 * Weight checks over the last 3 months (kg):
 *
 *                 Month 1   Month 2   Month 3
 *      Rex         20.0      21.5      23.0
 *      Misty        4.2       4.0       3.9
 *      Bolu         0.4       0.5       0.5
 *      Tiny         1.0       1.0       1.2
 *
 * PRICE RULES for one visit:
 *   - Base price by species:  dog = N5000,  cat = N4000,  anything else = N3000
 *     (the receptionist might type "DOG" or "Dog" - that's still a dog)
 *   - EMERGENCY visit: costs 50% EXTRA on top of the base price
 *   - SENIOR pet (age 10 or older): 10% OFF the final price, applied LAST
 *
 * THE PROGRAM MUST:
 *   A. Create the 4 pets as objects using OVERLOADED CONSTRUCTORS.
 *      A pet with only a name gets species "unknown", age 0, weight 0.0.
 *      Every pet starts NOT vaccinated.
 *   B. Keep them in one array of Pet objects.
 *   C. Ask the receptionist for a pet's name and search the array for it
 *      (any capitals). If not found, say so and stop.
 *   D. If found: print that pet's details, ask "Emergency? (true/false)", then print the visit cost.
 *      Do the cost with TWO overloaded static methods called visitCost:
 *         one for a normal visit, one that also takes the emergency answer.
 *   E. Vaccinate the pet (a method on the object), then print its details again to prove it changed.
 *   F. Weight report using a 2D array:
 *         - each pet's change in weight (Month 3 minus Month 1)
 *         - which pet GAINED the most, and by how much
 *         - how many single readings were under 1.0 kg
 *      Print with printf to 1 decimal place.
 *
 * ------------------------------------------------------------------
 * STEP 1: PAPER FIRST (no code until this is done!)
 * ------------------------------------------------------------------
 * 1. Work out these costs by hand. Show the steps like the trace in LibraryDesk.lateFee().
 *        Rex,   normal     = ______
 *        Rex,   emergency  = ______
 *        Misty, normal     = ______
 *        Misty, emergency  = ______
 *        Tiny,  emergency  = ______
 *
 * 2. "50% extra" - which is right, and why?   price * 0.5   or   price * 1.5   ?
 *    "10% off"   - which is right, and why?   price * 0.10  or   price * 0.90  ?
 *
 * 3. Weight report by hand:
 *        change:  Rex ____  Misty ____  Bolu ____  Tiny ____
 *        biggest gain: ________ by ____ kg
 *        readings under 1.0 kg: ____
 *    Misty's change is NEGATIVE. If you start your "best so far" at 0, does that cause a problem
 *    here? Would it cause a problem if EVERY pet had lost weight? What should you start it at?
 *
 * 4. ANALOGY: in your own words, what is the difference between
 *        Pet          (the class)
 *        new Pet(..)  (creating an object)
 *        pets[1]      (one item in the array)
 *        pets[1].age  (a field)
 *    Use a real-world comparison like LibraryDesk's catalogue card.
 *
 * ------------------------------------------------------------------
 * STEP 2: PLAN (write these yourself, inside main, before any code)
 * ------------------------------------------------------------------
 *   // INPUT:
 *   // PROCESS:
 *   // OUTPUT:
 *   Also list the FIELDS and METHODS your Pet class needs. Then list each static method in this
 *   file: its name, what it takes in, and what it gives back.
 *
 * ------------------------------------------------------------------
 * STEP 3: TEST CASES (predict the output BEFORE you run each one)
 * ------------------------------------------------------------------
 *   Typed name   Emergency   Should show
 *   Rex          false       Rex's details, your Rex-normal cost, then vaccinated = true
 *   misty        true        Misty found even in lowercase, your Misty-emergency cost
 *   TINY         true        species "unknown" and your Tiny-emergency cost
 *   Garfield     (not asked) a "not found" message - the program must NOT ask about emergency
 *
 * ------------------------------------------------------------------
 * LOGIC TRAPS TO WATCH FOR
 * ------------------------------------------------------------------
 *   - Your search: does the "found" part sit INSIDE the if, or does it run on every loop?
 *     (Look back at your own arrays3.java - there's a bug like this in there. Can you spot it?)
 *   - Senior discount: is it applied before or after the emergency extra? Does the order change the
 *     answer for Misty? Check on paper.
 *   - Emergency extra: can you write it so you don't type the species prices twice?
 *     (Hint: one visitCost can CALL the other one.)
 *   - The ".equals" vs "==" question for Strings.
 *   - The nextLine() trap after nextBoolean().
 *
 * ------------------------------------------------------------------
 * BONUS (only after everything above works)
 * ------------------------------------------------------------------
 *   - Wrap C to E in a loop so the receptionist can check pets until they type "done".
 *   - Add a 5th pet using the NAME + SPECIES constructor and make sure every part still works
 *     without changing any loop. (If you have to change a loop, ask yourself why: .length?)
 */
public class VetClinic {
    public static void main(String[] args) {
        // Your plan (STEP 2) goes here first, then your code.

    }

    // Your static methods go here.

}

// Your Pet class goes here (no "public" in front - see the note in LibraryDesk.java).
