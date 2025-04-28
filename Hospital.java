import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    
    // scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    private static Role userRole = null;
    private static final List<Department> DEPARTMENTS = new ArrayList<>();
    private static final List<Doctor> DOCTORS = new ArrayList<>();
    private static final List<Nurse> nurses = new ArrayList<>();
    private static final List<Patient> patients = new ArrayList<>();
    private static final List<Room> consultationRooms = new ArrayList<>();

    // constants file paths 
    private static final String DEPARTMENT_FILE = "department.txt";
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";

    public static void main(String[] args) {

        // lists constants for consultation rooms, departments, doctors, nurses, and patients

        // log in page
        while(true){
            clearScreen();
            System.out.println("Hospital Management System");
            System.out.println("--------------------------");
            System.out.println("1. Log In");
            System.out.println("2. Exit");
            System.out.println("--------------------------");
            int choice = getChoice();
            
            // log in page
            if (choice == 1){
                userRole = checkUserAccess();

                // display staff or patient menu based on user role
                if(Role.isStaff(userRole)){
                    while(true){
                        displayStaffMenu();
                        choice = getChoice();
                        
                        // doctor management page
                        if (choice == 1){
                            while(true){
                                doctorManagement();
                                choice = getChoice();
                                
                                // add doctor
                                if (choice == 1){
                                    clearScreen();
                                    Doctor newDoctor = getNewDoctorDetails();
                                    DOCTORS.add(newDoctor);
                                    storeRecord(DOCTOR_FILE, newDoctor.toFileFormat());
                                }

                                // list all doctors
                                else if (choice == 2){
                                    clearScreen();
                                    // clear doctors array
                                    DOCTORS.clear();
                                    DOCTORS.addAll(readDoctors(DOCTOR_FILE));
                                    listdoctor(DOCTORS);
                                }

                                // search for doctor
                                else if (choice == 3){
                                    clearScreen();
                                    DOCTORS.clear();
                                    DOCTORS.addAll(readDoctors(DOCTOR_FILE));
                                    Doctor doctor = searchDoctor(DOCTORS);
                                    if (doctor == null){
                                        System.out.println("Doctor information not found.");
                                    }
                                    else {
                                        System.out.println("Matching record found!");
                                        System.out.println(doctor);
                                    }
                                }

                                // delete doctor record
                                else if (choice == 4){
                                    DOCTORS.clear();
                                    DOCTORS.addAll(readDoctors(DOCTOR_FILE));
                                    Doctor doctor = searchDoctor(DOCTORS);
                                    if(doctor != null){
                                        clearScreen();
                                        System.out.println("Matching Record Found!");
                                        System.out.println("\nDoctor Details:");
                                        System.out.println(doctor);
                                        System.out.println("Delete doctor record?");
                                        if (getYesOrNoInput()){
                                            DOCTORS.remove(doctor);
                                            overwriteFile(DOCTOR_FILE, convertToFileFormat(new ArrayList<>(DOCTORS)));
                                            System.out.println("\nDoctor information deleted successfully.");
                                        } else {
                                            clearScreen();
                                            System.out.println("\nOperation cancelled.");
                                        }
                                    } else {
                                        System.out.println("Doctor information not found.");
                                    }
                                }

                                // back
                                else if (choice == 5){
                                    break;
                                }
                                
                                // invalid choice
                                else {
                                    System.out.println("Invalid selection.");
                                }
                            }

                            if (choice != 5){
                                System.out.print("Press any key to continue.");
                                scanner.nextLine();
                                break;
                            }

                        } // nurse management page
                        else if (choice == 2){
                            while(true){
                                nurseManagement();
                                choice = getChoice();

                                // add nurse
                                if (choice == 1){
                                    clearScreen();
                                    Nurse newNurse = getNewNurseDetails();
                                    nurses.add(newNurse);
                                    storeRecord(NURSE_FILE, newNurse.toFileFormat());
                                }

                                // list all nurses
                                else if (choice == 2){
                                    clearScreen();

                                    // clear nurse array
                                    nurses.clear();
                                    nurses.addAll(readNurse(NURSE_FILE));
                                    listNurse(nurses);
                                }

                                // search patient
                                else if (choice == 3){
                                    clearScreen();
                                    nurses.clear();
                                    nurses.addAll(readNurse(NURSE_FILE));
                                    Nurse nurse = searchNurse(nurses);
                                    if (nurse == null){
                                        System.out.println("Nurse information not found.");
                                    }
                                    else {
                                        System.out.println("Matching record found!");
                                        System.out.println(nurse);
                                    }
                                }

                                // delete nurse record
                                else if (choice == 4){
                                    clearScreen();
                                    nurses.clear();
                                    nurses.addAll(readNurse(NURSE_FILE));
                                    Nurse nurse = searchNurse(nurses);
                                    if(nurse != null){
                                        clearScreen();
                                        System.out.println("Matching Record Found!");
                                        System.out.println("\n Nurse Details:");
                                        System.out.println(nurse);
                                        System.out.println("Delete nurse record?");
                                        if (getYesOrNoInput()){
                                            nurses.remove(nurse);
                                            overwriteFile(NURSE_FILE, convertToFileFormat(new ArrayList<>(nurses)));
                                            System.out.println("\nNurse information deleted successfully.");
                                        } else {
                                            clearScreen();
                                            System.out.println("\nOperation cancelled.");
                                        }
                                    } else {
                                        System.out.println("Nurse information not found.");
                                    }
                                }
                                else if (choice == 5) {
                                    break;
                                } else {
                                    System.out.println("Invalid selection. Re-enter");
                                }
                            
                            }
                            if (choice != 5){
                                System.out.print("Press any key to continue.");
                                scanner.nextLine().trim();
                                break;
                            }
                        } // patient management page
                        else if (choice == 3){
                            while(true){
                                patientManagement();
                                choice = getChoice();

                                // add new patient
                                if (choice == 1){
                                    clearScreen();
                                    Patient newPatient = getNewPatientDetails();
                                    patients.add(newPatient);
                                    storeRecord(PATIENT_FILE, newPatient.toFileFormat());
                                }

                                // list all patient
                                else if (choice == 2){
                                    clearScreen();
                                    patients.clear();
                                    patients.addAll(readPatient(PATIENT_FILE));
                                    listPatient(patients);
                                }
                                
                                // search for patient
                                else if (choice == 3){
                                    clearScreen();
                                    patients.clear();
                                    patients.addAll(readPatient(PATIENT_FILE));
                                    Patient patient = searchPatient(patients);
                                    if (patient == null){
                                        System.out.println("Doctor information not found.");
                                    }
                                    else {
                                        System.out.println("Matching record found!");
                                        System.out.println(patient);
                                    }
                                }

                                // delete patient record
                                else if (choice == 4){
                                    clearScreen();
                                    patients.clear();
                                    patients.addAll(readPatient(PATIENT_FILE));
                                    Patient patient = searchPatient(patients);
                                    if(patient != null){
                                        clearScreen();
                                        System.out.println("Matching Record Found!");
                                        System.out.println("\nPatient Details:");
                                        System.out.println(patient);
                                        System.out.println("Delete patient record?");
                                        if (getYesOrNoInput()){
                                            patients.remove(patient);
                                            overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(patients)));
                                            System.out.println("\nPatient information deleted successfully.");
                                        } else {
                                            clearScreen();
                                            System.out.println("\nOperation cancelled.");
                                        }
                                    } else {
                                        System.out.println("Patient information not found.");
                                    }
                                }

                                // back
                                else if (choice == 5){
                                    clearScreen();
                                    break;
                                }

                                // invalid choice
                                else {
                                    System.out.println("Invalid selection. Please re-enter");
                                }
                            }

                            if (choice != 5){
                                System.out.print("Press any key to continue.");
                                scanner.nextLine().trim();
                                break;
                            }
                        } // department management page
                        else if (choice == 5){
                            while(true){
                                departmentManagement();    
                                choice = getChoice();
                                if (choice == 1){
                                    clearScreen();
                                    Department newDepartment = getNewDepartmentDetails();
                                    DEPARTMENTS.add(newDepartment);
                                    storeRecord(DEPARTMENT_FILE, newDepartment.toFileFormat());
                                }
                                else if (choice == 2) {
                                    clearScreen();
                                    // clear department array
                                    DEPARTMENTS.clear();
                                    DEPARTMENTS.addAll(readDepartments(DEPARTMENT_FILE));
                                    listDepartments(DEPARTMENTS);
                                }
                                else if (choice == 3){
                                    break;
                                }
                                else {
                                    System.out.println("Invalid selection. Please re-enter");
                                }
                            }
                            if (choice != 3){
                                System.out.print("Press any key to continue.");
                                scanner.nextLine();
                                break;
                            }
                        }
                        else if (choice == 6){
                            System.out.println("\nLog Out?\n");
                            if (getYesOrNoInput()){
                                break;
                            }
                            else {
                                System.out.println("Operation cancelled");
                            }
                        } else {
                            System.out.println("Invalid selection.\nPlease re-enter\n");
                        }
                    }
                } else {
                    while (true){
                        displayPatientMenu();
                        choice = getChoice();
                        if (choice == 1){
                            break;
                        } else if (choice == 5){
                            System.out.println("\nLog Out?\n");
                            if (getYesOrNoInput()){
                                break;
                            }
                            else {
                                System.out.println("Operation cancelled.");
                            }
                        } else {
                            System.out.println("Invalid selection. Please re-enter.");
                        }
                    }
                }
            }

            // exit program
            else if (choice == 2){
                clearScreen();
                System.out.println("Program exited.");
                System.exit(0);
            }
            
            // invalid choice input
            else {
                System.out.println("Invalid choice input.\nPlease enter a number.\n");
            }
        }
    }

    // set the rooms 
    private static void addRooms(){
    }

    // get and return choice for menus
    public static int getChoice(){
        while (true){
            try{
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                return choice;
            } catch (Exception e){
                System.out.println("Invalid choice input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    // get yes or no input
    public static boolean getYesOrNoInput(){
        String input;
        while (true){
            System.out.print("Enter Y or N: ");
            input = scanner.nextLine().toUpperCase();
            if(input.equals("Y")){
                return true;
            } else if(input.equals("N")){
                return false;
            } else {
                System.out.println("Invalid input.\nPlease enter Y or N.\n");
            }
        }
    }

    // clear screen method 
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // make appointment method
    public static void makeAppointment()
    {
        System.out.println("Appointment created successfully.");
    }

    // log in page that checks whether the user is a patient or staff 
    public static Role checkUserAccess(){

        // constans for username and password for staff and patient
        final String STAFF_USERNAME = "Staff";
        final String STAFF_PASSWORD = "12345";
        final String PATIENT_USERNAME = "Patient";
        final String PATIENT_PASSWORD = "123456";
        
        clearScreen();
        System.out.println("Hospital Management System");
        System.out.println("---------Log In-----------");

        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Password: ");
            String password = scanner.nextLine().trim();

            if(username.equals(STAFF_USERNAME) && password.equals(STAFF_PASSWORD)){
                clearScreen();
                System.out.println("Log In Successful!");
                return Role.STAFF;
            }
            else if(username.equals(PATIENT_USERNAME) && password.equals(PATIENT_PASSWORD)){
                clearScreen();
                System.out.println("Log In Successful!");
                return Role.PATIENT;
            }
            else{
                clearScreen();
                System.out.println("Invalid username or password.");
            }

        }
    }

    // display staff menu
    public static void displayStaffMenu(){
        Hospital.clearScreen();
        System.out.println("Staff Page");
        System.out.println("----------");
        System.out.println("1. Doctor Management ");
        System.out.println("2. Nurse Management ");
        System.out.println("3. Patient Management");
        System.out.println("4. Generate Medical Report");
        System.out.println("5. Department Management");
        System.out.println("6. Log Out");
        System.out.println("----------");
    }

    // display patient menu
    public static void displayPatientMenu(){
        Hospital.clearScreen();
        System.out.println("Patient Page");
        System.out.println("------------");
        System.out.println("1. View Doctor");
        System.out.println("2. Check own Information.");
        System.out.println("3. Book Appoinments");
        System.out.println("4. View Medical Report");
        System.out.println("5. Log Out");
        System.out.println("------------");
    }

    // get new person information and return as string list
    public static List<String> getPersonInformation(Role role){
        List<String> personInfo = new ArrayList<>();

        System.out.println("Enter " + role.getRoleName() + " Information: ");

        personInfo.add(getPersonName(role));

        String ic;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " IC (e.g. 123456-01-0123): ");
            ic = scanner.nextLine().trim();
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
            gender = scanner.nextLine().trim();
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
            contactNumber = scanner.nextLine().trim();
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
            address = scanner.nextLine().trim();
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
                department = scanner.nextLine().trim();
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
                yearOfExp = scanner.nextLine().trim();
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
                emergencyContact = scanner.nextLine().trim();
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
    
    // get person name
    public static String getPersonName(Role role){
        String name;

        while(true){
            clearScreen();
            System.out.print("Enter " + role.getRoleName() + " Name (e.g. John Smith): ");
            name = scanner.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return name;
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }
    }

    // get staff information
    public static String getPersonId(Role role){
        String id;

        while(true){
            System.out.print("Enter " + role.getRoleName() + " ID (e.g. DC-25-001): ");
            id = scanner.nextLine().trim();
            if(ValidationCheck.validateID(id, role.getRoleName())){
                return id;
            } else {
                System.out.println("\nInvalid ID format. Please re-enter: ");
            }
        }
    }

    // store record in file
    // add role in feedback message
    public static void storeRecord(String file, String record){
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(record);
            System.out.println("\nInformation added successfully.");
        } catch (IOException e){
            System.out.println("Error saving information." + e.getMessage());
        }
    }

    // return list of person as list of string
    public static List<String> convertToFileFormat(List<Person> list){
        return list.stream().map(Person::toFileFormat).toList();
    }

    // overwrite file with new data after modifying data
    public static void overwriteFile(String file, List<String> records){
        try(FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            for (String record : records){
                out.println(record);
            }
        } catch (IOException e){
            System.out.println("Error saving information." + e.getMessage());
        }
    }

    // department management page
    public static void departmentManagement(){
        clearScreen();
        System.out.println("Manage Departments");
        System.out.println("------------------");
        System.out.println("1. Add New Department");
        System.out.println("2. List All Departments");
        System.out.println("3. Back");
        System.out.println("------------------");
    }

    // add department
    public static Department getNewDepartmentDetails(){
        String name;

        while(true){
            System.out.print("Enter New Department Name (e.g. Orthopedics): ");
            name = scanner.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return new Department(name);
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }
    }

    // read departments from file
    public static List<Department> readDepartments(String file){
        List<Department> departmentRecords = new ArrayList<>();

        // read text from line
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] departmentRecord = line.split("\\|");
                departmentRecords.add(new Department(departmentRecord[0], departmentRecord[1]));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }

        return departmentRecords;
    }
    
    // list all departments
    public static void listDepartments(List<Department> departments){
        if(departments.isEmpty()){
            System.out.println("No departments added yet.");
            return;
        }

        for (Department department : departments){
            System.out.println(department);
        }
    }

    // doctor management system 
    public static void doctorManagement(){
        clearScreen();
        System.out.println("Doctor Management");
        System.out.println("-----------------");
        System.out.println("1. Add Doctor Information");
        System.out.println("2. List All Doctors");
        System.out.println("3. Search Doctor");
        System.out.println("4. Delete Doctor Record");
        System.out.println("5. Back");
        System.out.println("-----------------");
    }

    // doctor management system sub selection add doctor information
    public static Doctor getNewDoctorDetails(){

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
        return new Doctor(doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, Integer.parseInt(doctorYearOfExp));
    }

    // create an array list for the reading file 
    public static List<Doctor> readDoctors(String file){
        List<Doctor> doctorRecords = new ArrayList<>();

        // read from the text file
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] doctorRecord = line.split("\\|");
                doctorRecords.add(new Doctor(doctorRecord[0], doctorRecord[1], doctorRecord[2], doctorRecord[3], doctorRecord[4], doctorRecord[5], doctorRecord[6], Integer.parseInt(doctorRecord[7])));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }

        return doctorRecords;
    }

    // list doctor information 
    public static void listdoctor(List<Doctor> doctors){
        if(doctors.isEmpty()){
            System.out.println("No doctors is registered yet.");
            return;
        }

        for (Doctor doctor : doctors){
            System.out.println(doctor);
        }
    }

    // search doctor 
    public static Doctor searchDoctor(List<Doctor> doctors){
        String search;

        while (true){
            clearScreen();
            System.out.println("Search by:");
            System.out.println("----------");
            System.out.println("1. Doctor ID");
            System.out.println("2. Doctor Name");
            System.out.println("----------");

            int choice = getChoice();

            if (choice == 1){
                search = getPersonId(Role.DOCTOR);
                break;
            } else if (choice == 2){
                search = getPersonName(Role.DOCTOR);
                break;
            } else {
                System.out.println("Invalid choice. Please re-enter.");
            }
        }

        for (Doctor doctor: doctors){
            if(doctor.getId().equals(search) || doctor.getName().equalsIgnoreCase(search)){
                return doctor;
            }
        }

        return null;
    }

    //nurse management system
    public static void nurseManagement(){
        clearScreen();
        System.out.println("Nurse Management");
        System.out.println("----------------");
        System.out.println("1. Add Nurse Information");
        System.out.println("2. List all nurse");
        System.out.println("3. Search Nurse");
        System.out.println("4. Delete Nurse Record");
        System.out.println("5. Back");
        System.out.println("----------------");
    }

    //add nurse information
    public static Nurse getNewNurseDetails(){

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
        return new Nurse(nurseIC, nurseName, nurseGender, nurseContactNumber, nurseAddress, nurseDepartment, Integer.parseInt(nurseYearOfExp));
    }

    // read all nurse information and store it at the array list 
    public static List<Nurse> readNurse(String file){
        List<Nurse> nurseRecords = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            
            String line;
            while((line = br.readLine()) != null){
                String[] nurseRecord = line.split("\\|");
                nurseRecords.add(new Nurse(nurseRecord[0], nurseRecord[1], nurseRecord[2], nurseRecord[3], nurseRecord[4], nurseRecord[5], Integer.parseInt(nurseRecord[6])));
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e){
            System.out.println("Error reading nurse data: " + e.getMessage());
        }
        return nurseRecords;
    }

    // list nurse information 
    public static void listNurse(List<Nurse> nurses){
        if(nurses.isEmpty()){
            System.out.println("No nurse information is registered yet.");
            return;
        }

        for (Nurse nurse: nurses){
            nurse.toString();
        }
    }

    // search nurse 
    public static Nurse searchNurse(List<Nurse> nurses){
        String search;

        while (true){
            System.out.println("Search by");
            System.out.println("----------");
            System.out.println("1. Nurse ID");
            System.out.println("2. Nurse Name");
            System.out.println("----------");

            int choice = getChoice();

            if (choice == 1){
                search = getPersonId(Role.NURSE);
                break;
            } else if (choice == 2){
                search = getPersonName(Role.NURSE);
                break;
            } else {
                System.out.println("Invalid choice.\nPlease re-enter.\n");
            }
        }

        for (Nurse nurse: nurses){
            if(nurse.getId().equals(search) || nurse.getName().equalsIgnoreCase(search)){
                return nurse;
            }
        }

        return null;
    }
    
    //patient managment system
    public static void patientManagement(){
        clearScreen();
        System.out.println("Patient Management");
        System.out.println("-----------------");
        System.out.println("1. Register New Patient");
        System.out.println("2. List All Patient Records");
        System.out.println("3. Search Information");
        System.out.println("4. Delete Patient Record");
        System.out.println("5. Back");
        System.out.println("-----------------");
    }

    // add patient information 
    public static Patient getNewPatientDetails(){
        // get person information
        List<String> personInfo = getPersonInformation(Role.PATIENT);
        String patientName = personInfo.get(0);
        String patientIC = personInfo.get(1);
        String patientGender = personInfo.get(2);
        String patientContactNumber = personInfo.get(3);
        String patientAddress = personInfo.get(4);
        String patientEmergencyContact = personInfo.get(5);

        return new Patient(patientIC, patientName, patientGender, patientContactNumber, patientAddress, patientEmergencyContact);
    }

    // get all patient information 
    public static List<Patient> readPatient(String file){
        List<Patient> patientRecords = new ArrayList<>();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] patientRecord = line.split("\\|");
                patientRecords.add(new Patient(patientRecord[0], patientRecord[1], patientRecord[2], patientRecord[3], patientRecord[4], patientRecord[5]));
            }
        } catch (FileNotFoundException e){

        } catch (IOException e){
            System.out.println("Error reading patient information."+ e.getMessage());
        }
        return patientRecords;
    }

    // list for all patient
    public static void listPatient(List<Patient> patients){
        if(patients.isEmpty()){
            System.out.println("No patient information is added.");
            return;
        }

        for (Patient patient : patients){
            System.out.println(patient);
        }
    }

    // search for patient 
    public static Patient searchPatient(List<Patient> patients){
        String search;

        while (true){
            System.out.println("Search by:");
            System.out.println("----------");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.println("----------");

            int choice = getChoice();

            if (choice == 1){
                search = getPersonId(Role.PATIENT);
                break;
            } else if (choice == 2){
                search = getPersonName(Role.PATIENT);
                break;
            } else {
                System.out.println("Invalid choice. Please re-enter.");
            }
        }     

        for (Patient patient : patients){
            if(patient.getId().equals(search) || patient.getName().equalsIgnoreCase(search)){
                return patient;
            }
        }

        return null;
    }

}
