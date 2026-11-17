import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ProductReader {
    public static void main (String [] args){


// THIS TRY BLOCK IS A FILEREADER 
    // try{
    //     FileReader reader = new FileReader("products.txt");
    //     int data;
    //     while((data = reader.read()) != -1){
    //         System.out.print((char)data);
    //     } 
    //     reader.close();
    // }

    //     catch(IOException e){
    //         System.out.print(e.getMessage());
    //     }

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
    }
}
