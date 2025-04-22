import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Doctor extends Staff{

    private List<Appointment> appointments = new ArrayList<>(); // list of appointments for the doctor
    private int appointmentCount = 0;

    // constructor for new doctors
    public Doctor(String doctorIc, String doctorName, String doctorGender, String doctorDepartment, int yearOfExp, String doctorContactNumber, String doctorAddress){
        super(IdGenerator.generateDoctorId(), doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // constructor for file loading
    public Doctor(String doctorId, String doctorIc, String doctorName, String doctorGender, String doctorDepartment, int yearOfExp, String doctorContactNumber, String doctorAddress){
        super(doctorId, doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // getter method for appointments array 
    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(appointments);
    }

    // convert to string for writing to file
    public String toFileFormat(){
        return String.join("|", this.getId(), this.getIc(), this.getName(), this.getGender(), this.getContactNumber(), this.getAddress(), this.getDepartment(), String.valueOf(this.getYearOfExp()));
    }

    public String toString(){
        return super.toString();
    }
}

