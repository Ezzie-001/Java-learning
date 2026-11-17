import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Inventory {

    static Scanner input = new Scanner(System.in);
    static String product;
    static int productno;
    static String[] products;

    public static void main(String [] args){
        System.out.println("================================");
        System.out.println("INVENTORY MANAGEMENT");
        System.out.println("================================");
        
        


        boolean isRunning = true;

        while(isRunning){
            showMenu();
            System.out.print("Choose an Option: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProduct();
                case 3 -> inventValue();
                case 4 -> {
                    isRunning = false;
                    System.out.println("Exit");
                }
                default -> System.out.println("Choose a number from 1 to 4");
            }
        }
    }

    static void showMenu(){
        System.out.println("1. Add Product");
        System.out.println("2. View Product");
        System.out.println("3. Calculate Inventory Value");
        System.out.println("4. Exit");
    }

    static void addProduct(){
        do{
        System.out.println ("How many products do you want to add? ");
        productno = input.nextInt();
        input.nextLine();
        }
        while(productno <= 0);

        products = new String[productno];

        for(int i = 0; i < productno; i++){
        System.out.println ("Enter product name");
        products[i] = input.nextLine();
        }
System.out.println("You added " + productno + " products");
    }

    static void viewProduct(){
        for (int i = 0; i < products.length; i++){
            System.out.println(products[i]);

        }
    }

    static void inventValue(){
        
    }

}
