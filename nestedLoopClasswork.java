import java.util.Scanner;

public class nestedLoopClasswork {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int rows;
        int columns;

        System.out.print("Enter the number of rows: ");
        rows = Integer.parseInt(input.nextLine());

        

        for(int i = rows; i>= 1; i--) {
            for (int j = 1; j<=12; j++ ){
                System.out.println(i + " * " + j + " = " + i * j );
            }
            System.out.println();
        } 
    }
}
