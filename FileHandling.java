import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class FileHandling {
    public static void main(String[] args) {
//THIS CREATES A NEW FILE
        try {
            File myFile = new File("products.txt");

            if (myFile.createNewFile()) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//THIS WRITES TEXTS INTO THE NEW FILE WE CREATED
        try {
            FileWriter writer = new FileWriter("products.txt", true);

            writer.write("Laptop\n");
            writer.write("Mouse\n");
            writer.write("Keyboard\n");
            writer.write("Monitor\n");
            writer.write("Television\n");
            writer.write("Screen\n");
            writer.close();

            System.out.println("Product saved successfully");

            } catch(IOException e){
                System.out.println(e.getMessage());
        }
// THIS READS THE TEXT WE WROTE THAT IS IN products.txt IN THE TERMINAL 
        try{
            FileReader reader = new FileReader("products.txt");

            int data;
            
            while ((data = reader.read()) != -1){
                System.out.print((char) data);
            }
            reader.close();
        } catch(IOException e){
            System.out.print(e.getMessage());
        }

        
        // THIS TRY BLOCK IS A BUFFEREDREADER
        try{
            BufferedReader reader = new BufferedReader(
                new FileReader("products.txt")
            );
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
            reader.close();
        }   catch(IOException e){
        System.out.print(e.getMessage());
            }

    // THIS TRY BLOCK IS SIMILAR TO BUFFERREAD BUT IT IS WRITE INSTEAD
            try{
                BufferedWriter writer = new BufferedWriter(
                    new FileWriter("products.txt", true)
                );
                writer.write("Laptop\n");
                writer.write("Mouse\n");
                writer.write("Keyboard\n");
                writer.close();
              } catch(IOException e){
                System.out.print(e.getMessage());
            }


        //OVER-WRITING THE PREEXITING INFORMATION. 
        // try{
        // FileWriter writer = new FileWriter("products.txt", //this one is to append so that it will not override the existing information. true);
        // writer.write("Refrigerator");
        // writer.close();
        // } catch(IOException e){
        //     System.out.println("An error occured");
        // }




    }
}