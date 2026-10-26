

public class fruitStall {

    public static void main(String [] args){
        String [] fruits = {"Mango", "Orange", "Banana"};
        String [] days = {"Mon", "Tue", "Wed"};
        
        
        int [] [] sales = {
            {5, 3, 8},//mango
            {2, 6, 4},//Orange
            {7, 1, 9}//Banana
        };

System.out.println();
            System.out.printf("%-10s", "Fruit");
            for(int c = 0; c < days.length; c++){
                System.out.printf("%5s", days[c]);
            } 
            System.out.printf("%6s", "Total");
            System.out.println(); 


            for(int r = 0; r < fruits.length; r++){ //outer loop
                int fruitTotal = 0; //added this 
                System.out.printf("%-10s", fruits[r]);
                    for(int c = 0; c < sales[r].length; c++){ //inner loop
                        System.out.printf("%5d", sales[r][c]); 
                        fruitTotal += sales[r][c]; //i moved this
                    }
                    System.out.printf("%6d", fruitTotal); // i moved this too 
                    System.out.println();
            }


            
System.out.println();
            //I want to do average sales per day
            System.out.println("Average sales");
            for (int r = 0; r < fruits.length; r++){
                System.out.printf("%-10s", fruits[r]);
            }
            System.out.println();




            for (int c = 0; c < days.length; c++){
                double total = 0;
                System.out.printf("%-10s", days[c]);
                    for (int r =0; r < sales.length; r++){
                         System.out.printf("%5d", sales[r][c]); 
                         total += sales[r][c];
                    } 
                    double average = total / fruits.length;
                    System.out.println(" " + " =  " +  average );
            } 

              System.out.println();
    }
}
//222