import java.util.Scanner;

public class BinaryConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = input.nextLine();
        for (int i = 0; i < word.length(); i++) {
            String binary = Integer.toBinaryString(word.charAt(i));
            System.out.print(String.format("%7s", binary).replace(' ', '0'));
            if (i < word.length() - 1) System.out.print(" - ");
        }
        input.close();
    }
}
