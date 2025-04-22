import java.util.Collections;
import java.util.List;

public class Patient extends Person{
    private String emergencyContact;
    private final MedicalRecords medicalRecord;
    private List<Appointment> appointments;
    private int appointmentCount = 0;

    //parameterized constructor
    public Patient(String patientIc, String patientName,String patientGender, String patientAddress, String patientPhoneNumber, String emergencyContact){
        super(IdGenerator.generatePatientId(), patientIc, patientName, patientGender, patientPhoneNumber, patientAddress);
        this.emergencyContact = emergencyContact;
        this.medicalRecord = new MedicalRecords(this, null);
    }

    // add appoinment of patients to the appointment class 
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointmentCount++;
    }

    // return for the calling of number of appoints and the count 
    public List<Appointment> getAppointment(){
        return Collections.unmodifiableList(appointments);
    }

    //set emergency contact number and boolean method
    public boolean setPatientEmergencyNumber(String emergencyContact){
        if(emergencyContact.matches("^\\d{3}-\\d{7}$") || emergencyContact.matches("^\\d{3}-\\d{8}$") || emergencyContact.matches("^\\d{10}$") || emergencyContact.matches("^\\d{11}$")){
            this.emergencyContact = emergencyContact;
            return true;
        }
        return false;
    }

    //get emergency contact 
    public String getEmergencyContact(){
        return emergencyContact;
    }

    public String toString(){
        return super.toString() + "\n" +
                "Emergency Contact: " + emergencyContact + "\n" ;
    }
}
