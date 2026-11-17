public class Receptionist extends Person {
    String department;

    Receptionist() {
    }

    Receptionist(String id, String fullName, String phoneNumber, String department) {
        super(id, fullName, phoneNumber);
        this.department = department;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Phone: %s | Department: %s",
                id, fullName, phoneNumber, department);
    }
}
