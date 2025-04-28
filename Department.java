public class Department {
    private final String departmentID;
    private String departmentName;

    // parameterized constructor
    public Department(String departmentName){
        this.departmentID = IdGenerator.generateDepartmentId();
        this.departmentName = departmentName;
    }
    
    // constructor for file loading
    public Department(String departmentID, String departmentName){
        this.departmentID = departmentID;
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

    public String toFileFormat(){
        return String.join("|", departmentID, departmentName);
    }

    @Override
    public String toString(){
        return "ID: " + departmentID + "\n" +
                "Name: " + departmentName;
    }
}
