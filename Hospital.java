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

    // lists for consultation rooms, departments, doctors, nurses, and patients
    private final List<Room> consultationRooms = new ArrayList<>();
    private final List<Department> departments = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Nurse> nurses = new ArrayList<>();
    private final List<Patient> patients = new ArrayList<>();

    // scanner for user input
    Scanner scanner = new Scanner(System.in);
    private String userAccess;
    private Role userRole = null;

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        
        // log in page
        hospital.checkUserAccess();

        // creates a menu object for the hospital object
        Menu menu = new Menu(hospital);
        
        while(true){
            // display staff or patient menu based on user role
            menu.displayMenu();

            // read user input for page selection
            int choice = hospital.scanner.nextInt();
            hospital.scanner.nextLine(); // continue new line 

            // display page based on choice selected
            menu.choiceSelection(choice);
        }
    }

    // set the rooms 
    private void addRooms(){ 
    }

    // clear screen method 
    public void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void makeAppointment()
    {
        System.out.println("Appointment created successfully.");
    }
    
    // user access
    public String getUserAccess(){
        return userAccess;
    }

    // returns user role
    public Role getUserRole(){
        return userRole;
    }

    // returns a list of departments
    public List<Department> getDepartments(){
        return departments;
    }

    // returns a list of rooms
    public List<Room> getRooms(){
        return consultationRooms;
    }

    // returns a list of doctors
    public List<Doctor> getDoctors(){
        return doctors;
    }

    public List<Nurse> getNurses(){
        return nurses;
    }

    // return a list of patients
    public List<Patient> getPatient(){
        return patients;
    }


    // log in page that checks whether the user is a patient or staff 
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
                userRole = Role.STAFF;
                clearScreen();
                System.out.println("Log In Successful!");
                break;
            }
            else if(username.equals(PATIENT_USERNAME) && password.equals(PATIENT_PASSWORD)){
                userAccess = "Patient";
                userRole = Role.PATIENT;
                clearScreen();
                System.out.println("Log In Successful!");
                break;
            }
            else{
                clearScreen();
                System.out.println("Invalid username or password.");
            }

        }
    }


    // doctor management system 
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
                clearScreen();
                addDoctorInformation();
                break;
            case 2:
                clearScreen();
                readDoctors();
                listdoctor();
                break;
            case 3:
                clearScreen();
                searchDoctor();
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    // get person information
    public List<String> getPersonInformation(Role role){
        List<String> personInfo = new ArrayList<>();


        System.out.println("Enter " + role.getRoleName() + " Information: ");

        String name;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Name (e.g. John Smith): ");
            name = scanner.nextLine();
            if(ValidationCheck.validateName(name)){
                personInfo.add(name);
                break;
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }

        String ic;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " IC (e.g. 123456-01-0123): ");
            ic = scanner.nextLine();
            if(ValidationCheck.validateIc(ic)){
                personInfo.add(ic);
                break;
            } else{
                System.out.println("\nInvalid IC format. Please re-enter: ");
            }
        }

        String gender;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Gender (Male/Female): ");
            gender = scanner.nextLine();
            if(ValidationCheck.validateGender(gender)){
                personInfo.add(gender);
                break;
            } else{
                System.out.println("\nInvalid Gender format. Please re-enter: ");
            }
        }
        
        String contactNumber;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Contact Number (012-3456789): ");
            contactNumber = scanner.nextLine();
            if(ValidationCheck.validateNumber(contactNumber)){
                personInfo.add(contactNumber);
                break;
            } else{
                System.out.println("\nInvalid contact number format. Please re-enter: ");
            }
        }

        String address;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Address (3, Western Avenue, 11900, Bayan Lepas, Penang): ");
            address = scanner.nextLine();
            if(ValidationCheck.validateAddress(address)){
                personInfo.add(address);
                break;
            } else {
                System.out.println("\nInvalid address format. Please re-enter: ");
            }
        }

        if (Role.isStaff(role)){
            String department;
            while(true){
                System.out.print("Enter/Select" + role.getRoleName() + " Department (e.g. Cardiology): ");
                department = scanner.nextLine();
                if(ValidationCheck.validateDoctorDepartment(department)){
                    personInfo.add(department);
                    break;
                } else {
                    System.out.println("\nInvalid department format. Please re-enter: ");
                }
            }

            String yearOfExp;
            while(true){
                System.out.print("Enter " + role.getRoleName() + " Year of Experience (e.g. 12): ");
                yearOfExp = scanner.nextLine();
                if(ValidationCheck.validateYearOfExp(yearOfExp)){
                    personInfo.add(yearOfExp);
                    break;
                } else {
                    System.out.println("\nInvalid year of experience format. Please re-enter: ");
                }
            }
        }
        else if (role == Role.PATIENT){
            String emergencyContact;
            while(true){
                System.out.print("Enter " + role.getRoleName() + " Emergency Contact Number (012-3456789): ");
                emergencyContact = scanner.nextLine();
                if(ValidationCheck.validateNumber(emergencyContact)){
                    personInfo.add(emergencyContact);
                    break;
                } else {
                    System.out.println("\nInvalid emergency contact number format. Please re-enter: ");
                }
            }
        }

        // return the list of person information
        return personInfo;
    }
    
    // get staff information

    // doctor management system sub selection add doctor information
    public void addDoctorInformation(){

        // get person information
        List<String> personInfo = getPersonInformation(Role.DOCTOR);
        String doctorName = personInfo.get(0);
        String doctorIc = personInfo.get(1);
        String doctorGender = personInfo.get(2);
        String doctorContactNumber = personInfo.get(3);
        String doctorAddress = personInfo.get(4);
        String doctorDepartment = personInfo.get(5);
        String doctorYearOfExp = personInfo.get(6);

        // create a new doctor object
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
        // clear doctors array
        doctors.clear();

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
                System.out.println(doctor);
                exist = true;
                break;
            }
        }

        if (!exist){
            System.out.println("Information is not found.");
        }
    }

    //delete doctor information 
    public void deleteDoctor(){

    }

    //nurse management system
    public void nurseManagement(){
        System.out.println("Nurse Management");
        System.out.println("1. Add Nurse Information");
        System.out.println("2. List all nurse");
        System.out.println("3. Search Nurse");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter choice: : ");

        int selection = scanner.nextInt();
        scanner.nextLine();

        switch(selection){
            case 1:
                clearScreen();
                addNurseInformation();
                break;
            case 2:
                clearScreen();
                readNurse();
                listNurse();
                break;
            case 3:
                clearScreen();
                searchNurse();
                break;
            default:
                System.out.println("Invalid selection. Re-enter");
        }
    }

    //add nurse information
    public void addNurseInformation(){

        // get person information
        List<String> personInfo = getPersonInformation(Role.NURSE);
        String nurseName = personInfo.get(0);
        String nurseIC = personInfo.get(1);
        String nurseGender = personInfo.get(2);
        String nurseContactNumber = personInfo.get(3);
        String nurseAddress = personInfo.get(4);
        String nurseDepartment = personInfo.get(5);
        String nurseYearOfExp = personInfo.get(6);

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
        // clear nurse list
        nurses.clear();

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
    public void patientManagement(){
        System.out.println("Patient Management");
        System.out.println("1. Add Patient Information.");
        System.out.println("2. List all patient information");
        System.out.println("3. Search Information");
        System.out.println("4. Back to Main Menu");
        System.out.println("Enter choice: ");

        int selection = scanner.nextInt();
        scanner.nextLine();

        switch(selection){
            case 1:
                clearScreen();
                addPatientInformation();
                break;
            case 2:
                clearScreen();
                readPatient();
                listPatient();
                break;
            case 3:
                clearScreen();
                searchPatient();
                break;
            case 4:
                clearScreen();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please re-enter");
        }
    }

    // add patient information 
    public void addPatientInformation(){
        // get person information
        List<String> personInfo = getPersonInformation(Role.PATIENT);
        String patientName = personInfo.get(0);
        String patientIC = personInfo.get(1);
        String patientGender = personInfo.get(2);
        String patientContactNumber = personInfo.get(3);
        String patientAddress = personInfo.get(4);
        String patientEmergencyContact = personInfo.get(5);

        Patient patient = new Patient(patientIC, patientName, patientGender, patientContactNumber, patientAddress, patientEmergencyContact);

        // write to file 
        try(FileWriter fw = new FileWriter(PATIENT_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(patient);
            System.out.println("Nurse Information saved succesfully");
        } catch (IOException e){
            System.out.println("Error saving nurse information." + e.getMessage());
        }
    }

    // get all patient information 
    public void readPatient(){
        // clear patient list
        patients.clear();

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
        String search;

        while (true){
            System.out.println("Search by:");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")){
                while (true){
                    System.out.print("Enter Patient ID: ");
                    String searchID = scanner.nextLine();
                    if (ValidationCheck.validateID(searchID)){
                        search = searchID;
                        break;
                    } else {
                        System.out.println("Invalid ID format. Please re-enter.");
                    }
                }
                break;
            } else if (choice.equals("2")){
                while (true){
                    System.out.print("Enter Patient Name: ");
                    String searchName = scanner.nextLine();
                    if(ValidationCheck.validateName(searchName)){
                        search = searchName;
                        break;
                    } else {
                        System.out.println("Invalid name format. Please re-enter.");
                    }
                }
                break;
            } else {
                System.out.println("Invalid choice. Please re-enter.");
            }
        }
        
        boolean exist = false;

        for (Patient patient : patients){
            if(patient.getId().equals(search) || patient.getName().equalsIgnoreCase(search)){
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

    //patient management page 
}
