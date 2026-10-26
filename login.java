import java.util.Scanner;
public class login {

    public static void main(String [] args){
        Scanner input  = new Scanner(System.in);
            String username = "Subomi";
            int password = 123456;
            int tracking = 4;
            String username2;
            int password2;


        System.out.println("Your login page");



        while(tracking > 0){

            System.out.print("Enter your username: ");
            username2 = input.nextLine();


            System.out.print("Enter your password: ");
            password2 = Integer.parseInt(input.nextLine());


            if(username2.equals(username) && password2 == password){
                System.out.println("LOGIN SUCCESSFUL!!");
                break;
            }
            else if(tracking == 1){
                System.out.println("Account locked");
                break;

            }
            else{
                tracking--;
                System.out.printf("Wrong password, you have %d attempts left\n",tracking);
            }
}
    input.close();
        }
        

  }
