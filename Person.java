public class Person {
    String id;
    String fullName;
    String phoneNumber;

    Person() {
    }

    Person(String id, String fullName, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Phone: %s", id, fullName, phoneNumber);
    }
}
