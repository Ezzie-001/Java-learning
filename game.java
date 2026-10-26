import java.util.Random;
import java.util.Scanner;

public class game {
    public static void main(String[] args){
        Random ram = new Random();
        Scanner scanner = new Scanner(System.in);

        int luckyNumber = ram.nextInt(1, 11);
        int maxTrials = 5;
        double prizeMoney = 1000000;
        double penalty = 200000;
        int guess;
        boolean won = false;

        System.out.println("Guess the lucky number between 1 and 10. You have " + maxTrials + " trials.");

        for(int trial = 1; trial <= maxTrials; trial++){
            System.out.print("Trial " + trial + " - enter your guess: ");
            guess = scanner.nextInt();

            if(guess == luckyNumber){
                won = true;
                break;
            }
            else if(guess < luckyNumber){
                System.out.println("Too low, try again");
                System.out.println("Your cash price has been reduced by 200000, your current price is " + (prizeMoney - penalty));
            }
            else{
                System.out.println("Too high, try again");
                System.out.println("Your cash price has been reduced by 200000, your current price is " + (prizeMoney - penalty));
            }

            prizeMoney = prizeMoney - penalty;
            System.out.println("");
        }

        if(won){
            System.out.println("Congratulations! You win $" + prizeMoney);
        }
        else{
            System.out.println("Out of trials! The lucky number was " + luckyNumber);
        }

        scanner.close();
    }
}


//Ask the user to gues the lucky number within a range 1-10. if right, congratulations.
    //if the answ is lower than lucky no "too low, try again" if it is higher"too high try again
    ///additionally, the trials will be 5 trials
    //create a guessing game, generate a random no, which would be regarded as a lucky number
    //if user gets correctly on the first try "congrats you get 1m dollars"
    //but for every wrong answers, the 200,000 price money would be deducted
