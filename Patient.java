public class Patient extends Person {
    int age;
    String bloodGroup;

    Patient() {
    }

    Patient(String id, String fullName, String phoneNumber, int age, String bloodGroup) {
        super(id, fullName, phoneNumber);
        this.age = age;
        this.bloodGroup = bloodGroup;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Phone: %s | Age: %d | Blood group: %s",
                id, fullName, phoneNumber, age, bloodGroup);
    }
}
