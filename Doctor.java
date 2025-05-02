import java.util.ArrayList;
import java.util.List;

public class Doctor extends Staff{
    private final List<Appointment> APPOINTMENTS = new ArrayList<>(); // list of appointments for the doctor
    private static int doctorCount = 0; // static variable to keep track of the number of doctors

    // default constructor for new doctors
    public Doctor(String doctorIc, String doctorName, String doctorGender, String doctorContactNumber, String doctorAddress, Department doctorDepartment, int yearOfExp){
        this(IdGenerator.generateDoctorId(), doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // constructor for file loading
    public Doctor(String doctorId, String doctorIc, String doctorName, String doctorGender, String doctorContactNumber, String doctorAddress, Department doctorDepartment, int yearOfExp){
        super(Role.DOCTOR, doctorId, doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, yearOfExp);
    }

    // method to add appointment to the list of appointments
    public void addAppointment(Appointment appointment){
        APPOINTMENTS.add(appointment);
    }

    // get method for doctorCount
    public static int getDoctorCount(){
        return doctorCount;
    }

    // increment method for doctorCount
    public static void incrementDoctorCount(){
        doctorCount++;
    }

    // set method for doctorCount
    public static void setDoctorCount(int count){
        doctorCount = count;
    }

    // getter method for appointments array 
    public List<Appointment> getAppointments(){
        return APPOINTMENTS;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
