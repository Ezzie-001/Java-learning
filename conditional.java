import java.util.Scanner;
public class conditional {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name;
        int age;
        boolean is_student;


        System.out.print("Enter your name: ");
        name = input.nextLine();

        System.out.print("Enter your age: ");
        age = input.nextInt();


        System.out.print("Are you a student? (true/false)");
        is_student = input.nextBoolean();

        if(name.isEmpty()){
            System.out.println("You didn't enter your name!");
        }
        else{
            System.out.println("Hello " + name);
        }
    
        if(age >= 65){
            System.out.println("You are a granny");
        }
else if(age >= 18){
    System.out.println("You are an adult");
}

else if(age>=13){
    System.out.println("You are a teenager");
}
else{
    System.out.println("You are a child");
}
input.close();
    }
    
}

