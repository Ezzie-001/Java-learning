import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    String id;
    Patient patient;
    Doctor doctor;
    LocalDateTime dateTime;
    double consultationFee;
    AppointmentStatus status;

    Appointment() {
    }

    Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this(id, patient, doctor, dateTime, doctor.consultationFee);
    }

    Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime, double consultationFee) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.consultationFee = consultationFee;
        this.status = AppointmentStatus.SCHEDULED;
    }

    void printDetails() {
        System.out.printf("%s | %s | Patient: %s (%s) | Doctor: %s (%s, %s) | Fee: NGN %.2f | Status: %s%n",
                id, dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                patient.fullName, patient.id, doctor.fullName, doctor.id,
                doctor.specialty, consultationFee, status);
    }
}
