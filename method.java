public class method {
    public static void main(String[] args) {
        // method: a reusable block of code that is executed when called()

        String name = "Larry-Thomzy";
        int age = 21;

        if(ageChecker(age)){
            System.out.println("You may sign up");
        }else{
            System.out.println("You must be up to 18 to sign up");
        }

        happyBirthday(name,age);

        double result = square(5);
        square(5);

        System.out.println(result);
        System.out.println(square(9));
        System.out.println(cube(10));

        String fullName = getFullName("Larry", "Thomzy");
    System.out.println(fullName);

}
static void happyBirthday(String name, int age) {
    System.out.println("Happy Birthday to you!");
    System.out.printf("Happy birthday dear %s!\n", name);
    System.out.printf("You are %d years old!\n", age);
    System.out.println("Happy birthday to you!\n");
}

static double square(double number) {
    return number * number;
}

static double cube (double number){
    return number * number * number;
}

static String getFullName(String first, String last){
    return first + " " + last;
}

static boolean ageChecker(int age){
    if(age >= 18){
        return true;
    } 
    else{
        return false;
        }
    }
    
}
