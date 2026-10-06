import java.util.Scanner;
public class weightConversionSolution {
    public static void main(String[] args) {
        Double weight;
        int option;
        System.out.println("Welcome to weight conversion system");

        Scanner input = new Scanner(System.in);


         System.out.println("OPTIONS");
         System.out.println("1. kilogram to pounds");
         System.out.println("2. pounds to kilograms");
         System.out.print("Choose an option: ");
         option = input.nextInt();

         

         if (option == 1) {
            System.out.print("Enter your weight in kg ");
            weight = input.nextDouble();
            System.out.println("Your weight in pounds: " + weight * 2.20);
         }
         else if (option == 2){
            System.out.print("Enter your weight in pounds ");
            weight = input.nextDouble();
            System.out.println("Your weight in kg: " + weight/2.20);
         }

         else{
            System.out.println("Please enter your a valid option");
         }

         input.close();
    }
}