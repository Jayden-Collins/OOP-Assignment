public class NurseDetails extends Staff{
    private String nurseID;
    private String nurseName;
    private String nurseGender;
    private String nurseContactNumber;
    private String nurseAddress;

    //constructor 
    public NurseDetails(String nurseID, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress){
        this.nurseID = nurseID;
        this.nurseName = nurseName;
        this.nurseGender = nurseGender;
        this.nurseContactNumber = nurseContactNumber;
        this.nurseAddress = nurseAddress;
    }

    //abstract method link with staff super class
    public String getDetails(){
        return "Nurse";
    }

    //convert to string 
    public String toString(){
        return String.format("Nurse ID: %s\nName: %s\nGender: %s\nContactNumber: %s\nAddress: %s", nurseID, nurseName, nurseGender, nurseContactNumber, nurseAddress);
    }
        
}
