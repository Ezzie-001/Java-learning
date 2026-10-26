import java.util.Scanner;
public class quiz {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /*
        JAVA QUIZ GAME
        QUESTION array []
        OPTIONS array
        DECLARE variables
        WELCOME MESSAGES
        QUESTION (loop)
            OPTIONS
            GET GUESS FROM USER
            CHECK OUR GUESS
        DISPLAY FINAL SCORE
        */
       
       //input.nextLine();

        String[] questions = {
            "What is the main function of a router?",
            "Which part of the computer is considered the brain?",
            "What year was facebook launched?",
            "Who is know as the father of computer? ",
            "What was the first programming language?"
        };


        String [] [] options = {
            {"1. Storing files", "2. Encrypting data", "3. Directing internet traffic", "4. Managing passwords"},
            {"1.CPU", ".2. Hard drive", "3. RAM", "4. GPU"},
            {"1. 2000", "2. 2004", "3. 2006", "4. 2008"},
            {"1. Steve Jobs", "2. Bill Gates", "3. Alan Turing", "4. Charles Ba2bbage"},
            {"1.COBOL", ".2. C", "3. FORTRAN", "4. Assembly"}
        };

        int [] answers = {3,1,2,4,3};
        int guess = 0;
        int score = 0;

        System.out.println("*************************");
        System.out.println("** WELCOME TO JAVA QUIZ **");
        System.out.println("*************************");

        for (int i =0; i < questions.length; i++ ){
            System.out.println(questions[i]);
            for (String  option : options[i]){
            
                    System.out.println(option);
                    
                            
     }
     System.out.print("Enter your answer: ");
                    guess = Integer.parseInt(input.nextLine());

                    if (guess == answers[i]){
                        score++;
                        System.out.println("********");
                        System.out.println("CORRECT");
                        System.out.println("********");
                    }else{
                         System.out.println("********");
                        System.out.println("INCORRECT");
                        System.out.println("********");
                        System.out.println("Answer is " + answers[i]);
                        
                    }
  }

  System.out.println("You got " + score + " out of 5");

        // for(String []  : questions) {
        //     System.out.println(answer);
        //     System.out.println();

        }
    }

