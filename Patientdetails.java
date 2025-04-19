<<<<<<< HEAD

import java.util.Arrays;

public class Patientdetails{
    private String patientID;
    private String patientName;
    private String patientGender;
    private String patientAddress;
    private String patientPhoneNumber;
    private String emergencyContact;
    private String medicalHistory;
    private static int countOfPatient = 0;
    private Appointment[] appointments = new Appointment[1000];
    private int appointmentCount = 0;

    //no parameterized constructor 
    public Patientdetails(){
        this.patientID = "";
        this.patientName = "";
        this.patientGender = "";
        this.patientAddress = "";
        this.patientPhoneNumber = "";
        this.emergencyContact = "";
        this.medicalHistory = "";
    }

    //parameterized constructor
    public Patientdetails(String patientID, String patientName,String patientGender, String patientAddress, String patientPhoneNumber, String emergencyContact, String medicalHistory){
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAddress = patientAddress;
        this.patientPhoneNumber = patientPhoneNumber;
        this.emergencyContact = emergencyContact;
        this.medicalHistory = medicalHistory;
        countOfPatient++;
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

    //boolean setter method 
    public boolean setPatientID(String patientID){
        if(ValidationCheck.validateID(patientID)){
            this.patientID = patientID;
            return true;
        }
        return false;
    }

    //boolean and setter patientname method 
    public boolean setPatientName(String patientName){
        if(ValidationCheck.validateName(patientName)){
            this.patientName = patientName;
            return true;
        }
        return false;
    }

    //boolean and setter patient gender method 
    public boolean setPatientGender(String patientGender){
        if(ValidationCheck.validateGender(patientGender)){
            this.patientGender = patientGender;
            return true;
        }
        return false;
    }

    // set patient address and boolean method 
    public boolean setPatientAddress(String patientAddress){
        if(ValidationCheck.validateAddress(patientAddress)){
            this.patientAddress = patientAddress;
            return true;
        }
        return false;
    }

    //set patient phone number
    public boolean setpatientPhoneNumber(String patientPhoneNumber){
        if(ValidationCheck.validateNumber(patientPhoneNumber)){
            this.patientPhoneNumber = patientPhoneNumber;
            return true;
        }
        return false;
    }

    //set emergency contact number and boolean method
    public boolean setPatientEmergencyNumber(String emergencyContact){
        if(ValidationCheck.validateNumber(emergencyContact)){
            this.emergencyContact = emergencyContact;
            return true;
        }
        return false;
    }

    //set medical history and boolean method 
    public boolean setMedicalHistory(String medicalHistory){
        if(ValidationCheck.validatePatientMedicalHistory(medicalHistory)){
            this.medicalHistory = medicalHistory;
            return true;
        }
        return false;
    }

    //get patientID
    public String getPatientID(){
        return patientID;
    }

    //get patient name 
    public String getPatientName(){
        return patientName;
    }

    //get patient address
    public String getPatientAddress(){
        return patientAddress;
    }

    //get patient phone number 
    public String getPatientPhoneNumber(){
        return patientPhoneNumber;
    }

    //get emergency contact 
    public String getEmergencyContact(){
        return emergencyContact;
    }

    //get medical history
    public String getMedicalHistory(){
        return medicalHistory;
    }


    public String toString(){
        return String.format("Patient ID: %s\nPatient Name: %s\nPatient Gender: %s\nPatient ContactNumber: %s\nEmergency contact number: %s\nAddress: %s", patientID, patientName, patientGender, patientPhoneNumber, emergencyContact, patientAddress);
    }

=======
public class Patientdetails{
    private String patientID;
    private String patientName;
    private String patientAddress;
    private int patientPhoneNumber;
    private int emergencyContact;
    private String medicalHistory;

    //set patientID
    public void setPatientID(String patientID){
        this.patientID = patientID;
    }

    //set patientname 
    public void setPatientName(String patientName){
        this.patientName = patientName;
    }

    // set patient address
    public void setPatientAddress(String patientAddress){
        this.patientAddress = patientAddress;
    }

    //set patient phone number
    public void setpatientPhoneNumber(int patientPhoneNumber){
        this.patientPhoneNumber = patientPhoneNumber;
    }

    //set emergency contact number 
    public void setPatientEmergencyNumber(int emergencyContact){
        this.emergencyContact = emergencyContact;
    }

    //set medical history
    public void setMedicalHistory(String medicalHistory){
        this.medicalHistory = medicalHistory;
    }

    //get patientID
    public String getPatientID(){
        return patientID;
    }

    //get patient name 
    public String getPatientName(){
        return patientName;
    }

    //get patient address
    public String getPatientAddress(){
        return patientAddress;
    }

    //get patient phone number 
    public int getPatientPhoneNumber(){
        return patientPhoneNumber;
    }

    //get emergency contact 
    public int getEmergencyContact(){
        return emergencyContact;
    }

    //get medical history
    public String getMedicalHistory(){
        return medicalHistory;
    }






>>>>>>> d2f7a997248f290bf6632655d628b7444b452543
}