
import java.util.Arrays;

public class Patient extends Person{
    private String emergencyContact;
    private String medicalHistory;
    private Appointment[] appointments = new Appointment[1000];
    private int appointmentCount = 0;

    //parameterized constructor
    public Patient(String patientName,String patientGender, String patientAddress, String patientPhoneNumber, String emergencyContact, String medicalHistory){
        super(IdGenerator.generatePatientId(), patientName, patientGender, patientPhoneNumber, patientAddress);
        this.emergencyContact = emergencyContact;
        this.medicalHistory = medicalHistory;
    }

    // add appoinment of patients to the appointment class 
    public void addAppointment(Appointment appointment){
        if (appointmentCount < appointments.length){
            appointments[appointmentCount++] = appointment;
        } else {
            System.out.println("Appointment for patients is full. ");
        }
    }

    // return for the calling of number of appoints and the count 
    public Appointment[] getAppointment(){
        return Arrays.copyOf(appointments, appointmentCount);
    }

    //set emergency contact number and boolean method
    public boolean setPatientEmergencyNumber(String emergencyContact){
        if(emergencyContact.matches("^\\d{3}-\\d{7}$") || emergencyContact.matches("^\\d{3}-\\d{8}$") || emergencyContact.matches("^\\d{10}$") || emergencyContact.matches("^\\d{11}$")){
            this.emergencyContact = emergencyContact;
            return true;
        }
        return false;
    }

    //set medical history and boolean method 
    public boolean setMedicalHistory(String medicalHistory){
        if(medicalHistory != null && medicalHistory.matches("^[a-zA-Z0-9 ]+$")){
            this.medicalHistory = medicalHistory;
            return true;
        }
        return false;
    }

    //get emergency contact 
    public String getEmergencyContact(){
        return emergencyContact;
    }

    //get medical history
    public String getMedicalHistory(){
        return medicalHistory;
    }

    public String getDetails(){
        return "Patient";
    }

    public String toString(){
        return String.format("Patient ID: %s\nPatient Name: %s\nPatient Gender: %s\nPatient ContactNumber: %s\nEmergency contact number: %s\nAddress: %s", patientID, patientName, patientGender, patientPhoneNumber, emergencyContact, patientAddress);
    }
}
