
public class Menu {
    public void display(){
        System.out.println("1. Doctor details");
        System.out.println(("2. Nurse Details"));
        System.out.println("3. Patient Details");
        System.out.println("4. Appointments");
        System.out.println("5. Department list");
        System.out.println("6. Exit");
        System.out.print("Please enter your choice selection: ");
    }    

    public void choice(int choice, Doctordetails doctor, NurseDetails nurse, Patientdetails patient){
        switch(choice){
            case 1:
                displayDoctorDetails(doctor);
                break;
            case 2:
                displayNurseDetails(nurse);
                break;
            case 3:
                displayPatientDetails(patient);
                break;
            case 4:
            case 5:
            case 6:
                System.out.println("Exit Page.");
                System.exit(0);
            default:
                System.out.println("Invalid Choice. Please re-select.");
        }
    }

    // display doctor details in String format 
    public void displayDoctorDetails(Doctordetails doctor){
        System.out.println("Doctor Details");
        System.out.println(doctor.toString());
    }

    // display nurse details in String format 
    public void displayNurseDetails(NurseDetails nurse){
        System.out.println("Nurse Details");
        System.out.println(nurse.toString());
    }

    //display patient details in String format
    public void displayPatientDetails(Patientdetails patient){
        System.out.println("Patient Details");
        System.out.println(patient.toString());
    }
}
