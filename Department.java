public class Department {
    private String departmentID;
    private String departmentName;
    private String departmentStatus;

    public Department(String departmentID, String departmentName, String departmentStatus){
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentStatus = departmentStatus;
    }

    //validation for department id 
    public boolean setDepartmentID(String departmentID){
        if(ValidationCheck.validateID(departmentID)){
            this.departmentID = departmentID;
            return true;
        }
        return false;
    }

    // validation for department name 
    public boolean setDepartmentName(String departmentName){
        if(ValidationCheck.validateName(departmentName)){
            this.departmentName = departmentName;
            return true;
        }
        return false;
    }

    // validation for department status 
    public boolean setDepartmentStatus(String departmentStatus){
        if(ValidationCheck.validateAppointmentStatus(departmentStatus)){
            this.departmentStatus = departmentStatus;
            return true;
        }
        return false;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public String getDepartmentID(){
        return departmentID;
    }

    public String getDepartmentStatus(){
        return departmentStatus;
    }


}
