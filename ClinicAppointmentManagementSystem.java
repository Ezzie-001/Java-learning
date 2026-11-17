import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ClinicAppointmentManagementSystem {
    static Scanner input = new Scanner(System.in);
    static Patient[] patients = new Patient[100];
    static Doctor[] doctors = new Doctor[100];
    static Appointment[] appointments = new Appointment[300];
    static int patientCount = 0;
    static int doctorCount = 0;
    static int appointmentCount = 0;
    static int nextAppointmentId = 1001;
    static final int SLOT_MINUTES = 30;
    static final String DATA_FILE = "clinic_data.txt";
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    static Receptionist receptionist = new Receptionist("R001", "Ada Okafor", "0803-555-0100", "Front Desk");

    public static void main(String[] args) {
        if (!loadClinicData()) {
            addDemoRecords();
            saveClinicData();
        }
        System.out.println("============================================");
        System.out.println("   CLINIC APPOINTMENT MANAGEMENT SYSTEM");
        System.out.println("============================================");

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> registerDoctor();
                case 3 -> displayPatients();
                case 4 -> displayDoctors();
                case 5 -> searchRecords();
                case 6 -> updateRecord();
                case 7 -> bookAppointment();
                case 8 -> displayScheduledAppointments();
                case 9 -> updateAppointmentStatus();
                case 10 -> displayAppointmentReports();
                case 11 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 11.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register patient");
        System.out.println("2. Register doctor");
        System.out.println("3. Display patients");
        System.out.println("4. Display doctors");
        System.out.println("5. Search patient or doctor");
        System.out.println("6. Update patient or doctor");
        System.out.println("7. Book appointment");
        System.out.println("8. View scheduled appointments");
        System.out.println("9. Complete or cancel appointment");
        System.out.println("10. Clinic report");
        System.out.println("11. Exit");
    }

    static void addDemoRecords() {
        patients[patientCount++] = new Patient("P101", "Mariam Bello", "0802-111-2200", 34, "O+");
        patients[patientCount++] = new Patient("P102", "Tunde Akin", "0805-222-3300", 27, "A-");
        doctors[doctorCount++] = new Doctor("D201", "Dr. Chika Nwosu", "0807-333-4400", "Family Medicine", 7500.00);
        doctors[doctorCount++] = new Doctor("D202", "Dr. Femi Adeyemi", "0809-444-5500", "Paediatrics", 9000.00);
        System.out.println("Fictional demo records loaded. Receptionist: " + receptionist.fullName);
    }

    static void registerPatient() {
        if (patientCount == patients.length) { System.out.println("Patient list is full."); return; }
        System.out.print("Patient ID: ");
        String id = input.nextLine().trim();
        while (id.isEmpty()) {
            System.out.print("Patient ID cannot be empty. Enter it again: ");
            id = input.nextLine().trim();
        }
        if (findPatient(id) != null || findDoctor(id) != null) { System.out.println("That ID is already in use."); return; }
        System.out.print("Full name: ");
        String name = input.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Full name cannot be empty. Enter it again: ");
            name = input.nextLine().trim();
        }
        System.out.print("Phone number: ");
        String phone = input.nextLine().trim();
        while (phone.isEmpty()) {
            System.out.print("Phone number cannot be empty. Enter it again: ");
            phone = input.nextLine().trim();
        }
        System.out.print("Age: ");
        int age = input.nextInt();
        input.nextLine();
        while (age < 0 || age > 130) {
            System.out.print("Enter an age from 0 to 130: ");
            age = input.nextInt();
            input.nextLine();
        }
        System.out.print("Blood group: ");
        String bloodGroup = input.nextLine().trim();
        while (bloodGroup.isEmpty()) {
            System.out.print("Blood group cannot be empty. Enter it again: ");
            bloodGroup = input.nextLine().trim();
        }
        patients[patientCount++] = new Patient(id, name, phone, age, bloodGroup);
        saveClinicData();
        System.out.println("Patient registered.");
        patients[patientCount - 1].printDetails();
        System.out.println();
    }

    static void registerDoctor() {
        if (doctorCount == doctors.length) { System.out.println("Doctor list is full."); return; }
        System.out.print("Doctor ID: ");
        String id = input.nextLine().trim();
        while (id.isEmpty()) {
            System.out.print("Doctor ID cannot be empty. Enter it again: ");
            id = input.nextLine().trim();
        }
        if (findPatient(id) != null || findDoctor(id) != null) { System.out.println("That ID is already in use."); return; }
        System.out.print("Full name: ");
        String name = input.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Full name cannot be empty. Enter it again: ");
            name = input.nextLine().trim();
        }
        System.out.print("Phone number: ");
        String phone = input.nextLine().trim();
        while (phone.isEmpty()) {
            System.out.print("Phone number cannot be empty. Enter it again: ");
            phone = input.nextLine().trim();
        }
        System.out.print("Specialty: ");
        String specialty = input.nextLine().trim();
        while (specialty.isEmpty()) {
            System.out.print("Specialty cannot be empty. Enter it again: ");
            specialty = input.nextLine().trim();
        }
        System.out.print("Consultation fee (NGN): ");
        double fee = input.nextDouble();
        input.nextLine();
        while (fee <= 0) {
            System.out.print("Enter a fee greater than zero: ");
            fee = input.nextDouble();
            input.nextLine();
        }
        doctors[doctorCount++] = new Doctor(id, name, phone, specialty, fee);
        saveClinicData();
        System.out.println("Doctor registered.");
        doctors[doctorCount - 1].printDetails();
        System.out.println();
    }

    static void displayPatients() {
        System.out.println("\n--- Registered Patients (" + patientCount + ") ---");
        if (patientCount == 0) { System.out.println("No patients registered."); return; }
        for (int i = 0; i < patientCount; i++) {
            patients[i].printDetails();
            System.out.println();
        }
    }

    static void displayDoctors() {
        System.out.println("\n--- Registered Doctors (" + doctorCount + ") ---");
        if (doctorCount == 0) { System.out.println("No doctors registered."); return; }
        for (int i = 0; i < doctorCount; i++) {
            doctors[i].printDetails();
            System.out.println();
        }
    }

    static void searchRecords() {
        System.out.print("Enter patient or doctor ID: ");
        String id = input.nextLine().trim();
        Patient patient = findPatient(id);
        Doctor doctor = findDoctor(id);
        if (patient != null) {
            patient.printDetails();
            System.out.println();
        } else if (doctor != null) {
            doctor.printDetails();
            System.out.println();
        } else {
            System.out.println("No patient or doctor found with that ID.");
        }
    }

    static void updateRecord() {
        System.out.print("Enter patient or doctor ID to update: ");
        String id = input.nextLine().trim();
        Patient patient = findPatient(id);
        Doctor doctor = findDoctor(id);
        if (patient == null && doctor == null) {
            System.out.println("No patient or doctor found with that ID.");
            return;
        }

        if (patient != null) {
            System.out.println("Updating patient. Enter the new details.");
            System.out.print("Full name: ");
            patient.fullName = input.nextLine().trim();
            while (patient.fullName.isEmpty()) {
                System.out.print("Full name cannot be empty. Enter it again: ");
                patient.fullName = input.nextLine().trim();
            }
            System.out.print("Phone number: ");
            patient.phoneNumber = input.nextLine().trim();
            while (patient.phoneNumber.isEmpty()) {
                System.out.print("Phone number cannot be empty. Enter it again: ");
                patient.phoneNumber = input.nextLine().trim();
            }
            System.out.print("Age: ");
            patient.age = input.nextInt();
            input.nextLine();
            while (patient.age < 0 || patient.age > 130) {
                System.out.print("Enter an age from 0 to 130: ");
                patient.age = input.nextInt();
                input.nextLine();
            }
            System.out.print("Blood group: ");
            patient.bloodGroup = input.nextLine().trim();
            while (patient.bloodGroup.isEmpty()) {
                System.out.print("Blood group cannot be empty. Enter it again: ");
                patient.bloodGroup = input.nextLine().trim();
            }
            patient.printDetails();
            System.out.println();
        } else {
            System.out.println("Updating doctor. Enter the new details.");
            System.out.print("Full name: ");
            doctor.fullName = input.nextLine().trim();
            while (doctor.fullName.isEmpty()) {
                System.out.print("Full name cannot be empty. Enter it again: ");
                doctor.fullName = input.nextLine().trim();
            }
            System.out.print("Phone number: ");
            doctor.phoneNumber = input.nextLine().trim();
            while (doctor.phoneNumber.isEmpty()) {
                System.out.print("Phone number cannot be empty. Enter it again: ");
                doctor.phoneNumber = input.nextLine().trim();
            }
            System.out.print("Specialty: ");
            doctor.specialty = input.nextLine().trim();
            while (doctor.specialty.isEmpty()) {
                System.out.print("Specialty cannot be empty. Enter it again: ");
                doctor.specialty = input.nextLine().trim();
            }
            System.out.print("Consultation fee (NGN): ");
            doctor.consultationFee = input.nextDouble();
            input.nextLine();
            while (doctor.consultationFee <= 0) {
                System.out.print("Enter a fee greater than zero: ");
                doctor.consultationFee = input.nextDouble();
                input.nextLine();
            }
            doctor.printDetails();
            System.out.println();
        }
        saveClinicData();
        System.out.println("Record updated successfully.");
    }

    static void bookAppointment() {
        if (appointmentCount == appointments.length) { 
        System.out.println("Appointment list is full."); 
        return; 
    }
        if (patientCount == 0 || doctorCount == 0) 
        { System.out.println("Register at least one patient and doctor first."); 
        return; 
    }
        displayPatients();
        System.out.print("Enter patient ID: ");
        String patientId = input.nextLine().trim();
        Patient patient = findPatient(patientId);
        if (patient == null) 
        { System.out.println("Patient not found."); 
        return; 
    }
        displayDoctors();
        System.out.print("Enter doctor ID: ");
        String doctorId = input.nextLine().trim();
        Doctor doctor = findDoctor(doctorId);
        if (doctor == null) { System.out.println("Doctor not found."); return; }
        LocalDateTime dateTime = readAppointmentDateTime();
        if (dateTime == null) return;
        if (hasDoctorConflict(doctor, dateTime)) {
            System.out.println("That doctor already has an appointment overlapping this 30-minute slot.");
            return;
        }
        Appointment appointment = new Appointment("A" + nextAppointmentId++, patient, doctor, dateTime);
        appointments[appointmentCount++] = appointment;
        saveClinicData();
        System.out.println("Appointment booked successfully:");
        appointment.printDetails();
        System.out.printf("Expected consultation charge: NGN %.2f%n", appointment.consultationFee);
    }

    static LocalDateTime readAppointmentDateTime() {
        while (true) {
            System.out.print("Appointment date/time (yyyy-MM-dd HH:mm): ");
            String dateTimeInput = input.nextLine().trim();
            try {
                LocalDateTime value = LocalDateTime.parse(dateTimeInput, DATE_FORMAT);
                if (!value.isAfter(LocalDateTime.now())) {
                    System.out.println("Appointment must be in the future.");
                    continue;
                }
                if (value.getMinute() % SLOT_MINUTES != 0 || value.getSecond() != 0) {
                    System.out.println("Choose a 30-minute boundary, such as 09:00 or 09:30.");
                    continue;
                }
                return value;
            } catch (DateTimeParseException exception) {
                System.out.println("Use the format yyyy-MM-dd HH:mm, for example 2026-12-01 09:30.");
            }
        }
    }

    static boolean hasDoctorConflict(Doctor doctor, LocalDateTime requestedStart) {
        LocalDateTime requestedEnd = requestedStart.plusMinutes(SLOT_MINUTES);
        for (int i = 0; i < appointmentCount; i++) {
            Appointment existing = appointments[i];
            if (existing.doctor == doctor && existing.status == AppointmentStatus.SCHEDULED) {
                LocalDateTime existingStart = existing.dateTime;
                LocalDateTime existingEnd = existingStart.plusMinutes(SLOT_MINUTES);
                if (requestedStart.isBefore(existingEnd) && existingStart.isBefore(requestedEnd)) return true;
            }
        }
        return false;
    }

    static void displayScheduledAppointments() {
        System.out.println("\n--- Scheduled Appointments ---");
        boolean found = false;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].status == AppointmentStatus.SCHEDULED) {
                appointments[i].printDetails();
                found = true;
            }
        }
        if (!found) System.out.println("No scheduled appointments.");
    }

    static void updateAppointmentStatus() {
        if (appointmentCount == 0) { System.out.println("There are no appointments to update."); return; }
        displayScheduledAppointments();
        System.out.print("Appointment ID to update: ");
        String id = input.nextLine().trim();
        Appointment appointment = findAppointment(id);
        if (appointment == null) { System.out.println("Appointment not found."); return; }
        if (appointment.status != AppointmentStatus.SCHEDULED) {
            System.out.println("Only scheduled appointments can be updated."); return;
        }
        System.out.println("1. Mark Completed\n2. Cancel");
        System.out.print("Choose status: ");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            appointment.status = AppointmentStatus.COMPLETED;
            System.out.println("Appointment marked Completed.");
        } else if (choice == 2) {
            appointment.status = AppointmentStatus.CANCELLED;
            System.out.println("Appointment cancelled.");
        } else {
            System.out.println("No status change made.");
            return;
        }
        saveClinicData();
        appointment.printDetails();
    }

    static void displayAppointmentReports() {
        LocalDateTime now = LocalDateTime.now();
        double completedFees = 0;
        System.out.println("\n--- Upcoming Appointments ---");
        boolean upcomingFound = false;
        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.status == AppointmentStatus.SCHEDULED && appointment.dateTime.isAfter(now)) {
                appointment.printDetails();
                upcomingFound = true;
            }
        }
        if (!upcomingFound) System.out.println("No upcoming appointments.");

        System.out.println("\n--- Completed and Cancelled Appointments ---");
        boolean historyFound = false;
        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.status == AppointmentStatus.COMPLETED || appointment.status == AppointmentStatus.CANCELLED) {
                appointment.printDetails();
                historyFound = true;
                if (appointment.status == AppointmentStatus.COMPLETED) completedFees += appointment.consultationFee;
            }
        }
        if (!historyFound) System.out.println("No completed or cancelled appointments.");
        System.out.printf("Total expected fees from completed appointments: NGN %.2f%n", completedFees);
    }

    static Patient findPatient(String id) {
        for (int i = 0; i < patientCount; i++) if (patients[i].id.equalsIgnoreCase(id)) return patients[i];
        return null;
    }

    static Doctor findDoctor(String id) {
        for (int i = 0; i < doctorCount; i++) if (doctors[i].id.equalsIgnoreCase(id)) return doctors[i];
        return null;
    }

    static Appointment findAppointment(String id) {
        for (int i = 0; i < appointmentCount; i++) if (appointments[i].id.equalsIgnoreCase(id)) return appointments[i];
        return null;
    }

    static boolean loadClinicData() {
        boolean loadedAnyRecords = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\t", -1);
                try {
                    if (parts[0].equals("PATIENT") && parts.length == 6 && patientCount < patients.length) {
                        patients[patientCount++] = new Patient(parts[1], parts[2], parts[3],
                                Integer.parseInt(parts[4]), parts[5]);
                        loadedAnyRecords = true;
                    } else if (parts[0].equals("DOCTOR") && parts.length == 6 && doctorCount < doctors.length) {
                        doctors[doctorCount++] = new Doctor(parts[1], parts[2], parts[3], parts[4],
                                Double.parseDouble(parts[5]));
                        loadedAnyRecords = true;
                    } else if (parts[0].equals("APPOINTMENT") && parts.length == 7
                            && appointmentCount < appointments.length) {
                        Patient patient = findPatient(parts[2]);
                        Doctor doctor = findDoctor(parts[3]);
                        if (patient == null || doctor == null) continue;
                        Appointment appointment = new Appointment(parts[1], patient, doctor,
                                LocalDateTime.parse(parts[4]), Double.parseDouble(parts[5]));
                        appointment.status = AppointmentStatus.valueOf(parts[6]);
                        appointments[appointmentCount++] = appointment;
                        updateNextAppointmentId(parts[1]);
                        loadedAnyRecords = true;
                    }
                } catch (IllegalArgumentException exception) {
                    System.out.println("Skipping an invalid record in " + DATA_FILE + ".");
                }
            }
            System.out.println("Clinic records loaded from " + DATA_FILE + ".");
        } catch (FileNotFoundException exception) {
            System.out.println("No saved clinic records found. Starting with fictional demo records.");
        } catch (IOException exception) {
            System.out.println("Could not load clinic records: " + exception.getMessage());
        }
        return loadedAnyRecords;
    }

    static void saveClinicData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (int i = 0; i < patientCount; i++) {
                Patient patient = patients[i];
                writer.write("PATIENT\t" + cleanField(patient.id) + "\t"
                        + cleanField(patient.fullName) + "\t" + cleanField(patient.phoneNumber)
                        + "\t" + patient.age + "\t" + cleanField(patient.bloodGroup));
                writer.newLine();
            }
            for (int i = 0; i < doctorCount; i++) {
                Doctor doctor = doctors[i];
                writer.write("DOCTOR\t" + cleanField(doctor.id) + "\t"
                        + cleanField(doctor.fullName) + "\t" + cleanField(doctor.phoneNumber)
                        + "\t" + cleanField(doctor.specialty) + "\t" + doctor.consultationFee);
                writer.newLine();
            }
            for (int i = 0; i < appointmentCount; i++) {
                Appointment appointment = appointments[i];
                writer.write("APPOINTMENT\t" + cleanField(appointment.id) + "\t"
                        + cleanField(appointment.patient.id) + "\t"
                        + cleanField(appointment.doctor.id) + "\t"
                        + appointment.dateTime + "\t" + appointment.consultationFee
                        + "\t" + appointment.status);
                writer.newLine();
            }
            System.out.println("Clinic records saved to " + DATA_FILE + ".");
        } catch (IOException exception) {
            System.out.println("Could not save clinic records: " + exception.getMessage());
        }
    }

    static String cleanField(String value) {
        return value.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
    }

    static void updateNextAppointmentId(String id) {
        if (id.length() > 1 && id.charAt(0) == 'A') {
            try {
                nextAppointmentId = Math.max(nextAppointmentId, Integer.parseInt(id.substring(1)) + 1);
            } catch (NumberFormatException ignored) { }
        }
    }
}

