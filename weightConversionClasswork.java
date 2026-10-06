import java.util.Scanner;
public class weightConversionClasswork {
    public static void main(String[] args) {
        Double weight;
        Boolean choice1;
        Boolean choice2;
        System.out.println("Welcome to weight conversion system");

        Scanner input = new Scanner(System.in);


         System.out.print("Enter your weight: ");
         weight = input.nextDouble();

         System.out.print("Do you want your weight in kilogram? (true/false)" );
         choice1 = input.nextBoolean();

         System.out.print("Do you want your weight in Pounds? (true/false)" );
         choice2 = input.nextBoolean();


        if(choice2){
            System.out.println("Your weight in pounds is: " + weight*2.20 + "lbs");
        }
        else if(choice1){
            System.out.println("Your weight is: " + weight + "Kg");
        }
        else{
            System.out.println("Enter your weight please");
        }

    }
}
