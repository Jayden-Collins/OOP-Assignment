import java.time.LocalDate;

public class Appointment {
    private final LocalDate creationDate = LocalDate.now();
    private final String appointmentID = IdGenerator.generateAppointmentId();
    private final Doctor doctor;
    private final Patient patient;
    private LocalDate appointmentDate;
    private String consulationRoom;
    private String appointmentStatus;

    //parameter constructor 
    public Appointment(Doctor doctor, Patient patient, LocalDate appointmentDate, String consultationRoom, String appoinmentStatus){
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.consulationRoom = consultationRoom;
        this.appointmentStatus = appoinmentStatus;

        //link appointment with doctor and patient 
        doctor.addAppointment(this);
        patient.addAppointment(this);
    }

    // setter and boolean for appointmentStatus
    public boolean setAppointmentStatus(String appointmentStatus){
        if(appointmentStatus != null && appointmentStatus.matches("^[a-zA-z ]+$")){
            this.appointmentStatus = appointmentStatus;
            return true;
        }
        return false;
    }

    // change venue
    public void changeVenue(String newRoom){
        this.consulationRoom = newRoom;
    }

    // reschedule appointment
    public void rescheduleAppointment(LocalDate newDate){
        this.appointmentDate = newDate;
    }

    // get creation date
    public LocalDate getCreationDate(){
        return creationDate;
    }
    
    // get mthod for appointment id 
    public String getAppointmentID(){
        return appointmentID;
    }

    // get method for doctor details 
    public Doctor getDoctorDetails(){
        return doctor;
    }

    // get method for patient details 
    public Patient getPatientDetails(){
        return patient;
    }

    // get method for appointment date 
    public LocalDate getAppointmentDate(){
        return appointmentDate;
    }

    // get method for consultation room
    public String getConsultationRoom(){
        return consulationRoom;
    }

    //get method for appointment status
    public String getAppointmentStatus(){
        return appointmentStatus;
    }
}
