package snippet;

//import java.io.*;
import java.util.*;
//import java.text.*;

public class HospitalManagementSystem {

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public HospitalManagementSystem() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    // Add a patient to the system
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    // Add a doctor to the system
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    // Schedule an appointment for a patient with a doctor
    public void scheduleAppointment(Patient patient, Doctor doctor, String appointmentDate) throws InvalidAppointmentException {
        // Check if the doctor is available on the given date
        boolean isAvailable = true;
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor) && appointment.getAppointmentDate().equals(appointmentDate)) {
                isAvailable = false;
                break;
            }
        }

        if (isAvailable) {
            Appointment appointment = new Appointment(patient, doctor, appointmentDate);
            appointments.add(appointment);
            System.out.println("Appointment scheduled successfully for " + patient.getName() + " with Dr. " + doctor.getName() + " on " + appointmentDate);
        } else {
            throw new InvalidAppointmentException("Doctor is not available on this date.");
        }
    }

    // Display all patients
    public void displayPatients() {
        System.out.println("Patients in the system:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    // Display all doctors
    public void displayDoctors() {
        System.out.println("Doctors in the system:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    // Display all appointments
    public void displayAppointments() {
        System.out.println("Appointments scheduled:");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public static void main(String[] args) {
        // Create the hospital management system
        HospitalManagementSystem system = new HospitalManagementSystem();

        // Add doctors
        Doctor doctor1 = new Doctor("D001", "Dr. Smith", "Cardiology");
        Doctor doctor2 = new Doctor("D002", "Dr. Johnson", "Neurology");

        system.addDoctor(doctor1);
        system.addDoctor(doctor2);

        // Add patients
        Patient patient1 = new Patient("P001", "Alice", "1234567890");
        Patient patient2 = new Patient("P002", "Bob", "0987654321");

        system.addPatient(patient1);
        system.addPatient(patient2);

        // Schedule appointments
        try {
            system.scheduleAppointment(patient1, doctor1, "2025-01-25");
            system.scheduleAppointment(patient2, doctor2, "2025-01-26");
            system.scheduleAppointment(patient1, doctor1, "2025-01-25"); // This will throw an exception (Doctor not available)
        } catch (InvalidAppointmentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Display information
        system.displayPatients();
        system.displayDoctors();
        system.displayAppointments();
    }
}

// Patient class
class Patient {
    private String patientId;
    private String name;
    private String contactNumber;

    public Patient(String patientId, String name, String contactNumber) {
        this.patientId = patientId;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + ", Name: " + name + ", Contact: " + contactNumber;
    }
}

// Doctor class
class Doctor {
    private String doctorId;
    private String name;
    private String specialty;

    public Doctor(String doctorId, String name, String specialty) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialty = specialty;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + doctorId + ", Name: " + name + ", Specialty: " + specialty;
    }
}

// Appointment class
class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate;

    public Appointment(Patient patient, Doctor doctor, String appointmentDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment for " + patient.getName() + " with Dr. " + doctor.getName() + " on " + appointmentDate;
    }
}

// Exception class for invalid appointment
class InvalidAppointmentException extends Exception {
    public InvalidAppointmentException(String message) {
        super(message);
    }
}
