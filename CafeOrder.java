import java.util.Scanner;

/*
 * CLASSWORK: Campus Cafe Order System
 * ------------------------------------
 * The campus cafe wants a program that takes a customer's order and prints a receipt.
 *
 * STEP 1 - Ask the user for (use Scanner):
 *      - their name                         (String) 
 *      - the meal they want                 (String)
 *      - their age                          (int)
 *      - whether they have a loyalty card   (boolean)
 *      - how many plates they want          (int)
 *
 * STEP 2 - If the name is empty, print "Name is required!" and call them "Customer".
 *          Otherwise print "Hi <name>, let's take your order."
 *
 * STEP 3 - Use an ENHANCED SWITCH (ignore upper/lower case) to set the price per plate:
 *      jollof      -> 5.00
 *      fried rice  -> 5.50
 *      pasta       -> 4.75
 *      burger      -> 6.25
 *      shawarma    -> 7.00
 *      anything else -> price is 0
 *
 * STEP 4 - Use IF / ELSE IF / ELSE on the number of plates to pick a bulk discount:
 *      10 or more plates -> 15%
 *      5 to 9 plates     -> 10%
 *      2 to 4 plates     -> 5%
 *      1 plate           -> 0%
 *
 * STEP 5 - Use NESTED IF for the extra discount:
 *      If they have a loyalty card:
 *          - if they are under 18 -> extra 20% off
 *          - otherwise            -> extra 10% off
 *      If they do NOT have a loyalty card:
 *          - if they are under 18 -> extra 5% off
 *          - otherwise            -> no extra discount
 *
 * STEP 6 - If the meal was not on the menu, print "<meal> is not on the menu."
 *          Otherwise print a receipt with PRINTF that uses ALL of these:
 *      - %05d   order number (make up any int, e.g. 7)
 *      - %-12s  customer name, left justified
 *      - %c     first letter of the meal (hint: meal.charAt(0))
 *      - %d     number of plates
 *      - %b     loyalty card true/false
 *      - %.2f   price per plate
 *      - %(.2f  the amount saved shown as a negative number
 *      - %,.2f  the final total
 *
 * SAMPLE RUN:
 *      Enter your name: Tola
 *      What would you like? Fried Rice
 *      Enter your age: 16
 *      Do you have a loyalty card? (true/false): true
 *      How many plates? 12
 *      Hi Tola, let's take your order.
 *
 *      ======== CAFE RECEIPT ========
 *      Order No.  : 00007
 *      Customer   : Tola        |
 *      Meal       : Fried Rice (F)
 *      Plates     : 12
 *      Loyalty    : true
 *      Per plate  : $5.50
 *      Saved      : $(21.12)
 *      TOTAL      : $44.88
 *      ==============================
 *
 *   (Check: 5.50 x 12 = 66.00 -> 15% bulk off = 56.10 -> extra 20% loyalty off = 44.88)
 *
 * BONUS:
 *      - Print "Weekend special: free drink!" if the user also enters a day that is
 *        Saturday or Sunday (use a second switch).
 *      - Use %,.2f to show a total over 1,000 correctly (try 300 burgers).
 */
public class CafeOrder {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Write your code here
//declare variables
        String name;
        String meal;
        int age;
        double price;
        boolean isLoyalClient;
        int plate; 
        double basePrice=0;
        int orderNumber = 45;
       
        //output the input
        System.out.print("Enter your name: ");
            name = input.nextLine();

        System.out.print("Enter the meal you want: ");
            meal = input.nextLine();

        System.out.print("Enter your age: ");
            age = input.nextInt();

        System.out.print("Do you have a loyalty card? (true/false): ");
            isLoyalClient = input.nextBoolean();

        System.out.print("Enter the number of plates you want: ");
            plate = input.nextInt();
            input.nextLine();


        //if statement
if(name.isEmpty()){
    name = "Customer"; 
        System.out.println("Name is required");
    }
    else{
        System.out.println("hi " + name + " let's take your order");
    }

    //enhanced switch
switch(meal.toLowerCase()){
    case "jollof" -> basePrice = 5.00;
    case "fried rice" -> basePrice = 5.50;
    case "pasta" -> basePrice = 4.75;
    case "burger" -> basePrice = 6.25;
    case "shawarma" -> basePrice = 7.00;
    default -> basePrice = 0.00;
}

price = basePrice;

//if/else if, else
    if(plate>= 10){
        System.out.println("You have received a discount of 15%");
        price *= 0.85; 
    }
    else if(plate >= 5 && plate < 10){
        System.out.println("You've received a discount of 10%");
        price *= 0.90;
        }

    else if(plate >= 2 && plate < 5){
            System.out.println("You have received a discount of 5%");
            price *= 0.95;
        }
    else if(plate == 1){
            System.out.println("No discount!");
            price *= 1;
        }
        else{
            System.out.println("How many plates do you want?");
        }
    

double price2 = price;


    //nested if
    if(isLoyalClient = true){
        if(age < 18){
            System.out.println("You have received 20% discount!!");
                price2 *= 0.2;
            }
            else{
            System.out.println("You have received 10% discount!!");
                price2 *= 0.1;
            }
        }
    else{
        if(age<18){
            System.out.println("You have received 5% discount!!");
                price2 *= 0.05;
        }
        else{
            System.out.println("You have received no discount!!");
                price2 *= 1;
        }
    }

   double total = (price * plate) - (price2 * plate);
   double totalPrice = basePrice * plate;

    if(meal.isEmpty()){
        System.out.println("Enter a valid meal");
    }
    else{
        System.out.println();
        System.out.println("=======RECEIPT============");
        System.out.printf("Order No      :    %5d\n", orderNumber);
        System.out.printf("Customer      :    %-12s|\n", name);
        System.out.printf("Initial       :    %s\n", meal);
        System.out.printf("No of plate   :    %6d\n", plate);
        System.out.printf("Loyalty card  :    %6b\n", isLoyalClient);
        System.out.printf("Plate price   :    %6.2f\n", basePrice);
        System.out.printf("Original Price:  %8.2f\n",   totalPrice);
        System.out.printf("Amt saved     :    %6.2f\n", (basePrice * plate) - total);
        System.out.printf("Total         :    %6.2f\n", total);
        System.out.println("===========================");
    }

    input.close();
}

    }


//so we have initial price
//we have discounted price from loyalty(price * discount)
// then we have a new price from the discount
//then we apply a new discount on an already discounted price
//Then that would be out final price