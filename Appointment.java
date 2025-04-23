import java.time.LocalDate;

public class Appointment {
    private final LocalDate creationDate = LocalDate.now();
    private final String appointmentID = IdGenerator.generateAppointmentId();
    private final Doctor doctor;
    private final Patient patient;
    private LocalDate appointmentDate;
    // get room string method 
    private Room consulationRoom;
    private String appointmentStatus;

    //parameter constructor 
    public Appointment(Doctor doctor, Patient patient, LocalDate appointmentDate, Room consultationRoom){
        //check the room is it available or not 
        if(!consultationRoom.getAvailable()){
            throw new IllegalStateException("Consultation Room is not available.");
        }
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.consulationRoom = consultationRoom;
        this.appointmentStatus = "Scheduled";

        //book the room
        consultationRoom.bookedRoom(this);

        //link appointment with doctor and patient 
        doctor.addAppointment(this);
        patient.addAppointment(this);
    }

    //changing room 
    public boolean changeRoom(Room changeRoom){
        if(changeRoom.getAvailable()){
            this.consulationRoom.freeRoom();
            this.consulationRoom = changeRoom;
            changeRoom.bookedRoom(this);
            return true;
        }
        return false;
    }

    // set appointment status 
    public void completeAppointment(){
        this.appointmentStatus = "Completed";
        this.consulationRoom.freeRoom();
    }

    // cancel appointment 
    public void cancelAppointment(){
        this.appointmentStatus = "Cancelled";
        this.consulationRoom.freeRoom();
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

    // get method for appointment date 
    public LocalDate getAppointmentDate(){
        return appointmentDate;
    }

    // get method for consultation room
    public Room getConsultationRoom(){
        return consulationRoom;
    }

    //get method for appointment status
    public String getAppointmentStatus(){
        return appointmentStatus;
    }
}
