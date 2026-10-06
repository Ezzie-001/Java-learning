public class printfmeth {
    public static void main(String[] args){
        //printf() is a method used to format output
        // %[flags][width][.precision][specifier-character]
        //% -- placeholder
        //%s == lowercase 's' is used for strings
        //%c -- lowercase 'c' is used for character(single)
        //%d -- lowercase'd' is used for integer
        //%f == lowercase "f" is used for floating or double
        //b -- lowercase "b" is used for boolean

        String name = "Larry";
        char firstLetter = 'S';
        int age = 30;
        double height = 60.5;
        boolean isEmployed = true;

        System.out.printf("Hello %s\n", name);
        System.out.printf("Your name starts with %c\n", firstLetter);
        System.out.printf("Your age is %d years old\n", age);
        System.out.printf("You are %f inches taller\n", height);
        System.out.printf("Employed: %b\n", isEmployed);
        System.out.println();

        //we can also use multiple variables
        System.out.printf("%s is %d years old/n", name, age );
        System.out.println();

        //precision
        //with precision we can limit the amount of digit that display after a decimal but by default it displays 3 digits.
        double price = 9.99;
        double price2 = 100.15;
        double price3 = -54.01;

        System.out.printf("%.2f\n", price);
        System.out.printf("%.2f\n", price2);
        System.out.printf("%.2f\n", price3);

        //FLAGS
        //+ = output a plus
        // - = comma grouping seperator
        //( = negative models are enclosed in parenthesis())
        // space = displaya  minus if negative, space ifnpositive

        double price4 = 9000.99;
        double price5 = 10000.15;
        double price6 = 540001.01;

        System.out.printf("%+.2f\n", price3);
        System.out.printf("%+.2f\n", price2);
        System.out.printf("%+.2f\n", price);

        System.out.printf("%,.2f\n", price4);
        System.out.printf("%,.2f\n", price5);
        System.out.printf("%,.2f\n", price6);

        // by adding a left parenthesis any negative numbers are enclosed in a set of parenthesis)
        System.out.println();

        System.out.printf("%(.2f\n", price);
        System.out.printf("%(.2f\n", price2);
        System.out.printf("%(.2f\n", price3);

        //using a space character any positive numbers are proceeded with a space
        System.out.printf("% .2f\n", price);
        System.out.printf("% .2f\n", price2);
        System.out.printf("% .2f\n", price3);

        //[WIDTH]// 
        // 0 = zero padding
        //number = right justified padding
        //negative number = left justified padding

        int id1 =1;
        int id2 =23;
        int id3 =456;
        int id4 =7890;
        
        System.out.println();

        //by adding a zero after the modulo sign we can zero pad some of these variables
        System.out.printf("%04d\n", id1);
       System.out.printf("%04d\n", id2);
       System.out.printf("%04d\n", id3);
       System.out.printf("%04d\n", id4);

       // by setting a negative number we can left justify these variables
       System.out.printf("%4d\n", id1);
       System.out.printf("%4d\n", id2);
       System.out.printf("%4d\n", id3);
       System.out.printf("%4d\n", id4);
    }
}
