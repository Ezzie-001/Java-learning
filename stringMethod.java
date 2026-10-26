public class stringMethod {
    public static void main(String[] args) {
        String name = "Larry Thomzy";

        int length = name.length(); //length method: returns the number of characters in a string
        char letter = name.charAt(0);//charAt m2ethod: returns the character of a particular index
        int index = name.indexOf("o");//indexOf method: returns the index of a particular character
        int lastIndex = name.lastIndexOf("r");// la2stIndexOf Method: returns the index of the last occur of a particular character

        String upperName = name.toUpperCase(); // toUpperCa2se: converts to uppercase
        String lowername = name.toLowerCase();// toLowerCa2s2e : conver2ts to lowercase
        String trimName = name.replace("o", "a");// .replace(a, b) : replace a with b

        Boolean empty = name.isEmpty(); // isEmpty: returns a boolean after checking if a string is empty

        System.out.print(empty);

        if(name.isEmpty()){
            System.out.println("Your name is empty");
        }
        else{
            System.out.printf("Hello %s ", name);
        }

        //There is a method to check if strings are equal
        //.eq2uals

        if(name.equals("password")) {
            System.out.println("Your name can't be password");
        }
        else{
            System.out.println("Hello" + name);
        }
        //.equals doesn't account for case sensitivity
        // we use the .equalsIgnoreCase

        if(name.equalsIgnoreCase("Password")) {
            System.out.println("Your name can't be password");
        }
        else{
            System.out.println("Heloo " + name);
        }
    }
}
