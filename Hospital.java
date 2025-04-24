import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    // username and password for staff and patient
    private static final String STAFF_USERNAME = "Staff";
    private static final String STAFF_PASSWORD = "12345";
    private static final String PATIENT_USERNAME = "Patient";
    private static final String PATIENT_PASSWORD = "123456";

    // file paths 
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";

    private final List<Department> departments = new ArrayList<>(); // list of departments
    private final List<Doctor> doctors = new ArrayList<>(); // list of doctors
    private final List<Nurse> nurses = new ArrayList<>(); // list of nurses
    private final List<Patient> patients = new ArrayList<>(); // list of patients

    // scanner for user input
    Scanner scanner = new Scanner(System.in);
    private String userAccess;

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.combination();
    }

    //clear screen method 
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void makeAppointment()
    {
        System.out.println("Appointment created successfully.");
    }

    //combine access, management 
    public void combination(){
        checkUserAccess();
        Menu menu = new Menu(this);
        
        while(true){
            menu.displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // continue new line 
            menu.choiceSelection(choice);
        }
    }
    
    // user access
    public String UserAccess(){
        return userAccess;
    }

    //check either is patient or staff 
    public void checkUserAccess(){
        clearScreen();
        System.out.println("Hospital Login System.");

        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if(username.equals(STAFF_USERNAME) && password.equals(STAFF_PASSWORD)){
                userAccess = "Staff";
                clearScreen();
                System.out.println("Login Successful as staff.");
                break;
            }
            else if(username.equals(PATIENT_USERNAME) && password.equals(PATIENT_PASSWORD)){
                userAccess = "Patient";
                clearScreen();
                System.out.println("Login Successful as patient.");
                break;
            }
            else{
                clearScreen();
                System.out.println("Invalid username or password.");
            }

        }
    }

    //doctor management system 
    public void doctorManagement(){
        clearScreen();
        System.out.println("Doctor Management");
        System.out.println("1. Add Doctor Information.");
        System.out.println("2. List all doctor");
        System.out.println("3. Search Doctor");
        System.out.print("Choose option: ");

        int selection = scanner.nextInt();
        scanner.nextLine();
        
        switch (selection){
            case 1:
                addDoctorInformation();
                break;
            case 2:
                listdoctor();
                break;
            case 3:
                searchDoctor();
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    // doctor management system sub selection add doctor information
    public void addDoctorInformation(){
        System.out.println("Add Doctor Information");

        // doctor name 
        String doctorName;
        while(true){
            System.out.print("Enter Doctor Name: (Desmond Tan) ");
            doctorName = scanner.nextLine();
            if(ValidationCheck.validateName(doctorName)){
                break;
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }

        // doctor IC
        String doctorIc;
        while(true){
            System.out.print("Enter Doctor IC: (012345-67-1234) ");
            doctorIc = scanner.nextLine();
            if(ValidationCheck.validateIc(doctorIc)){
                break;
            } else{
                System.out.println("\nInvalid IC format. Please re-enter: ");
            }
        }

        //doctor gender 
        String doctorGender;
        while(true){
            System.out.print("Enter Doctor Gender: (Male/Female) ");
            doctorGender = scanner.nextLine();
            if(ValidationCheck.validateGender(doctorGender)){
                break;
            } else{
                System.out.println("\nInvalid Gender format. Please re-enter: ");
            }
        }

        // doctor contact number 
        String doctorContactNumber;
        while(true){
            System.out.println("Enter Doctor Contact Number: (012-3456789/0123456789) ");
            doctorContactNumber = scanner.nextLine();
            if(ValidationCheck.validateNumber(doctorContactNumber)){
                break;
            } else{
                System.out.println("\nInvalid contact number format. Please re-enter: ");
            }
        }

        // doctor address 
        String doctorAddress;
        while(true){
            System.out.println("Enter Doctor Address: (19-09 Taman Rambutan 11900 Bayan Lepas Pulau Pinang) ");
            doctorAddress = scanner.nextLine();
            if(ValidationCheck.validateAddress(doctorAddress)){
                break;
            } else {
                System.out.println("\nInvalid address format. Please re-enter");
            }
        }

        // doctor department
        String doctorDepartment;
        while(true){
            System.out.print("Enter Doctor Department: (Cardiology) ");
            doctorDepartment = scanner.nextLine();
            if(ValidationCheck.validateDoctorDepartment(doctorDepartment)){
                break;
            } else {
                System.out.println("\nInvalid department format. Please re-enter: ");
            }
        }

        //doctor year of experience 
        String doctorYearOfExp;
        while(true){
            System.out.println("Enter Doctor Year of Experience: (12) ");
            doctorYearOfExp = scanner.nextLine();
            if(ValidationCheck.validateYearOfExp(doctorYearOfExp)){
                break;
            } else {
                System.out.println("\nInvalid year of experience format. Please re-enter: ");
            }
        }

        // create new doctor object
        Doctor doctor = new Doctor(doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, Integer.parseInt(doctorYearOfExp));
        doctors.add(doctor);

        // store record in doctor file 
        try(FileWriter fw = new FileWriter(DOCTOR_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(doctor.toFileFormat());
            System.out.println("\nNew doctor information added successfully.");
        } catch (IOException e){
            System.out.println("Error saving doctor information" + e.getMessage());
        }
    }

    // create an array list for the reading file 
    public void readDoctors(){
        // read from the text file 
        try(BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))){
    
            String line;
            while ((line = br.readLine()) != null){
                String[] doctorRecord = line.split("\\|");
                doctors.add(new Doctor(doctorRecord[0], doctorRecord[1], doctorRecord[2], doctorRecord[3], doctorRecord[4], doctorRecord[5], doctorRecord[6], Integer.parseInt(doctorRecord[7])));
            }
        } catch (FileNotFoundException e){
            
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }
    }

    // returns a list of doctors
    public List<Doctor> getDoctors(){
        return doctors;
    }

    // list doctor information 
    public void listdoctor(){
        if(doctors.isEmpty()){
            System.out.println("No doctors is registered yet.");
            return;
        }

        for (Doctor doctor : doctors){
            System.out.println(doctor);
        }
    }

    // search doctor 
    public void searchDoctor(){
        System.out.print("Enter doctor id or name to search: ");
        String searchID_Name = scanner.nextLine();

        boolean exist = false;

        for (Doctor doctor: doctors){
            if(doctor.getId().equals(searchID_Name) || doctor.getName().equalsIgnoreCase(searchID_Name)){
                System.out.println("Found the information");
                System.out.println("ID: " + doctor.getId());
                System.out.println("IC: " + doctor.getIc());
                System.out.println("Name: " + doctor.getName());
                System.out.println("Gender: " + doctor.getGender());
                Systemr.out.println("Contact Number: " + doctor.getContactNumber());
                System.out.println("Address: " + doctor.getAddress());
                System.out.println("Department: " + doctor.getDepartment());
                System.out.println("Year of Experience: " + doctor.getYearOfExp());
                exist = true;
                break;
            }
        }

        if (!exist){
            System.out.println("Information is not found.");
        }
    }

    //delete doctor information 


    //nurse management system
    public void nurseManagement(){
        System.out.println("Nurse Management");
        System.out.println("1. Add Nurse Information");
        System.out.println("2. List all nurse");
        System.out.println("3. Search Nurse");
        System.out.print("Enter choice: : ");

        int selection = scanner.nextInt();
        scanner.nextLine();

        switch(selection){
            case 1:
                addNurseInformation();
                break;
            case 2:
                listNurse();
                break;
            case 3:
                searchNurse();
                break;
            default:
                System.out.println("Invalid selection. Re-enter");
        }
    }

    //add nurse information
    public void addNurseInformation(){
        System.out.println("Add Nurse Information");

        System.out.print("Enter Nurse Name: ");
        String nurseName = scanner.nextLine();

        System.out.print("Enter Nurse IC: ");
        String nurseIC = scanner.nextLine();

        System.out.print("Enter Nurse Gender: ");
        String nurseGender = scanner.nextLine();

        System.out.print("Enter Nurse Contact Number: ");
        String nurseContactNumber = scanner.nextLine();

        System.out.print("Enter Nurse Address: ");
        String nurseAddress = scanner.nextLine();

        // doctor department
        String nurseDepartment;
        while(true){
            System.out.print("Enter Doctor Department: (Cardiology) ");
            nurseDepartment = scanner.nextLine();
            if(ValidationCheck.validateDoctorDepartment(nurseDepartment)){
                break;
            } else {
                System.out.println("\nInvalid department format. Please re-enter: ");
            }
        }

        //doctor year of experience 
        String nurseYearOfExp;
        while(true){
            System.out.println("Enter Doctor Year of Experience: (12) ");
            nurseYearOfExp = scanner.nextLine();
            if(ValidationCheck.validateYearOfExp(nurseYearOfExp)){
                break;
            } else {
                System.out.println("\nInvalid year of experience format. Please re-enter: ");
            }
        }

        // createa a new nurse object
        Nurse nurse = new Nurse(nurseIC, nurseName, nurseGender, nurseContactNumber, nurseAddress, nurseDepartment, Integer.parseInt(nurseYearOfExp));
        nurses.add(nurse);

        // store record in doctor file 
        try(FileWriter fw = new FileWriter(NURSE_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(nurse.toFileFormat());
            System.out.println("\n Nurse Information added successful.");
        } catch (IOException e){
            System.out.println("Error saving nurse information" + e.getMessage());
        }
    }

    // read all nurse information and store it at the array list 
    public void readNurse(){
        try(BufferedReader br = new BufferedReader(new FileReader(NURSE_FILE))){
            
            String line;
            while((line = br.readLine()) != null){
                String[] nurseRecord = line.split("\\|");
                nurses.add(new Nurse(nurseRecord[0], nurseRecord[1], nurseRecord[2], nurseRecord[3], nurseRecord[4], nurseRecord[5], Integer.parseInt(nurseRecord[6])));
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e){
            System.out.println("Error reading nurse data: " + e.getMessage());
        }
    }

    public List<Nurse> getNurses(){
        return nurses;
    }

    // list nurse information 
    public void listNurse(){
        if(nurses.isEmpty()){
            System.out.println("No nurse information is registered yet.");
            return;
        }

        for (Nurse nurse: nurses){
            nurse.toString();
        }
    }

    // search nurse information
    public void searchNurse(){
        System.out.print("Enter nurse id or nurse name to search information: ");
        String searchID_Name = scanner.nextLine();

        boolean exist = false;

        for(Nurse nurse : nurses){
            if(nurse.getId().equals(searchID_Name) || nurse.getName().equals(searchID_Name)){
                System.out.println("Found the information");
                System.out.println(nurse.toString());
                exist = true;
            }
        }

        if(!exist){
            System.out.println("Information is not found");
        }
    }

    //patient managment system
    public void patientManagment(){
        System.out.println("Patient Management");
        System.out.println("1. Add Patient Information.");
        System.out.println("2. List all patient information");
        System.out.println("3. Search Information");
        System.out.println("Enter choice: ");

        int selection = scanner.nextInt();
        scanner.nextLine();

        switch(selection){
            case 1:
                addPatientInformation();
                break;
            case 2:
                listPatient();
                break;
            case 3:
                searchPatient();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please re-enter");
        }
    }

    // add patient information 
    public void addPatientInformation(){
        System.out.println("Add Patient Information");

        System.out.println("Enter Patient IC: ");
        String patientIC = scanner.nextLine();

        System.out.println("Enter Patient Name: ");
        String patientName = scanner.nextLine();

        System.out.println("Enter Patient Gender: ");
        String patientGender = scanner.nextLine();

        System.out.println("Enter Patient Contact Number: ");
        String patientContactNumber = scanner.nextLine();

        System.out.println("Enter Patient Address: ");
        String patientAddress = scanner.nextLine();

        String patientRecord = String.join("|", patientIC, patientName, patientGender, patientContactNumber, patientAddress);

        // write to file 
        try(FileWriter fw = new FileWriter(PATIENT_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(patientRecord);
            System.out.println("Nurse Information saved succesfully");
        } catch (IOException e){
            System.out.println("Error saving nurse information." + e.getMessage());
        }
    }

    // get all patient information 
    public void readPatient(){
        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] patientRecord = line.split("\\|");
                patients.add(new Patient(patientRecord[0], patientRecord[1], patientRecord[2], patientRecord[3], patientRecord[4], patientRecord[5]));
            }
        } catch (FileNotFoundException e){

        } catch (IOException e){
            System.out.println("Error reading patient information."+ e.getMessage());
        }
    }

    // return a list of patients
    public List<Patient> getPatient(){
        return patients;
    }

    // list for all patient
    public void listPatient(){
        if(patients.isEmpty()){
            System.out.println("No patient information is added.");
            return;
        }

        for (Patient patient : patients){
            System.out.println(patient);
        }
    }

    // search for patient 
    public void searchPatient(){
        System.out.print("Enter patient id or name to search information: ");
        String searchID_Name = scanner.nextLine();

        boolean exist = false;

        for (Patient patient : patients){
            if(patient.getId().equals(searchID_Name) || patient.getName().equalsIgnoreCase(searchID_Name)){
                System.out.println("Found the information");
                System.out.println(patient);
                exist = true;
                break;
            }
        }

        if(!exist){
            System.out.println("Information is not found.");
        }
    }
}
