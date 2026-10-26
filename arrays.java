import java.util.Arrays;
public class arrays {
    public static void main(String[] args){
    //Arrays:A coolection of values of the same data types
    //Think of it as a variable that can store more than one value

        String[] fruits = {"apple", "orange", "banana", "coconut"};
        System.out.println(fruits); //this would return a memory address, because its a reference variable
        System.out.println(fruits[0]); // we would access it through the index/indices

        fruits[0] = "pineapple";
        String [] names = new String[3]; //empty array
        names[0] = "Larry";
        names[1] = "Bolanle";
        names[2] = "Charles";

        int [] nums =new int[5];
        
        System.out.println(fruits[0]);
        int numOfFruits = fruits.length; //the length properties returns the number of values in an array
        System.out.println(numOfFruits);

        System.out.println("=====================");

        for(int i = 0; i< fruits.length; i++){
            System.out.println(fruits[i]);
        }

        System.out.println("=====================");

        Arrays.sort(fruits); //sort is an array that arranges the value order alphabetically
        for (String fruit: fruits) {
            System.out.println(fruit);
        }

        Arrays.fill(fruits, "pineapple"); //fill method replaces all valued in an array with a specific value

        for (String fruit: fruits) {
            System.out.println(fruit);
        }
        
    }
}
