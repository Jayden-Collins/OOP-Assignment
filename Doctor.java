
import java.util.Arrays;

public class Doctor extends Staff{
    private String yearOfExp;
    private Appointment[] appointments = new Appointment[1000];
    private int appointmentCount = 0;

    // constructor for new doctors
    public Doctor(String doctorIc, String doctorName, String doctorGender, String doctorDepartment, String yearOfExp, String doctorContactNumber, String doctorAddress){
        super(IdGenerator.generateDoctorId(), doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment);
        this.yearOfExp = yearOfExp;
    }

    // constructor for file loading
    public Doctor(String doctorId, String doctorIc, String doctorName, String doctorGender, String doctorDepartment, String yearOfExp, String doctorContactNumber, String doctorAddress){
        super(doctorId, doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment);
        this.yearOfExp = yearOfExp;
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

    public String getYearOfExp(){
        return yearOfExp;
    }

    public String toFileFormat(){
        return String.join("|", this.getId(), this.getIc(), this.getName(), this.getGender(), this.getContactNumber(), this.getAddress(), this.getDepartment(), this.getYearOfExp());
    }

    public String toString(){
        return super.toString() + String.format("yearofExperience: %s", yearOfExp);
    }
}

