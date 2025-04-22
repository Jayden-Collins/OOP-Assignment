public class Department {
    private final String departmentID;
    private String departmentName;

    public Department(String departmentName, String departmentStatus){
        this.departmentID = IdGenerator.generateDepartmentId();
        this.departmentName = departmentName;
    }

    // validation for department name 
    public boolean setDepartmentName(String departmentName){
        if(ValidationCheck.validateName(departmentName)){
            this.departmentName = departmentName;
            return true;
        }
        return false;
    }

    public String getDepartmentID(){
        return departmentID;
    }

    public String getDepartmentName(){
        return departmentName;
    }
}
