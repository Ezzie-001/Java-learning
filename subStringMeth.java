import java.util.Scanner;
public class subStringMeth {
    public static void main(String args[]){
    //substring() = a method used to extract a portiion of a string
    Scanner input = new Scanner (System.in);
    String email;
    String username;
    String domain;

    System.out.print("Enter your Email: ");
    email = input.nextLine();

    if (email.contains("@")){
        username = email.substring(0,email.indexOf("@"));
        domain = email.substring(email.indexOf("@") + 1);

        System.out.println(username);
        System.out.println(domain);
    }
    else{
        System.out.println("Email must contain @");
    }
   //email = "larrythomzy@gmail.com";
   // String username2 = email.substring(0, 11);p
   // domain = email.substring(0, email.indexOf("@"));
   //String domain2 = email.substring(12);
   //String domain3 = email.substring(email.indexOf("@") + 1);
   //System.out.println(username2);
   //System.out.println(domain3);

   input.close();
    }
}