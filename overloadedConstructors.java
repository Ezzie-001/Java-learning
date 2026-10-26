public class overloadedConstructors {
    public static void main(String[] args) {
        /*
        overloaded constructors = allows class to have multiple constructors with different parameter list
        Enable objects to be initialized in various ways22
        */

        User user1 = new User("Larry");
        User user2 = new User( "Larrythomzygmail.com");
        User user3 = new User("Larry", "Larrythomzygmail.com", 21);
        User user4 = new User();

        System.out.println(user1.username);
        System.out.println(user1.email);
        System.out.println(user1.age);
        System.out.println();



        System.out.println(user2.username);
        System.out.println(user2.email);
        System.out.println(user2.age);
        System.out.println();


        System.out.println(user3.username);
        System.out.println(user3.email);
        System.out.println(user3.age);
        System.out.println();


        System.out.println(user4.username);
        System.out.println(user4.email);
        System.out.println(user4.age);
        System.out.println();

    }
}
