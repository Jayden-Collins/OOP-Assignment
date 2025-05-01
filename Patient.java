import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient extends Person{
    private String emergencyContact;
    private final List<MedicalRecords> medicalRecords = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    private int appointmentCount = 0;
    private final MedicalRecords MEDICAL_RECORD;
    private final List<Appointment> APPOINTMENTS = new ArrayList<>();
    private static int patientCount = 0;
    private static final int MAX_APPOINTMENTS = 5; // maximum number of appointments

    // default constructor for new patients
    public Patient(String patientIc, String patientName,String patientGender, String patientAddress, String patientPhoneNumber, String emergencyContact){
        this(IdGenerator.generatePatientId(), patientIc, patientName, patientGender, patientAddress, patientPhoneNumber, emergencyContact);
    }

    // constructor for file loading
    public Patient(String patientId, String patientIc, String patientName,String patientGender, String patientAddress, String patientPhoneNumber, String emergencyContact){
        super(Role.PATIENT, patientId, patientIc, patientName, patientGender, patientPhoneNumber, patientAddress);
        this.emergencyContact = emergencyContact;
        this.MEDICAL_RECORD = new MedicalRecords(this, null);
    }

    // add appoinment of patients to the appointment class 
    public void addAppointment(Appointment appointment){
        APPOINTMENTS.add(appointment);
    }

    // return for the calling of number of appoints and the count 
    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(APPOINTMENTS);
    }

    //set emergency contact number and boolean method
    public boolean setEmergencyContactNumber(String emergencyContact){
        if(emergencyContact.matches("^\\d{3}-\\d{7}$") || emergencyContact.matches("^\\d{3}-\\d{8}$") || emergencyContact.matches("^\\d{10}$") || emergencyContact.matches("^\\d{11}$")){
            this.emergencyContact = emergencyContact;
            return true;
        }
        return false;
    }

    // get emergency contact 
    public String getEmergencyContact(){
        return emergencyContact;
    }

    // get medical record
    public MedicalRecords getMedicalRecord(){
        return MEDICAL_RECORD;
    }

    // get method for patient count
    public static int getPatientCount(){
        return patientCount;
    }

    // increment method for patient count
    public static void incrementPatientCount(){
        patientCount++;
    }

    // set method for patient count
    public static void setPatientCount(int count){
        patientCount = count;
    }

    // to file format for the patient class
    @Override
    public String toFileFormat(){
        return String.join("|", super.toFileFormat(), emergencyContact);
    }

    @Override
    public String toString(){
        return super.toString() +
                "Emergency Contact: " + emergencyContact;
    }
}
