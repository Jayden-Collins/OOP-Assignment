public class Doctordetails {
    private String doctorID;
    private String doctorName;
    private String doctorGender;
    private int doctorContactNumber;
    private String doctorAddress;

    //constructor 
    public Doctordetails(String doctorID, String doctorName, String doctorGender, int doctorContactNumber, String doctorAddress){
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorGender = doctorGender;
        this.doctorContactNumber = doctorContactNumber;
        this.doctorAddress = doctorAddress;
    }

    //set 
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }

    public void setDoctorGender(String doctorGender){
        this.doctorGender = doctorGender;
    }

    public void setDoctorContactNumber(int doctorContactNumber){
        this.doctorContactNumber = doctorContactNumber;
    }

    public void setDoctorAddress(String doctorAddress){
        this.doctorAddress = doctorAddress;
    }

    //get method 
    public String getDoctorName(){
        return doctorName;
    }

    public String getDoctorID(){
        return doctorID;
    }

    public String getDoctorGender(){
        return doctorGender;
    }

    public int getDoctorContactNumber(){
        return doctorContactNumber;
    }

    public String getDoctorAddress(){
        return doctorAddress;
    }
    
    
    
}
