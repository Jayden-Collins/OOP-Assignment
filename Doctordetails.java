<<<<<<< HEAD

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
        this.doctorID = "";
        this.doctorName = "";
        this.doctorGender = "";
        this.doctorDepartment = "";
        this.doctorContactNumber = "";
        this.doctorAddress = "";
    }

    //parameterized constructor 
    public Doctordetails(String doctorID, String doctorName, String doctorGender, String doctorDepartment, String doctorContactNumber, String doctorAddress){
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorGender = doctorGender;
        this.doctorDepartment = doctorDepartment;
        this.doctorContactNumber = doctorContactNumber;
        this.doctorAddress = doctorAddress;
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

    public String getDoctorDepartment(){
        return doctorDepartment;
    }
    
    public String toString(){
        return super.toString() + String.format("\n Department: %s\n ", doctorDepartment);
    }
}

=======
<<<<<<< HEAD
public class Doctordetails {
    private String doctorID;
    private String doctorName;
    private String doctorGender;
    private int doctorContactNumber;
    private String doctorAddress;

    //constructor 
    public Doctordetails(String doctorID, String doctorName, String doctorGender, int doctorContactNumber, String doctorAddress){
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorGender = doctorGender;
        this.doctorContactNumber = doctorContactNumber;
        this.doctorAddress = doctorAddress;
    }

    //set 
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }

    public void setDoctorGender(String doctorGender){
        this.doctorGender = doctorGender;
    }

    public void setDoctorContactNumber(int doctorContactNumber){
        this.doctorContactNumber = doctorContactNumber;
    }

    public void setDoctorAddress(String doctorAddress){
        this.doctorAddress = doctorAddress;
    }

    //get method 
    public String getDoctorName(){
        return doctorName;
    }

    public String getDoctorID(){
        return doctorID;
    }

    public String getDoctorGender(){
        return doctorGender;
    }

    public int getDoctorContactNumber(){
        return doctorContactNumber;
    }

    public String getDoctorAddress(){
        return doctorAddress;
    }
    
    
    
}
=======
public class Doctordetails {
    private String doctorID;
    private String doctorName;
    private String doctorGender;
    private int doctorContactNumber;
    private String doctorAddress;

    //constructor 
    public Doctordetails(String doctorID, String doctorName, String doctorGender, int doctorContactNumber, String doctorAddress){
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.doctorGender = doctorGender;
        this.doctorContactNumber = doctorContactNumber;
        this.doctorAddress = doctorAddress;
    }

    //set 
    public void setDoctorID(String doctorID){
        this.doctorID = doctorID;
    }

    public void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }

    public void setDoctorGender(String doctorGender){
        this.doctorGender = doctorGender;
    }

    public void setDoctorContactNumber(int doctorContactNumber){
        this.doctorContactNumber = doctorContactNumber;
    }

    public void setDoctorAddress(String doctorAddress){
        this.doctorAddress = doctorAddress;
    }

    //get method 
    public String getDoctorName(){
        return doctorName;
    }

    public String getDoctorID(){
        return doctorID;
    }

    public String getDoctorGender(){
        return doctorGender;
    }

    public int getDoctorContactNumber(){
        return doctorContactNumber;
    }

    public String getDoctorAddress(){
        return doctorAddress;
    }
    
    
    
}
>>>>>>> 1f7ec8c51d2b7c48cf8d8cfdd791406fda18806b
>>>>>>> d2f7a997248f290bf6632655d628b7444b452543
