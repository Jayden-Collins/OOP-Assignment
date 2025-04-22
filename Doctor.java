
import java.util.Arrays;

public class Doctor extends Staff{
    private String yearOfExp;
    private Appointment[] appointments = new Appointment[1000];
    private int appointmentCount = 0;

    //parameterized constructor 
    public Doctor(String doctorID, String doctorName, String doctorGender, String doctorDepartment, String yearOfExp, String doctorContactNumber, String doctorAddress){
        super(doctorID, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment);
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

    // set method for year of exp
    public void setYearOfExp(String yearOfExp){
        this.yearOfExp = yearOfExp;
    }

    public String getYear(){
        return yearOfExp;
    }

    public String toString(){
        return super.toString() + String.format("yearofExperience: %s", yearOfExp);
    }
}

