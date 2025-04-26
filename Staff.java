public abstract class Staff extends Person{
    private String department;
    private int yearOfExp;

    // parameter constructor
    public Staff(Role role, String staffId, String staffIc, String staffName, String staffGender, String staffContactNumber, String staffAddress, String department, int yearOfExp){
        super(role, staffId, staffIc, staffName, staffGender, staffContactNumber, staffAddress);

        this.department = department;
        this.yearOfExp = yearOfExp;
    }

    public boolean setDoctorDepartment(String department){
        if(department != null && department.matches("^[a-zA-Z0-9 ]+$")){
            this.department = department;
            return true;
        }
        return false;
    }

    public void setYearOfExp(int yearOfExp){
        this.yearOfExp = yearOfExp;
    }

    public String getDepartment(){
        return department;
    }

    public int getYearOfExp(){
        return yearOfExp;
    }

    // convert to string for writing to file
    @Override
    public String toFileFormat(){
        return String.join("|", super.toFileFormat(), department, String.valueOf(yearOfExp));
    }

    @Override
    //toString method 
    public String toString(){
        return super.toString() + "Department: " + department + "\n" +
                "Year of Experience: " + yearOfExp + "\n";
    }
}
