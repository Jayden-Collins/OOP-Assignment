import java.util.Date;

public class Appointment {
    private String appointmentID;
    private Doctordetails doctor;
    private Patientdetails patient;
    private Date appointmentDate;
    private String consulationRoom;
    private String appointmentStatus;
    private static int appointmentCount = 0;

    //parameter constructor 
    public Appointment(Doctordetails doctor, Patientdetails patient, Date appointmentDate, String consultationRoom, String appoinmentStatus){
        this.appointmentID = IdGenerator.generateAppointmentId();
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.consulationRoom = consultationRoom;
        this.appointmentStatus = appoinmentStatus;

        //link appointment with doctor and patient 
        doctor.addAppointment(this);
        patient.addAppointment(this);
    }

    //setter and boolean method for appointmentID 
    public boolean setAppointmentID(String appointmentID){
        if(ValidationCheck.validateID(appointmentID)){
            this.appointmentID =  appointmentID;
            return true;
        }
        return false;
    }

    //setter and boolena method for consultation room
    public boolean setConsultationRoom(String consultationRoom){
        if(ValidationCheck.validateName(consultationRoom)){
            this.consulationRoom = consultationRoom;
            return true;
        }
        return false;
    }

    //setter and boolean for appointmentStatus
    public boolean setAppointmentStatus(String appointmentStatus){
        if(ValidationCheck.validateAppointmentStatus(appointmentStatus)){
            this.appointmentStatus = appointmentStatus;
            return true;
        }
        return false;
    }

    // get mthod for appointment id 
    public String getAppointmentID(){
        return appointmentID;
    }

    // get method for doctor details 
    public Doctordetails getDoctorDetails(){
        return doctor;
    }

    // get method for patient details 
    public Patientdetails getPatientDetails(){
        return patient;
    }

    // get method for appointment date 
    public Date getAppointmentDate(){
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

    //validation for appointment status 
    public static boolean validateAppointmentStatus(String status){
        return status != null && status.matches("^[a-zA-z ]+$");
    }
}
