import java.util.Random;
import java.util.Scanner;

/*
 * EXERCISE: Lagoon View Hotel - Front Desk Booking
 * -------------------------------------------------
 * A guest arrives at the front desk of Lagoon View Hotel. The receptionist uses your
 * program to book the room and print a booking slip for the guest.
 *
 * Before you start: write the 8 steps below as // comments inside main, then fill
 * them in ONE step at a time, compiling and running after each step.
 * GymMembership.java is the worked example - every step here has a matching step there.
 *
 * STEP 1 - Ask the guest for (use Scanner, IN THIS ORDER):
 *      - their name     (String)  -> use a WHILE loop: keep asking until it isn't empty
 *      - their email    (String)
 *      - their age      (int)     -> use a WHILE loop: if under 18, print
 *                                    "You must be 18 or older to book." and ask again
 *      - rewards member (boolean)
 *      - nights         (int)     -> use a DO-WHILE loop: keep asking until it is 1 to 30
 *      - room type      (String - can be TWO words, e.g. "sea view")
 *
 *      HINT: look at what comes right before the room type question.
 *            Remember what nextInt() leaves behind.
 *
 * STEP 2 - Use IF / ELSE with contains("@"):
 *          If the email has an @, use indexOf() and substring() to split it, and print
 *              "Confirmation will be sent to <username> at <domain>"
 *          Otherwise print "Invalid email - no confirmation will be sent."
 *
 * STEP 3 - Use an ENHANCED SWITCH (ignore upper/lower case) to set the price per night:
 *      single        -> 40.00
 *      double        -> 65.00
 *      family suite  -> 95.00
 *      sea view      -> 120.00
 *      anything else -> price is 0
 *
 *      HINT: keep TWO price variables - one for the original price per night, and one
 *            that the discounts change. You will need both for "Saved" in Step 8.
 *
 * STEP 4 - Use IF / ELSE IF / ELSE on the number of nights for a long-stay discount:
 *      14 or more nights -> 20% off
 *      7 to 13 nights    -> 15% off
 *      3 to 6 nights     -> 5% off
 *      1 or 2 nights     -> no discount
 *
 * STEP 5 - Use NESTED IF for the extra discount:
 *      If they ARE a rewards member:
 *          - if they are 60 or older -> extra 15% off
 *          - otherwise               -> extra 8% off
 *      If they are NOT a rewards member:
 *          - if they are 60 or older -> extra 5% off
 *          - otherwise               -> no extra discount
 *
 * STEP 6 - Work out (use the MATH class and the TERNARY operator):
 *      - total for the whole stay, and how much was saved on the whole stay
 *      - reward points: 1 point for every 4.00 of the total, rounded  -> Math.round()
 *      - housekeeping visits: one every 3 nights, rounded UP          -> Math.ceil()
 *            (careful: nights / 3 with two ints throws away the decimal. Use nights / 3.0)
 *      - guestType  = "Rewards Member" if they are a member, otherwise "Standard Guest"  -> ternary
 *      - breakfast  = "Included" if they stay 7+ nights, otherwise "Not included"         -> ternary
 *
 * STEP 7 - Use RANDOM to make:
 *      - a booking number from 1 to 999
 *      - a room number from 100 to 499
 *
 * STEP 8 - If the room type was not valid, print "<room> is not a room we offer."
 *          Otherwise print a booking slip with PRINTF that uses ALL of these:
 *      - %05d   booking number
 *      - %-14s  guest name, left justified, followed by a |
 *      - %c     first letter of the room type
 *      - %d     room number, nights, reward points
 *      - %b     rewards member true/false
 *      - %s     guest type, breakfast
 *      - %.0f   housekeeping visits (Math.ceil gives back a double)
 *      - %.2f   original price per night, discounted price per night
 *      - %(.2f  the amount saved shown as a negative number in brackets
 *      - %,.2f  the final total with a comma
 *
 * SAMPLE RUN (booking and room numbers are random, so yours will differ):
 *      Enter your name:
 *      Enter your name: Ada
 *      Enter your email: ada.obi@yahoo.com
 *      Enter your age: 65
 *      Are you a rewards member? (true/false): true
 *      How many nights? (1-30): 40
 *      How many nights? (1-30): 14
 *      Which room? (single / double / family suite / sea view): Sea View
 *      Welcome to Lagoon View, Ada!
 *      Confirmation will be sent to ada.obi at yahoo.com
 *      14+ nights: 20% off
 *      Rewards member 60+: extra 15% off
 *
 *      ======== LAGOON VIEW HOTEL ========
 *      Booking No.  : 00316
 *      Guest        : Ada           |
 *      Guest type   : Rewards Member
 *      Room         : Sea View (S) - Room 247
 *      Nights       : 14
 *      Member       : true
 *      Breakfast    : Included
 *      Housekeeping : 5 visits
 *      Per night    : $120.00
 *      You pay/night: $81.60
 *      Saved        : $(537.60)
 *      TOTAL        : $1,142.40
 *      Reward points: 286
 *      ===================================
 *
 *   (Check: 120.00 -> 20% off = 96.00 -> extra 15% off = 81.60 per night
 *           81.60 x 14 = 1,142.40      saved = (120.00 - 81.60) x 14 = 537.60
 *           points = 1,142.40 / 4 = 285.6 -> rounds to 286
 *           housekeeping = 14 / 3.0 = 4.67 -> rounds UP to 5)
 *
 * MORE TEST CASES - your program should give these:
 *      Bayo, 30, false, 2, single         -> TOTAL $80.00,    points 20,  housekeeping 1, breakfast Not included
 *      Chi, 45, true, 7, Double           -> TOTAL $355.81,   points 89,  housekeeping 3, breakfast Included
 *      Dami, 70, false, 3, family suite   -> TOTAL $257.21,   points 64,  housekeeping 1
 *      Efe, 25, false, 5, penthouse       -> "penthouse is not a room we offer."
 *      Age 16                             -> "You must be 18 or older to book." then asks again
 *      Email without @                    -> "Invalid email - no confirmation will be sent."
 *
 * BONUS:
 *      - After the room type, also ask "Which day are you checking in?" and use a second
 *        switch: Friday or Saturday check-ins add 10.00 per night (add it BEFORE the discounts).
 *      - Use a do-while loop at the end: "Book another room? (yes/no)" and run the whole
 *        booking again if they type yes. (Watch out for the nextLine() trap again!)
 */
