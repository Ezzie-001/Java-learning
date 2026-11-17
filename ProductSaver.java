import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;


public class ProductSaver {
    public static void main(String [] args){

Scanner input = new Scanner(System.in);
        boolean addAnother;
        String proName;
        int price;

        


        // try{
        //     File myFile = new File("products.txt");
        //     if(myFile.createNewFile()){
        //         System.out.print("File Created Successfully");
        //     } else{
        //         System.out.print("File already exists");
        //     }
        // } catch(IOException e){
        //     System.out.println(e.getMessage());
        // }

        do{
            System.out.print("Enter a product name: ");
            proName = input.nextLine();
            System.out.print("Enter the product price: ");
            price = input.nextInt();


        //     try{
        //     BufferedWriter writer = new BufferedWriter(
        //     new FileWriter("products.txt", true)
        //     );

        //     writer.write(proName);
        //     writer.newLine();
        //     writer.close();

        // } 
        try(
            BufferedWriter writer = new BufferedWriter
            (
                new FileWriter("products.txt", true)
            )
        )
        {
            writer.write(proName);
            writer.newLine();
            writer.write(String.valueOf(price));
            writer.newLine();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }

            System.out.print("Do you want to add another product? ");
            addAnother = input.nextBoolean();
            input.nextLine();
            
        } while(addAnother);

        input.close();
        
    }
}
