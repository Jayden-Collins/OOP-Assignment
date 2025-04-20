public class NurseDetails extends Staff{
    private String nurseID;
    private String nurseName;
    private String nurseGender;
    private String nurseContactNumber;
    private String nurseAddress;

    //constructor 
    public NurseDetails(String nurseID, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress){
        super(nurseID, nurseName, nurseGender, nurseContactNumber, nurseAddress);
    }

    //abstract method link with staff super class
    public String getDetails(){
        return "Nurse";
    }

    //convert to string 
    public String toString(){
        return super.toString();
    }
        
}