public class HotelBooking {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        // Write your code here
        String name = "";
        String email;
        int age;
        boolean rewardMember;
        int nights;
        String roomType;
         

        while(name.isEmpty()){
            System.out.print("Enter your name: ");
            name = input.nextLine();
            if(name.isEmpty()){
                System.out.println("Name cannot be empty, Please enter your name.");
        }else{
            System.out.println("Welcome " + name);
        }
            
        }
    
        System.out.print("Enter your email: ");
        email = input.nextLine();


//age registratiion.
        System.out.print("Enter your age: ");
        age = input.nextInt();
       while(age < 18){
        System.out.println("You must be 18 and older to book!");
            age = input.nextInt();  
        }
        
       System.out.println("Are you a rewards member? (true or false)");
       rewardMember = input.nextBoolean();

    
       do { 
           System.out.println("How many nights are you staying here? ");
           nights = input.nextInt();
        }
        while (nights < 1 || nights > 30); 
        input.nextLine();
       

        System.out.print("Enter the type of room you want: ");
        roomType = input.nextLine();


        String username;
        if(email.contains("@")){
        username = email.substring(0,email.indexOf("@"));
        String domain = email.substring(email.indexOf("@") + 1);
        System.out.println("Confirmation would be sent to: " + username + "at" + domain);
        }
       else{
        System.out.println("No confirmation email would be sent");
        }
        
double basePrice;

        switch(roomType.toLowerCase()) {
            case "single" -> basePrice = 40.00;
            case "double" -> basePrice = 65.00;
            case "family suite" -> basePrice = 95.00;
            case "sea view" -> basePrice = 120.00;
            default -> basePrice = 0;
        }

       double finalPrice = basePrice;


       if(nights >= 14){
        System.out.println("You have a discount of 20%");
        finalPrice *= 0.80;
       }
       else if (nights >= 7 && nights <= 13) {
           System.out.print("You have a discount of 15%");
           finalPrice *= 0.85;

       } else if (nights >= 3 && nights <= 6){
        System.out.print("You have a discount of 5%");
        finalPrice *= 0.95;
        
       } else if (nights >=1 && nights <=2){
        System.out.print("You have no discount");
       } 
       else{
        System.out.print("Choose the number of days you want.");
       }


       

 
if(rewardMember){
    if(age >= 60){
        System.out.print("You have extra 15% off");
        basePrice *= 0.85;
    } else{
        System.out.print("You have extra 8% off");
        basePrice *= 0.92;
    }
}
else{
    if(age >= 60){
        System.out.print("You have extra 5% off");
        basePrice *= 0.95;
    }
    else{
        System.out.print("No extra discount");
    }
    }




finalPrice = basePrice * nights;

double total = finalPrice * nights;
double saved = basePrice;

input.close();
        }
    }


