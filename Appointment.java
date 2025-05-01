import java.time.LocalDateTime;

public class Appointment {
    private final LocalDateTime CREATION_DATE_TIME;
    private final String APPOINTMENT_ID;
    private LocalDateTime appointmentDateTime;
    private final Doctor DOCTOR;
    private final Patient PATIENT;
    // get room string method 
    private Room consultationRoom;
    private String appointmentStatus;

    // default constructor 
    public Appointment(LocalDateTime appointmentDateTime, Doctor doctor, Patient patient, Room consultationRoom){
        // calls other constructor
        this(LocalDateTime.now(), IdGenerator.generateAppointmentId(), appointmentDateTime, doctor, patient, consultationRoom, "Scheduled");
    }

    // constructor for file loading
    public Appointment(LocalDateTime creationDateTime, String appointmentId, LocalDateTime appointmentDateTime, Doctor doctor, Patient patient, Room consultationRoom, String appointmentStatus){
        this.CREATION_DATE_TIME = creationDateTime;
        this.APPOINTMENT_ID = appointmentId;
        this.DOCTOR = doctor;
        this.PATIENT = patient;
        this.appointmentDateTime = appointmentDateTime;
        this.consultationRoom = consultationRoom;
        this.appointmentStatus = appointmentStatus;
    }

    //changing room 
    public void changeRoom(Room changeRoom){
        this.consultationRoom.freeRoom();
        this.consultationRoom = changeRoom;
    }

    // set appointment status 
    public void completeAppointment(){
        this.appointmentStatus = "Completed";
        this.consultationRoom.freeRoom();
    }

    // cancel appointment 
    public void cancelAppointment(){
        this.appointmentStatus = "Cancelled";
        this.consultationRoom.freeRoom();
    }

    // setter and boolean for appointmentStatus
    public boolean setAppointmentStatus(String appointmentStatus){
        if(appointmentStatus.equals("Scheduled") || appointmentStatus.equals("Completed") || appointmentStatus.equals("Cancelled")){
            this.appointmentStatus = appointmentStatus;
            return true;
        }
        return false;
    }

    // reschedule appointment
    public void rescheduleAppointment(LocalDateTime newDateTime){
        this.appointmentDateTime = newDateTime;
    }

    // get creation date
    public LocalDateTime getCreationDateTime(){
        return CREATION_DATE_TIME;
    }
    
    // get mthod for appointment id 
    public String getAppointmentID(){
        return APPOINTMENT_ID;
    }

    // get method for appointment date 
    public LocalDateTime getAppointmentDateTime(){
        return appointmentDateTime;
    }

    // get method for consultation room
    public Room getConsultationRoom(){
        return consultationRoom;
    }

    // get method for appointment status
    public String getAppointmentStatus(){
        return appointmentStatus;
    }

    // get method for DOCTOR
    public Doctor getDoctor(){
        return DOCTOR;
    }

    // get method for patient
    public Patient getPatient(){
        return PATIENT;
    }

    // to file format
    public String toFileFormat(){
        return String.join("|", CREATION_DATE_TIME.toString(), APPOINTMENT_ID, appointmentDateTime.toString(), DOCTOR.getId(), PATIENT.getId(), consultationRoom.getRoomID(), appointmentStatus);
    }

    @Override
    public String toString(){
        return String.format("Appointment ID: %s\nDoctor: %s\nPatient: %s\nRoom: %s\nDate and Time: %s", 
            APPOINTMENT_ID, DOCTOR.getName(), PATIENT.getName(), consultationRoom.getLocation(), appointmentDateTime);
    }
}
