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






}