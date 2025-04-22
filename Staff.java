public abstract class Staff extends Person{
    private String department;

    // parameter constructor 
    public Staff(String staffId, String staffIc, String staffName, String staffGender, String staffContactNumber, String staffAddress, String department){
        super(staffId, staffIc, staffName, staffGender, staffContactNumber, staffAddress);
        this.department = department;
    }

    public boolean setDoctorDepartment(String department){
        if(department != null && department.matches("^[a-zA-Z0-9 ]+$")){
            this.department = department;
            return true;
        }
        return false;
    }

    public String getDepartment(){
        return department;
    }

    //abstract method from person class implemented here
    public String getDetails(){
        return "Staff";
    }

    //toString method 
    public String toString(){
        return super.toString() + String.format("\nDepartment: %s", department);
    }
}
