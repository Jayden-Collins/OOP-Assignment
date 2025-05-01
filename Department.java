public class Department {
    private final String DEPARTMENT_ID;
    private String departmentName;
    private static int departmentCount = 0;

    // default constructor for new department creation
    public Department(String departmentName){
        this(IdGenerator.generateDepartmentId(), departmentName);
    }
    
    // constructor for file loading
    public Department(String departmentID, String departmentName){
        this.DEPARTMENT_ID = departmentID;
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

    public String getDepartmentId(){
        return DEPARTMENT_ID;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    // get method for department count
    public static int getDepartmentCount(){
        return departmentCount;
    }

    // increment department count
    public static void incrementDepartmentCount(){
        departmentCount++;
    }

    // set method for department count
    public static void setDepartmentCount(int departmentCount){
        Department.departmentCount = departmentCount;
    }

    public String toFileFormat(){
        return String.join("|", DEPARTMENT_ID, departmentName);
    }

    @Override
    public String toString(){
        return "ID: " + DEPARTMENT_ID + "\n" +
                "Name: " + departmentName;
    }
}
