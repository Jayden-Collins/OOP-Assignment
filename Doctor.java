import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Doctor extends Staff{
    private final List<Appointment> appointments = new ArrayList<>(); // list of appointments for the doctor
    private int appointmentCount = 0;

    // constructor for new doctors
    public Doctor(String doctorIc, String doctorName, String doctorGender, String doctorContactNumber, String doctorAddress, String doctorDepartment, int yearOfExp){
        super(Role.DOCTOR, IdGenerator.generateDoctorId(), doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // constructor for file loading
    public Doctor(String doctorId, String doctorIc, String doctorName, String doctorGender, String doctorContactNumber, String doctorAddress, String doctorDepartment, int yearOfExp){
        super(Role.DOCTOR, doctorId, doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // method to add appointment to the list of appointments
    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointmentCount++;
    }

    // getter method for appointments array 
    public List<Appointment> getAppointments(){
        return Collections.unmodifiableList(appointments);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
