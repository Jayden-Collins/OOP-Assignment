
import java.util.Arrays;

public class Doctordetails extends Staff{
    private String doctorID;
    private String doctorName;
    private String doctorGender;
    private String doctorDepartment;
    private String yearOfExp;
    private String doctorContactNumber;
    private String doctorAddress;
    private Appointment[] appointments = new Appointment[1000];
    private int appointmentCount = 0;


    //non-paramterized constructor
    public Doctordetails(){
        super();
        this.doctorDepartment = "";
        this.yearOfExp = "";
    }

    //parameterized constructor 
    public Doctordetails(String doctorID, String doctorName, String doctorGender, String doctorDepartment, String yearOfExp, String doctorContactNumber, String doctorAddress){
        super(doctorID, doctorName, doctorGender, doctorContactNumber, doctorAddress);
        this.doctorDepartment = doctorDepartment;
        this.yearOfExp = yearOfExp;
    }

    //abstract method link to staff class 
    public String getDetails(){
        return "Doctor";
    }

    // add appointment method 
    public void addAppointment(Appointment appointment){
        if(appointmentCount < appointments.length){
            appointments[appointmentCount++] = appointment;
        } else {
            System.out.println("Doctor Appointments is full.");
        }
    }

    // getter method for appointments array 
    public Appointment[] getAppointments(){
        return Arrays.copyOf(appointments, appointmentCount);
    }

    // set method for doctor department 
    public boolean setDoctorDepartment(String doctorDepartment){
        if(ValidationCheck.validateDoctorDepartment(doctorDepartment)){
            this.doctorDepartment = doctorDepartment;
            return true;
        }
        return false;
    }

    // set method for year of exp
    public void setYearOfExp(String yearOfExp){
        this.yearOfExp = yearOfExp;
    }

    public String getYear(){
        return yearOfExp;
    }

    public String getDoctorDepartment(){
        return doctorDepartment;
    }
    
    public String toString(){
        return super.toString() + String.format("\n Department: %s\nyearofExperience: %s", doctorDepartment, yearOfExp);
    }
}

