public class Doctor extends Person {
    String specialty;
    double consultationFee;

    Doctor() {
    }

    Doctor(String id, String fullName, String phoneNumber, String specialty, double consultationFee) {
        super(id, fullName, phoneNumber);
        this.specialty = specialty;
        this.consultationFee = consultationFee;
    }

    void printDetails() {
        System.out.printf("ID: %s | Name: %s | Phone: %s | Specialty: %s | Consultation fee: NGN %.2f",
                id, fullName, phoneNumber, specialty, consultationFee);
    }
}
