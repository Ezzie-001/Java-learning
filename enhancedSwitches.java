
import java.util.Scanner;

public class enhancedSwitches {
    public static void main(String[] args) {
        //enhanced switch = A replacement to the many else if statements


        Scanner input = new Scanner(System.in);

        //using if else statements

        System.out.print("Enter the day of the week: ");
        String day = input.nextLine();

        if (day.equals("Monday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Tuesday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Wednesday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Thursday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Friday")){
            System.out.println("It is a weekday");
        }
        else if(day.equals("Saturday")){
            System.out.println("It is a weekend");
        }
        else if(day.equals("Sunday")){
            System.out.println("It is a weekend");
        }

        else{
            System.out.println (day + " is not a day");
        }
       
       
       
        //using enhanced switches
    System.out.print("Enter the day of the week: ");
    String Day = input.nextLine();

    switch (Day.toLowerCase()){
        case "Monday" -> System.out.println("It is a weekday");
        case "Tuesday" -> System.out.println("It is a weekday");
        case "Wednesday" -> System.out.println("It is a weekday");
        case "Thursday" -> System.out.println("It is a weekday");
        case "Friday" -> System.out.println("It is a weekday");
        case "Saturday" -> System.out.println("It is a weekend");
        case "Sunday" -> System.out.println("It is a weekend");
        default -> System.out.println(Day + " is not a day");
    }
    //a shorter way of implementing it

    switch (Day) {
        case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" ->
        System.out.println("It is a weekday");
        default -> System.out.println(Day + " is not a day");

    }
    input.close();
} 
     }