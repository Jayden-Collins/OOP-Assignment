public class Staff {
    private String staffID;
    private String staffName;
    private String staffGender;
    private String staffContactNumber;
    private String staffAddress;

    // empty constructor 
    public Staff(){

    }

    // parameter constructor 
    public Staff(String staffID, String staffName, String staffGender, String staffContactNumber, String staffAddress){
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.staffContactNumber = staffContactNumber;
        this.staffAddress = staffAddress;
    }

    public String getDetails(){
        return "Staff";
    }

    //set method and boolean for staff id 
    public boolean setStaffID(String staffID){
        if(ValidationCheck.validateID(staffID)){
            this.staffID = staffID;
            return true;
        }
        return false;
    }

    //set and boolean method for staff name 
    public boolean setStaffName(String staffName){
        if(ValidationCheck.validateName(staffName)){
            this.staffName = staffName;
            return true;
        }
        return false;
    }

    //set and boolean method for staff gender
    public boolean setStaffGender(String staffGender){
        if(ValidationCheck.validateGender(staffGender)){
            this.staffGender = staffGender;
            return true;
        }
        return false;
    }

    //set and boolean method for staff contact number 
    public boolean setStaffContactNumber(String staffContactNumber){
        if(ValidationCheck.validateNumber(staffContactNumber)){
            this.staffContactNumber = staffContactNumber;
        }
        return false;
    }

    // set and boolean method for staff address 
    public boolean setStaffAddress(String staffAddress){
        if(ValidationCheck.validateAddress(staffAddress)){
            this.staffAddress = staffAddress;
            return true;
        }
        return false;
    }

    //get method 
    public String getStaffID(){
        return staffID;
    }

    public String getStaffName(){
        return staffName;
    }

    public String getStaffGender(){
        return staffGender;
    }

    public String getStaffContactNumber(){
        return staffContactNumber;
    }

    public String getStaffAddress(){
        return staffAddress;
    }

    //toString method 
    public String toString(){
        return String.format("ID: %s\nName: %s\nGender: %s\nContactNumber: %s\nAddress: %s\n", staffID, staffName, staffGender, staffContactNumber, staffAddress);
    }


}
