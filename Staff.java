public abstract class Staff extends Person{
    private Department department;
    private int yearOfExp;

    // parameter constructor
    public Staff(Role role, String staffId, String staffIc, String staffName, String staffGender, String staffContactNumber, String staffAddress, Department department, int yearOfExp){
        super(role, staffId, staffIc, staffName, staffGender, staffContactNumber, staffAddress);
        this.department = department;
        this.yearOfExp = yearOfExp;
    }

    public boolean setDoctorDepartment(Department department){
        if(department != null){
            this.department = department;
            return true;
        }
        return false;
    }

    public void setYearOfExp(int yearOfExp){
        this.yearOfExp = yearOfExp;
    }

    public Department getDepartment(){
        return department;
    }

    public int getYearOfExp(){
        return yearOfExp;
    }

    // convert to string for writing to file
    @Override
    public String toFileFormat(){
        return String.join("|", super.toFileFormat(), department.getDepartmentId(), String.valueOf(yearOfExp));
    }

    @Override
    //toString method 
    public String toString(){
        return super.toString() + "Department: " + department.getDepartmentName() + "\n" +
                "Year of Experience: " + yearOfExp;
    }
}
