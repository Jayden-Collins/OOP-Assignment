import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Hospital {
    
    // scanner for user input
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Role userRole = null;
  
    private static final List<Department> DEPARTMENTS = new ArrayList<>();
    private static final List<Doctor> DOCTORS = new ArrayList<>();
    private static final List<Nurse> NURSES = new ArrayList<>();
    private static final List<Patient> PATIENTS = new ArrayList<>();
    private static final List<Room> CONSULTATION_ROOMS = new ArrayList<>();
    private static final List<Appointment> APPOINTMENTS = new ArrayList<>();
    private static final List<Medication> MEDICATIONS = new ArrayList<>();

    // constants file paths 
    private static final String DEPARTMENT_FILE = "department.txt";
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";
    private static final String MEDICAL_RECORD_FILE = "medical_records.txt";
    private static final String ROOM_FILE = "room.txt";
    private static final String APPOINTMENT_FILE = "appointment.txt";
    private static final String MEDICATION_FILE = "medication.txt";

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
                readDepartments();
                readDoctors();
                readNurse();
                readPatient();
                readRooms();
                readMedications();
                readAppointments();

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
                                    // go back
                                    if (newDoctor == null){
                                        continue;
                                    }
                                    System.out.println("\nNew Doctor Details:");
                                    System.out.println("-------------------");
                                    System.out.println(newDoctor);
                                    System.out.println("-------------------");
                                    System.out.println("\nSave new doctor information?");
                                    if (getYesOrNoInput()){
                                        DOCTORS.add(newDoctor);
                                        storeRecord(DOCTOR_FILE, newDoctor.toFileFormat());
                                        System.out.println("\nNew doctor added successfully.");
                                        System.out.print("\nPress any key to return.");
                                        SCANNER.nextLine();
                                        break;
                                    }
                                }

                                // list all doctors
                                else if (choice == 2){
                                    clearScreen();
                                    while(true){
                                        readDepartments();
                                        readDoctors();
                                        listdoctor();
                                        System.out.println("\nPress any key to return.");
                                        SCANNER.nextLine();
                                        break;
                                    }
                                }

                                // search for doctor
                                else if (choice == 3){
                                    clearScreen();
                                    while(true){
                                        readDoctors();
                                        Doctor doctor = searchDoctor();
                                        if (doctor != null){
                                            System.out.println("\nMatching record found!");
                                            System.out.println("-----------------------");
                                            System.out.println(doctor);
                                            System.out.println("-----------------------");
                                            System.out.print("\nPress any key to return.");
                                            SCANNER.nextLine();
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                // delete doctor record
                                else if (choice == 4){
                                    clearScreen();
                                    while(true){
                                        readDoctors();
                                        Doctor doctor = searchDoctor();
                                        if(doctor != null){
                                            clearScreen();
                                            System.out.println("Matching Record Found!");
                                            System.out.println("\nDoctor Details:");
                                            System.out.println("-----------------------");
                                            System.out.println(doctor);
                                            System.out.println("-----------------------");
                                            System.out.println("\nDelete doctor record?");
                                            if (getYesOrNoInput()){
                                                DOCTORS.remove(doctor);
                                                overwriteFile(DOCTOR_FILE, convertToFileFormat(new ArrayList<>(DOCTORS)));
                                                System.out.println("\nDoctor information deleted successfully.");
                                                System.out.print("\nPress any key to return.");
                                                SCANNER.nextLine();
                                                break;
                                            }
                                        }
                                        else {
                                            break;  
                                        }
                                    }
                                }

                                // view doctor appointments
                                else if (choice == 5){
                                    clearScreen();
                                    while(true){
                                        readDoctors();
                                        Doctor doctor = searchDoctor();
                                        if (doctor == null){
                                            break;
                                        } else {
                                            readAppointments();
                                            List<Appointment> doctorAppointments = getDoctorAppointments(doctor);
                                            if (doctorAppointments.isEmpty()){
                                                clearScreen();
                                                System.out.println("No appointments found for this doctor.");
                                            } else {
                                                clearScreen();
                                                System.out.println("Doctor's Appointments:");
                                                System.out.println("-----------------------");
                                                for (int i = 0; i < doctorAppointments.size(); i++){
                                                    System.out.println("Appointment #" + (i + 1));
                                                    System.out.println(doctorAppointments.get(i));
                                                    System.out.println("-----------------------");
                                                }
                                                System.out.println("\nPress any key to return.");
                                                SCANNER.nextLine();
                                                break;
                                            }
                                        }
                                    }
                                }

                                // go back
                                else if (choice == 6){
                                    break;
                                }
                                
                                // invalid choice
                                else {
                                    System.out.println("Invalid selection.");
                                }
                            }
                        }
                        // nurse management page
                        else if (choice == 2){
                            while(true){
                                nurseManagement();
                                choice = getChoice();

                                // add nurse
                                if (choice == 1){
                                    clearScreen();
                                    Nurse newNurse = getNewNurseDetails();
                                    // go back
                                    if (newNurse == null){
                                        continue;
                                    }
                                    System.out.println("\nNew Nurse Details:");
                                    System.out.println("-------------------");
                                    System.out.println(newNurse);
                                    System.out.println("-------------------");
                                    System.out.println("Save new nurse information?");
                                    if (getYesOrNoInput()){
                                        NURSES.add(newNurse);
                                        storeRecord(NURSE_FILE, newNurse.toFileFormat());
                                        System.out.println("\nNew nurse registered successfully.");
                                        System.out.print("\nPress any key to return.");
                                        SCANNER.nextLine();
                                        break;
                                    }
                                }

                                // list all nurses
                                else if (choice == 2){
                                    clearScreen();
                                    while(true){
                                        readNurse();
                                        listNurse();
                                        System.out.print("\nPress any key to return.");
                                        SCANNER.nextLine();
                                        break;
                                    }
                                }

                                // search nurse
                                else if (choice == 3){
                                    clearScreen();
                                    while(true){
                                        readNurse();
                                        Nurse nurse = searchNurse();
                                        if (nurse != null){
                                            System.out.println("\nMatching record found!");
                                            System.out.println("-----------------------");
                                            System.out.println(nurse);
                                            System.out.println("-----------------------");
                                            System.out.print("\nPress any key to return.");
                                            SCANNER.nextLine();
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                // delete nurse record
                                else if (choice == 4){
                                    clearScreen();
                                    while(true){
                                        readNurse();
                                        Nurse nurse = searchNurse();
                                        if(nurse != null){
                                            clearScreen();
                                            System.out.println("Matching Record Found!");
                                            System.out.println("\n-----------------------");
                                            System.out.println("Nurse Details:");
                                            System.out.println(nurse);
                                            System.out.println("-----------------------");
                                            System.out.println("Delete nurse record?");
                                            if (getYesOrNoInput()){
                                                NURSES.remove(nurse);
                                                overwriteFile(NURSE_FILE, convertToFileFormat(new ArrayList<>(NURSES)));
                                                System.out.println("\nNurse information deleted successfully.");
                                                System.out.print("\nPress any key to return.");
                                                SCANNER.nextLine();
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                else if (choice == 5) {
                                    break;
                                } else {
                                    System.out.println("Invalid selection. Re-enter");
                                }
                            
                            }
                        } // patient management page
                        // patient management page
                        else if (choice == 3){
                            while(true){
                                patientManagement();
                                choice = getChoice();

                                // add new patient
                                if (choice == 1){
                                    clearScreen();
                                    Patient newPatient = getNewPatientDetails();
                                    if (newPatient == null){
                                        continue;
                                    } else {
                                    PATIENTS.add(newPatient);
                                    storeRecord(PATIENT_FILE, newPatient.toFileFormat());
                                    System.out.println("\nNew patient registered successfully.");
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                    }
                                }

                                // list all patient
                                else if (choice == 2){
                                    clearScreen();
                                    readPatient();
                                    listPatient();
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                }
                                
                                // search for patient
                                else if (choice == 3){
                                    clearScreen();
                                    while(true){
                                        readPatient();
                                        Patient patient = searchPatient();
                                        if (patient != null){
                                            System.out.println("\nMatching record found!");
                                            System.out.println("-----------------------");
                                            System.out.println(patient);
                                            System.out.println("-----------------------");
                                            System.out.print("\nPress any key to return.");
                                            SCANNER.nextLine();
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                // delete patient record
                                else if (choice == 4){
                                    clearScreen();
                                    while(true){
                                        readPatient();
                                        Patient patient = searchPatient();
                                        if (patient == null){
                                            break;
                                        }
                                        else {
                                            clearScreen();
                                            System.out.println("Matching Record Found!");
                                            System.out.println("\nPatient Details:");
                                            System.out.println("-----------------------");
                                            System.out.println(patient);
                                            System.out.println("-----------------------");
                                            System.out.println("Delete patient record?");
                                            if (getYesOrNoInput()){
                                                PATIENTS.remove(patient);
                                                overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                                System.out.println("\nPatient information deleted successfully.");
                                                System.out.print("\nPress any key to return.");
                                                SCANNER.nextLine();
                                                break;
                                            }
                                        }
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
                        }
                        // medical record management page
                        else if (choice == 4){
                            medicalRecordManagement();
                        }
                        // department management page
                        else if (choice == 5){
                            while(true){
                                departmentManagement();    
                                choice = getChoice();

                                // add department
                                if (choice == 1){
                                    clearScreen();
                                    while(true){
                                        Department newDepartment = getNewDepartmentDetails();
                                        // go back
                                        if (newDepartment == null){
                                            break;
                                        }
                                        System.out.println("\nNew Department Details:");
                                        System.out.println("-------------------");
                                        System.out.println(newDepartment);
                                        System.out.println("-------------------");
                                        System.out.println("\nSave new department information?");
                                        if (getYesOrNoInput()){
                                            DEPARTMENTS.add(newDepartment);
                                            storeRecord(DEPARTMENT_FILE, newDepartment.toFileFormat());
                                            System.out.println("\nNew department registered successfully.");
                                            System.out.println("\nPress any key to return.");
                                            SCANNER.nextLine();
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                }

                                // list all departments
                                else if (choice == 2) {
                                    clearScreen();
                                    while(true){
                                        // clear department array
                                        readDepartments();
                                        listDepartments();
                                        System.out.println("\nPress any key to return.");
                                        SCANNER.nextLine();
                                        break;
                                    }
                                }

                                // back
                                else if (choice == 3){
                                    break;
                                }

                                // invalid selection
                                else {
                                    System.out.println("Invalid selection. Please re-enter");
                                }
                            }
                            if (choice != 3){
                                System.out.print("\nPress any key to return.");
                                SCANNER.nextLine();
                                break;
                            }
                        }
                        else if (choice == 6){
                            clearScreen();
                            while(true){
                                System.out.println("Reports");
                                System.out.println("-------");
                                System.out.println("1. Top 3 Most Experienced Doctors by Department");
                                System.out.println("2. Monthly Appointments");
                                System.out.println("3. Monthly New Patients");
                                System.out.println("4. Most Used medications");
                                System.out.println("5. Back");
                                System.out.println("-------");

                                choice = getChoice();

                                // view all appointments
                                if (choice == 1){
                                    clearScreen();
                                    generateTop3ExperienceDoctorsReport();
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                    clearScreen();
                                }

                                // view all medications
                                else if (choice == 2){
                                    clearScreen();
                                    generateMonthlyAppointmentReport();
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                    clearScreen();
                                }

                                // view new patients
                                else if (choice == 3){
                                    clearScreen();
                                    readPatient();
                                    reportNewPatients();
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                    clearScreen();
                                }

                                // view most used medications
                                else if (choice == 4){
                                    clearScreen();
                                    generateTop5MedicationsReport();
                                    System.out.print("\nPress any key to return.");
                                    SCANNER.nextLine();
                                    clearScreen();
                                }

                                // back
                                else if (choice == 5){
                                    break;
                                }

                                // invalid selection
                                else {
                                    System.out.println("Invalid selection. Please re-enter");
                                }
                            }
                        } else if (choice == 7){
                            clearScreen();
                            System.out.println("Log Out?\n");
                            if (getYesOrNoInput()){
                                break;
                            }
                        } else {
                            System.out.println("Invalid selection.\nPlease re-enter\n");
                        }
                    }
                } else {
                    while (true){
                        displayPatientMenu();
                        choice = getChoice();

                        // view all available doctors by department
                        if (choice == 1){
                            while(true){
                                clearScreen();

                                // prompt user to select department
                                Department department = selectDepartment();

                                // if department is null, user wants to go back
                                if(department == null){
                                    choice = 0;
                                    break;
                                }

                                // list of available doctors in the selected department
                                List<Doctor> availableDoctors = getDoctorInDepartment(department);

                                // if no doctors available, prompt user to select another department
                                if(availableDoctors.isEmpty()){
                                    System.out.println("No doctors available in this department.\nPlease select another department.");
                                    break;
                                }
                                
                                Doctor selectedDoctor;

                                while(true){
                                    
                                    displayDoctorInDepartment(availableDoctors, department);
                                    System.out.println("Select Doctor to View Details.");

                                    choice = getChoice();
                                    if (choice >= 1 && choice <= availableDoctors.size()){
                                        selectedDoctor = availableDoctors.get(choice - 1);
                                        clearScreen();
                                        System.out.println("Doctor Name: " + selectedDoctor.getName());
                                        System.out.println("Department: " + selectedDoctor.getDepartment().getDepartmentName());
                                        System.out.println("Years of Experience: " + selectedDoctor.getYearOfExp() + " years");
                                        System.out.println("\nPress any key to go back.");
                                        SCANNER.nextLine();
                                    } else if (choice == availableDoctors.size() + 1){
                                        break;
                                    } else {
                                        System.out.println("Invalid selection. Please re-enter.");
                                    }
                                }
                            }
                        }

                        // check own information
                        else if (choice == 2){
                            clearScreen();
                            readPatient();
                            while(true){
                                Patient patient = searchPatient();

                                // if patient is null, user wants to go back
                                if (patient == null){
                                    break;
                                }

                                else {
                                    clearScreen();
                                    System.out.println("Matching record found!");
                                    System.out.println("------------------------");
                                    System.out.println(patient);
                                    System.out.println("------------------------");
                                    System.out.println("1. Update Personal Information");
                                    System.out.println("2. Back");
                                    System.out.println("------------------------");

                                    choice = getChoice();

                                    // update personal information
                                    if (choice == 1){
                                        clearScreen();
                                        while(true){
                                            System.out.println("Update Personal Information");
                                            System.out.println("----------------------------");
                                            System.out.println("1. Update Name");
                                            System.out.println("2. Update Contact Number");
                                            System.out.println("3. Update Address");
                                            System.out.println("4. Update Emergency Contact Number");
                                            System.out.println("5. Back");
                                            System.out.println("----------------------------");
                                            choice = getChoice();

                                            if (choice == 1){
                                                System.out.print("Enter new name: ");
                                                patient.setName(getName());
                                                overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                                clearScreen();
                                                System.out.println("\nName updated successfully.");
                                            } else if (choice == 2){
                                                System.out.print("Enter new contact number: ");
                                                patient.setContactNumber(getContactNumber());
                                                overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                                clearScreen();
                                                System.out.println("\nContact number updated successfully.");
                                            } else if (choice == 3){
                                                System.out.print("Enter new address: ");
                                                patient.setAddress(getAddress());
                                                overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                                clearScreen();
                                                System.out.println("\nAddress updated successfully.");
                                            } else if (choice == 4){
                                                System.out.print("Enter new emergency contact number: ");
                                                patient.setEmergencyContactNumber(getContactNumber());
                                                overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                                clearScreen();
                                                System.out.println("\nEmergency contact number updated successfully.");
                                            } else if (choice == 5){
                                                break;
                                            } else {
                                                clearScreen();
                                                System.out.println("Invalid selection. Please re-enter");
                                            }
                                        }
                                    }
                                    // go back
                                    else if (choice == 2){
                                        break;
                                    } else {
                                        System.out.println("Invalid selection. Please re-enter");
                                    }
                                }
                            }
                        }
                        
                        // book appointment
                        else if (choice == 3){
                            clearScreen();
                            bookAppointment();
                        }

                        // view medical report
                        else if (choice == 4){
                            clearScreen();
                            viewMedicalRecord();
                        }
                        
                        // view appointments
                        else if (choice == 5){
                            clearScreen();
                            readAppointments();
                            while(true){
                                Patient patient = searchPatient();
                                if (patient == null){
                                    break;
                                } else {
                                    List<Appointment> appointments = getPatientAppointments(patient);
                                    if (appointments.isEmpty()){
                                        clearScreen();
                                        System.out.println("No appointments found for this doctor.");
                                    } else {
                                        clearScreen();
                                        System.out.println("Patient's Appointments:\n\n");
                                        for (int i = 0; i < appointments.size(); i++){
                                            System.out.println("Appointment #" + (i + 1));
                                            System.out.println("-----------------------");
                                            System.out.println(appointments.get(i));
                                            System.out.println("-----------------------");
                                        }
                                        System.out.println("\nPress any key to return.");
                                        SCANNER.nextLine();
                                    }
                                }
                            }
                        }

                        // log out
                        else if (choice == 6){
                            System.out.println("\nLog Out?\n");
                            if (getYesOrNoInput()){
                                break;
                            }
                        }
                        
                        // invalid selection
                        else {
                            System.out.println("Invalid selection. Please re-enter.");
                        }
                    }
                }
            }

            // exit program
            else if (choice == 2){
                clearScreen();
                System.out.println("Program closed successfully.");
                System.exit(0);
            }
            
            // invalid choice input
            else {
                System.out.println("Invalid choice input.\nPlease enter a number.\n\n");
            }
        }
    }

    // get and return choice for menus
    // modify to become get integer method
    private static int getChoice(){
        while (true){
            try{
                System.out.print("Enter choice: ");
                int choice = SCANNER.nextInt();
                SCANNER.nextLine(); // consume the newline character
                return choice;
            } catch (Exception e){
                System.out.println("Invalid choice input. Please enter a number.");
                SCANNER.nextLine(); // clear the invalid input
            }
        }
    }

    // get yes or no input
    private static boolean getYesOrNoInput(){
        String input;
        while (true){
            System.out.print("Enter Y or N: ");
            input = SCANNER.nextLine().toUpperCase();
            if(input.equals("Y")){
                return true;
            } else if(input.equals("N")){
                clearScreen();
                System.out.println("Operation cancelled.");
                return false;
            } else {
                System.out.println("Invalid input.\nPlease enter Y or N.\n");
            }
        }
    }

    //clear screen method 
    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // log in page that checks whether the user is a patient or staff 
    private static Role checkUserAccess(){

        // constans for username and password for staff and patient
        final String STAFF_USERNAME = "Staff";
        final String STAFF_PASSWORD = "12345";
        final String PATIENT_USERNAME = "Patient";
        final String PATIENT_PASSWORD = "123456";
        
        clearScreen();

        while(true){
            System.out.println("Hospital Management System");
            System.out.println("---------Log In-----------");
            System.out.print("Username: ");
            String username = SCANNER.nextLine().trim();

            System.out.print("Password: ");
            String password = SCANNER.nextLine().trim();

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

    // patient access page 
    private static void patientPage(){
        clearScreen();
        System.out.println("Hi");
    }

    // display staff menu
    private static void displayStaffMenu(){
        clearScreen();
        System.out.println("Staff Page");
        System.out.println("----------");
        System.out.println("1. Doctor Management ");
        System.out.println("2. Nurse Management ");
        System.out.println("3. Patient Management");
        System.out.println("4. Medical Record Management");
        System.out.println("5. Department Management");
        System.out.println("6. Reports");
        System.out.println("7. Log Out");
        System.out.println("----------");
    }

    // display patient menu
    private static void displayPatientMenu(){
        clearScreen();
        System.out.println("Patient Page");
        System.out.println("------------");
        System.out.println("1. View Available Doctors");
        System.out.println("2. View and Manage Personal Details");
        System.out.println("3. Book Appoinment");
        System.out.println("4. View Medical Report");
        System.out.println("5. View Appointments");
        System.out.println("6. Log Out");
        System.out.println("------------");
    }

    // get staff information
    private static String getPersonId(Role role){
        while(true){
            System.out.print("Enter " + role.getRoleName() + " ID (e.g. " + role.getRoleIdExample() + " or -1 to return): ");
            String id = SCANNER.nextLine().trim();
            if(ValidationCheck.validateID(id, role.getRoleName())){
                return id;
            } else if(id.equals("-1")){
                return "-1";
            } else {
                System.out.println("\nInvalid ID format. Please re-enter: ");
            }
        }
    }

    // get name
    private static String getName(){
        while(true){
            String name = SCANNER.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return name;
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }
    }

    // get contact number
    private static String getContactNumber(){
        while(true){
            String contactNumber = SCANNER.nextLine().trim();
            if(ValidationCheck.validateNumber(contactNumber)){
                return contactNumber;
            } else{
                System.out.println("\nInvalid contact number format. Please re-enter: ");
            }
        }
    }

    // get emergency contact number

    // get address
    private static String getAddress(){
        while(true){
            String address = SCANNER.nextLine().trim();
            if(ValidationCheck.validateAddress(address)){
                return address;
            } else {
                System.out.println("\nInvalid address format. Please re-enter: ");
            }
        }
    }
    
    // get person name
    private static String getPersonName(Role role){
        while(true){
            clearScreen();
            System.out.print("Enter " + role.getRoleName() + " Name (e.g. John Smith or -1 to return): ");
            String name = SCANNER.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return name;
            } else if (name.equals("-1")){
                return "-1";
            } else {
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }
    }

    // get ic
    private static String getIc(){
        while(true){
            String ic = SCANNER.nextLine().trim();
            if(ValidationCheck.validateIc(ic)){
                return ic;
            } else if (ic.equals("-1")){
                return "-1";
            } else {
                return "";
            }
        }
    }

    // get person ic
    private static String getPersonIc(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " IC (e.g. 123456-01-0123 or -1 to return): ");
            String ic = getIc();
            if (ValidationCheck.validateIc(ic)){
                return ic;
            } else if (ic.equals("-1")){
                return "-1";
            } else {
                clearScreen();
                System.out.println("\nInvalid IC format. Please re-enter: ");
            }
        }
    }

    // get person gender
    private static String getPersonGender(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Gender (Male/Female): ");
            String gender = SCANNER.nextLine().trim();
            //capitalize the first letter of all input and lowercase the rest
            gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
            System.out.println(gender);
            if(ValidationCheck.validateGender(gender)){
                return gender;
            } else{
                clearScreen();
                System.out.println("Invalid Gender format. Please re-enter: ");
            }
        }
    }
    
    // get person contact number
    private static String getPersonContactNumber(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Contact Number (012-3456789): ");
            String contactNumber = SCANNER.nextLine().trim();
            if(ValidationCheck.validateNumber(contactNumber)){
                return contactNumber;
            } else{
                clearScreen();
                System.out.println("\nInvalid contact number format. Please re-enter: ");
            }
        }
    }

    // get person contact number
    private static String getPersonEmergencyContactNumber(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Emergency Contact Number (012-3456789): ");
            String contactNumber = SCANNER.nextLine().trim();
            if(ValidationCheck.validateNumber(contactNumber)){
                return contactNumber;
            } else{
                clearScreen();
                System.out.println("\nInvalid contact number format. Please re-enter: ");
            }
        }
    }

    // get person address
    private static String getPersonAddress(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Address (3, Western Avenue, 11900, Bayan Lepas, Penang): ");
            String address = SCANNER.nextLine().trim();
            if(ValidationCheck.validateAddress(address)){
                return address;
            } else {
                clearScreen();
                System.out.println("\nInvalid address format. Please re-enter");
            }
        }
    }

    // select department
    private static Department selectDepartment(){
        readDepartments();
        System.out.println("Select Department");
        System.out.println("-----------------");

        // display the name of all departments 
        for (int i = 0; i < DEPARTMENTS.size(); i++){
            System.out.println((i + 1) + ". " + DEPARTMENTS.get(i).getDepartmentName());
        }

        System.out.println(DEPARTMENTS.size() + 1 + ". Back");
        System.out.println("-----------------");

        while(true){
            int choice = getChoice();
            if (choice >= 1 && choice <= DEPARTMENTS.size()){
                return DEPARTMENTS.get(choice - 1);
            }
            else if (choice == DEPARTMENTS.size() + 1){
                return null;
            }
            else {
                System.out.println("Invalid selection.");
            }
        }
    }

    // list all departments
    private static void listDepartments(){
        if(DEPARTMENTS.isEmpty()){
            System.out.println("No departments added yet.");
            return;
        }

        TablePrinter table = new TablePrinter(Arrays.asList("ID", "Department Name"));

        // Print each doctor's information
        for(Department department : DEPARTMENTS) {
            table.addRow(Arrays.asList(department.getDepartmentId(), department.getDepartmentName()));
        }

        table.print();
    }

    // find a department using department id
    private static Department findDepartmentById(String search){
        for (Department department : DEPARTMENTS){
            if (department.getDepartmentId().equals(search)){
                return department;
            }
        }
        return null;
    }

    // display the name and year of experience for all available doctors in the selected department
    private static void displayDoctorInDepartment(List<Doctor> availableDoctors, Department department){
        clearScreen();
        System.out.println("Available Doctors in " + department.getDepartmentName() + " Department : ");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < availableDoctors.size(); i++){
            System.out.println((i+1) + ". " + availableDoctors.get(i).getName() + ", " + availableDoctors.get(i).getYearOfExp() + " years of experience");
        }

        System.out.println((availableDoctors.size() + 1) + ". Back");
        System.out.println("-----------------------------------------");
    }



    // get staff year of experience
    private static int getPersonYearOfExp(Role role){
        clearScreen();
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Year of Experience (e.g. 12): ");
            String yearOfExp = SCANNER.nextLine().trim();
            if(ValidationCheck.validateYearOfExp(yearOfExp)){
                return Integer.parseInt(yearOfExp);
            } else {
                System.out.println("\nInvalid year of experience format. Please re-enter: ");
            }
        }
    }

    // store record in file
    // add role in feedback message
    private static void storeRecord(String file, String record){
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(record);
        } catch (IOException e){
            System.out.println("Error saving information." + e.getMessage());
        }
    }

    // return list of person as list of string
    private static List<String> convertToFileFormat(List<Person> list){
        return list.stream()
        .map(Person::toFileFormat)
        .toList();
    }

    // overwrite file with new data after modifying data
    private static void overwriteFile(String file, List<String> records){
        try(FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            for (String record : records){
                out.println(record);
            }
        } catch (IOException e){
            System.out.println("Error saving information." + e.getMessage());
        }

        // read and update all lists
        readDepartments();
        readDoctors();
        readNurse();
        readPatient();
        readRooms();
        readAppointments();
    }

    // department management page
    private static void departmentManagement(){
        clearScreen();
        System.out.println("Manage Departments");
        System.out.println("------------------");
        System.out.println("1. Add New Department");
        System.out.println("2. List All Departments");
        System.out.println("3. Back");
        System.out.println("------------------");
    }

    // add department
    private static Department getNewDepartmentDetails(){
        String name;

        while(true){
            System.out.print("Enter New Department Name (e.g. Orthopedics or -1 to return): ");
            name = SCANNER.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return new Department(name);
            } else if (name.equals("-1")){
                return null; // Go back
            } else {
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }
    }

    // read departments from file
    private static void readDepartments(){
        DEPARTMENTS.clear();

        // read text from line
        try(BufferedReader br = new BufferedReader(new FileReader(DEPARTMENT_FILE))){
            String line;
            while ((line = br.readLine()) != null){
                String[] departmentRecord = line.split("\\|");
                DEPARTMENTS.add(new Department(departmentRecord[0], departmentRecord[1]));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }

        Department.setDepartmentCount(DEPARTMENTS.size());
    }
    
    // doctor management system 
    private static void doctorManagement(){
        clearScreen();
        System.out.println("Doctor Management");
        System.out.println("-----------------");
        System.out.println("1. Add Doctor Information");
        System.out.println("2. List All Doctors");
        System.out.println("3. Search Doctor");
        System.out.println("4. Delete Doctor Record");
        System.out.println("5. View Doctor Appointments");
        System.out.println("6. Back");
        System.out.println("-----------------");
    }

    // doctor management system sub selection add doctor information
    private static Doctor getNewDoctorDetails(){

        // get person information
        String doctorName = getPersonName(Role.DOCTOR);
        if (doctorName.equals("-1")){
            return null; // Go back
        }
        String doctorIc = getPersonIc(Role.DOCTOR);
        if (doctorIc.equals("-1")) {
            return null; // Go back
        }
        String doctorGender = getPersonGender(Role.DOCTOR);
        String doctorContactNumber = getPersonContactNumber(Role.DOCTOR);
        String doctorAddress = getPersonAddress(Role.DOCTOR);
        clearScreen();
        Department doctorDepartment = selectDepartment();
        if (doctorDepartment == null) {
            return null; // Go back
        }
        int doctorYearOfExp = getPersonYearOfExp(Role.DOCTOR);

        // create a new doctor object
        return new Doctor(doctorIc, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorDepartment, doctorYearOfExp);
    }

    // create an array list for the reading file 
    private static void readDoctors(){
        DOCTORS.clear();

        // read from the text file
        try(BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))){
            String line;
            while ((line = br.readLine()) != null){
                String[] doctorRecord = line.split("\\|");
                DOCTORS.add(new Doctor(doctorRecord[0], doctorRecord[1], doctorRecord[2], doctorRecord[3], doctorRecord[4], doctorRecord[5], findDepartmentById(doctorRecord[6]), Integer.parseInt(doctorRecord[7])));
            }
        } catch (FileNotFoundException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }

        Doctor.setDoctorCount(DOCTORS.size());
    }


    // list doctor information 
    private static void listdoctor(){
        if(DOCTORS.isEmpty()){
            System.out.println("No doctors is registered yet.");
            return;
        }

        TablePrinter table = new TablePrinter(Arrays.asList("ID", "IC", "Name", "Gender", "Contact Number", "Address", "YearOfExp", "Department Name"));

        // Print each doctor's information
        for(Doctor doctor : DOCTORS) {
            table.addRow(Arrays.asList(doctor.getId(), doctor.getIc(), doctor.getName(), doctor.getGender(), doctor.getContactNumber(), doctor.getAddress(), String.valueOf(doctor.getYearOfExp()), doctor.getDepartment().getDepartmentName()));
        }

        table.print();
    }

    // search doctor 
    private static Doctor searchDoctor(){
        String search;

        while (true){
            System.out.println("Search by");
            System.out.println("----------");
            System.out.println("1. Doctor ID");
            System.out.println("2. Doctor IC");
            System.out.println("3. Doctor Name");
            System.out.println("4. Back");
            System.out.println("----------");

            int choice = getChoice();

            if (choice == 1){
                search = getPersonId(Role.DOCTOR);
                break;
            } else if (choice == 2){
                search = getPersonIc(Role.DOCTOR);
                break;
            } else if (choice == 3){
                search = getPersonName(Role.DOCTOR);
                break;
            } else if (choice == 4){
                return null; // Go back
            } else {
                System.out.println("Invalid choice.\nPlease re-enter.\n");
            }
        }

        for (Doctor doctor: DOCTORS){
            if(doctor.getId().equals(search)
            || doctor.getIc().equals(search)
            || doctor.getName().equalsIgnoreCase(search)){
                return doctor;
            }
        }

        clearScreen();
        System.out.println("Doctor record not found.\nPlease try again.\n");
        System.out.println("Press any key to return.");
        SCANNER.nextLine();
        return null;
    }

    // find doctor by id
    private static Doctor findDoctorById(String id){
        for (Doctor doctor : DOCTORS){
            if(doctor.getId().equals(id)){
                return doctor;
            }
        }
        return null;
    }

    // find patient by id
    private static Patient findPatientById(String id){
        for (Patient patient : PATIENTS){
            if(patient.getId().equals(id)){
                return patient;
            }
        }
        return null;
    }

    // find room by id
    private static Room findRoomById(String id){
        for (Room room : CONSULTATION_ROOMS){
            if(room.getRoomID().equals(id)){
                return room;
            }
        }
        return null;
    }

    //nurse management system
    private static void nurseManagement(){
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
    private static Nurse getNewNurseDetails(){

        // get person information
        String nurseName = getPersonName(Role.NURSE);
        if (nurseName.equals("-1")){
            return null; // Go back
        }
        String nurseIC = getPersonIc(Role.NURSE);
        String nurseGender = getPersonGender(Role.NURSE);
        String nurseContactNumber = getPersonContactNumber(Role.NURSE);
        String nurseAddress = getPersonAddress(Role.NURSE);
        clearScreen();
        Department nurseDepartment = selectDepartment();
        int nurseYearOfExp = getPersonYearOfExp(Role.NURSE);

        // createa a new nurse object
        return new Nurse(nurseIC, nurseName, nurseGender, nurseContactNumber, nurseAddress, nurseDepartment, nurseYearOfExp);
    }

    // read all nurse information and store it at the array list 
    private static void readNurse(){
        NURSES.clear();

        try(BufferedReader br = new BufferedReader(new FileReader(NURSE_FILE))){
            
            String line;
            while((line = br.readLine()) != null){
                String[] nurseRecord = line.split("\\|");
                NURSES.add(new Nurse(nurseRecord[0], nurseRecord[1], nurseRecord[2], nurseRecord[3], nurseRecord[4], nurseRecord[5], findDepartmentById(nurseRecord[6]), Integer.parseInt(nurseRecord[7])));
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e){
            System.out.println("Error reading nurse data: " + e.getMessage());
        }
    
        Nurse.setNurseCount(NURSES.size());
    }

    // list nurse information 
    private static void listNurse(){
        if(NURSES.isEmpty()){
            System.out.println("No nurse information is registered yet.");
            return;
        }

        TablePrinter table = new TablePrinter(Arrays.asList("ID", "IC", "Name", "Gender", "Contact Number", "Address", "YearOfExp", "Department Name"));

        for (Nurse nurse: NURSES){
            table.addRow(Arrays.asList(nurse.getId(), nurse.getIc(), nurse.getName(), nurse.getGender(), nurse.getContactNumber(), nurse.getAddress(), String.valueOf(nurse.getYearOfExp()), nurse.getDepartment().getDepartmentName()));
        }

        table.print();
    }

    // search nurse 
    private static Nurse searchNurse(){
        String search;

        while (true){
            System.out.println("Search by");
            System.out.println("----------");
            System.out.println("1. Nurse ID");
            System.out.println("2. Nurse IC");
            System.out.println("3. Nurse Name");
            System.out.println("4. Back");
            System.out.println("----------");

            int choice = getChoice();

            if (choice == 1){
                search = getPersonId(Role.NURSE);
                break;
            } else if (choice == 2){
                search = getPersonIc(Role.NURSE);
                break;
            } else if (choice == 3){
                search = getPersonName(Role.NURSE);
                break;
            } else if (choice == 4){
                return null; // Go back
            } else {
                System.out.println("Invalid choice.\nPlease re-enter.\n");
            }
        }

        for (Nurse nurse: NURSES){
            if(nurse.getId().equals(search)
            || nurse.getIc().equals(search)
            || nurse.getName().equalsIgnoreCase(search)){
                return nurse;
            }
        }

        clearScreen();
        System.out.println("Nurse record not found.\nPlease try again.\n");
        System.out.println("Press any key to return.");
        SCANNER.nextLine();
        return null;
    }
    
    //patient management system
    private static void patientManagement(){
        clearScreen();
        System.out.println("Patient Management");
        System.out.println("-----------------");
        System.out.println("1. Register New Patient");
        System.out.println("2. List All Patient Records");
        System.out.println("3. Search Patient");
        System.out.println("4. Delete Patient Record");
        System.out.println("5. Back");
        System.out.println("-----------------");
    }

    // add patient information 
    private static Patient getNewPatientDetails(){
        String patientName = getPersonName(Role.PATIENT);
        if (patientName.equals("-1")){
            return null; // Go back
        }
        String patientIC = getPersonIc(Role.PATIENT);
        String patientGender = getPersonGender(Role.PATIENT);
        String patientContactNumber = getPersonContactNumber(Role.PATIENT);
        String patientAddress = getPersonAddress(Role.PATIENT);
        String patientEmergencyContact = getPersonEmergencyContactNumber(Role.PATIENT);

        Patient newPatient = new Patient(patientIC, patientName, patientGender, patientContactNumber, patientAddress, patientEmergencyContact);

        clearScreen();
        System.out.println("New Patient Details:");
        System.out.println("---------------------");
        System.out.println(newPatient);
        System.out.println("---------------------");
        System.out.println("Save new patient information?");
        if (getYesOrNoInput()){
            return newPatient;
        } else {
            return null;
        }
    }

    // get all patient information 
    private static void readPatient(){
        PATIENTS.clear();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] patientRecord = line.split("\\|");
                PATIENTS.add(new Patient(patientRecord[0], patientRecord[1], patientRecord[2], patientRecord[3], patientRecord[4], patientRecord[5], LocalDate.parse(patientRecord[6]), patientRecord[7]));
            }
        } catch (FileNotFoundException e ){

        } catch (IOException e){
            System.out.println("Error reading patient information."+ e.getMessage());
        }

        Patient.setPatientCount(PATIENTS.size());
    }

    // list for all patient
    private static void listPatient(){
        if(PATIENTS.isEmpty()){
            System.out.println("No patient information is added.");
            return;
        }

        TablePrinter table = new TablePrinter(Arrays.asList("ID", "IC", "Name", "Gender", "Contact Number", "Address", "Emergency Contact Number"));

        // Print each doctor's information
        for(Patient patient : PATIENTS) {
            table.addRow(Arrays.asList(patient.getId(), patient.getIc(), patient.getName(), patient.getGender(), patient.getContactNumber(), patient.getAddress(), patient.getEmergencyContact()));
        }

        table.print();
    }

    // search for patient 
    private static Patient searchPatient(){
        readPatient();
        while(true){
            clearScreen();
            System.out.println("Search by:");
            System.out.println("----------");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient IC");
            System.out.println("3. Patient Name");
            System.out.println("4. Back");
            System.out.println("----------");

            int choice = getChoice();
            Patient patient;

            switch (choice) {
                case 1:
                    patient = searchPatientById(getPersonId(Role.PATIENT));
                    if (patient != null) {
                        return patient;
                    } else {
                        clearScreen();
                        System.out.println("Patient record not found.\nPlease try again.\n");
                    }
                    break;
                case 2:
                    patient = searchPatientByIc(getPersonIc(Role.PATIENT));
                    if (patient != null) {
                        return patient;
                    } else {
                        clearScreen();
                        System.out.println("Patient record not found.\nPlease try again.\n");
                    }
                    break;
                case 3:
                    patient = searchPatientByName(getPersonName(Role.PATIENT));
                    if (patient != null) {
                        return patient;
                    } else {
                        clearScreen();
                        System.out.println("Patient record not found.\nPlease try again.\n");
                    }
                    break;
                case 4:
                    return null; // Go back
                default:
                    System.out.println("Invalid choice. Please re-enter.");
            }
        }
    }

    // search patient by ic
    private static Patient searchPatientByIc(String ic){
        readPatient();
        for (Patient patient : PATIENTS){
            if(patient.getIc().equals(ic)){
                return patient;
            }
        }
        return null;
    }

    // search patient by id
    private static Patient searchPatientById(String id){
        readPatient();
        for (Patient patient : PATIENTS){
            if(patient.getId().equals(id)){
                return patient;
            }
        }
        return null;
    }

    // search patient by name
    private static Patient searchPatientByName(String name){
        readPatient();
        for (Patient patient : PATIENTS){
            if(patient.getName().equalsIgnoreCase(name)){
                return patient;
            }
        }
        return null;
    }

    // read appointment records from file
    private static void readAppointments(){
        readDoctors();
        readPatient();
        readRooms();
        APPOINTMENTS.clear();

        // read from the file
        try(BufferedReader br = new BufferedReader(new FileReader(APPOINTMENT_FILE))){
            String line;
            while ((line = br.readLine()) != null){
                String[] appointmentRecord = line.split("\\|");
                Doctor doctor = findDoctorById(appointmentRecord[3]);
                Patient patient = findPatientById(appointmentRecord[4]);
                Appointment appointment = new Appointment(LocalDateTime.parse(appointmentRecord[0]), appointmentRecord[1], LocalDateTime.parse(appointmentRecord[2]), doctor, patient, findRoomById(appointmentRecord[5]), appointmentRecord[6]);
                APPOINTMENTS.add(appointment);
                doctor.addAppointment(appointment);
                patient.addAppointment(appointment);
            }
        }catch (FileNotFoundException e){

        } catch (IOException e){
            System.out.println("Error reading appointment information."+ e.getMessage());
        }

        Appointment.setAppointmentCount(APPOINTMENTS.size());
    }

    // check room availability at given date and time
    private static boolean checkRoomAvailability(Room room, LocalDateTime date){
        for(Appointment appointment : APPOINTMENTS){
            LocalDateTime appointmentDate = appointment.getAppointmentDateTime();
            if((appointmentDate.equals(date)
            || appointmentDate.plusMinutes(15).equals(date)
            || appointmentDate.minusMinutes(15).equals(date))
            && appointment.getConsultationRoom().equals(room) ){
                return false;
            }
        }
        return true;
    }

    // returns the next available room, if none available, return null
    private static Room getNextAvailableRoom(String roomType, LocalDateTime date){
        for (Room room : CONSULTATION_ROOMS){
            if(room.getRoomType().equals(roomType) && checkRoomAvailability(room, date)){
                return room;
            }
        }
        return null;
    }

    // check doctor is it available 
    private static boolean doctorAvailability(Doctor doctor, LocalDateTime date){
        List<Appointment> appointments = doctor.getAppointments();
        for(Appointment appointment : appointments){
            if(appointment.getDoctor().equals(doctor) && appointment.getAppointmentDateTime().equals(date)){
                return false;
            }
        }
        return true;
    }

    // returns a list of all doctors in a department
    private static List<Doctor> getDoctorInDepartment(Department department){
        List<Doctor> departmentDoctors = new ArrayList<>();

        readDoctors();
        for (Doctor doctor : DOCTORS){
            if(doctor.getDepartment().equals(department)){
                departmentDoctors.add(doctor);
            }
        }
        return departmentDoctors;
    }

    private static List<Appointment> getDoctorAppointments(Doctor doctor){
        List<Appointment> doctorAppointments = new ArrayList<>();

        readAppointments();
        for (Appointment appointment : APPOINTMENTS){
            if(appointment.getDoctor().equals(doctor)){
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    // returns a list of all appointments for a patient
    private static List<Appointment> getPatientAppointments(Patient patient){
        List<Appointment> patientAppointments = new ArrayList<>();

        readAppointments();
        for (Appointment appointment : APPOINTMENTS){
            if(appointment.getPatient().equals(patient)){
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    // read medications list and store to the file 
    

    // read rooms from file
    private static void readRooms(){
        CONSULTATION_ROOMS.clear();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(ROOM_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] roomRecord = line.split("\\|");
                Room newRoom = new Room(roomRecord[0]);
                CONSULTATION_ROOMS.add(newRoom);
                Room.incrementRoomCount(newRoom.getFloor());
            }
        } catch (FileNotFoundException e){

        } catch (IOException e){
            System.out.println("Error reading room data: " + e.getMessage());
        }
    }

    // returns all rooms on a specific floor
    public List<Room> getRoomsOnFloor(int floor){
        List<Room> roomsOnFloor = new ArrayList<>();

        for(Room room : CONSULTATION_ROOMS){
            if(room.onFloor(floor)){
                roomsOnFloor.add(room);
            }
        }

        return roomsOnFloor;
    }

    // book appointment page
    private static void bookAppointment(){
        clearScreen();
        while(true){
            // get patient details
            Patient patient = null;

            while(true){
                clearScreen();
                System.out.println("Booking Appointment Page");
                System.out.println("------------------------");
                System.out.println("1. New Patient");
                System.out.println("2. Existing Patient");
                System.out.println("3. Back");
                System.out.println("------------------------");

                int choice = getChoice();

                // get new patient details
                if(choice == 1){
                    patient = getNewPatientDetails();
                    if (patient != null){
                        PATIENTS.add(patient);
                        storeRecord(PATIENT_FILE, patient.toFileFormat());
                        System.out.println("\nNew patient registered successfully.");
                    }
                }

                // retrieve existing patient details
                else if (choice == 2){
                    clearScreen();
                    while(true){
                        System.out.print("Enter Your IC (.e.g. 123456-07-1234 or -1 to quit): ");
                        String ic = getIc();
                        if (ic.equals("-1")){
                            break;
                        }
                        else if (ic.equals("")){
                            System.out.println("Invalid IC format.\nPlease re-enter.\n");
                        }
                        else {
                            patient = searchPatientByIc(ic);
                            if (patient != null){
                                System.out.println("\nMatching Record found!");
                                System.out.println("\nPatient Details:");
                                System.out.println("------------------");
                                System.out.println(patient);
                                System.out.println("------------------");
                                System.out.println("\nBook Appointment?");
                                if (getYesOrNoInput()){
                                    break;
                                }
                                else {
                                    patient = null;
                                    break;
                                }
                            } else {
                                clearScreen();
                                System.out.println("Patient not found.\nPlease try again.\n");
                            }
                        }
                    }
                }

                // back
                else if (choice == 3){
                    return;
                } 
                
                // invalid selection
                else {
                    System.out.println("Invalid selection. Please re-enter.");
                }

                // patient has been found or created
                if (patient != null && patient.getAppointments().size() < 5){
                    while(true){
                        // prompt user to select department
                        clearScreen();
                        System.out.println("Select Appointment Doctor:\n");
                        Department department = selectDepartment();

                        // if department is null, user wants to go back
                        if(department == null){
                            break;
                        }

                        // list of available doctors in the selected department
                        List<Doctor> availableDoctors = getDoctorInDepartment(department);

                        // if no doctors available, prompt user to select another department
                        if(availableDoctors.isEmpty()){
                            System.out.println("No doctors available in this department.\nPlease select another department.");
                        }
                        else {
                            while(true){                                                   
                                displayDoctorInDepartment(availableDoctors, department);

                                choice = getChoice();
                                if (choice >= 1 && choice <= availableDoctors.size()){

                                    Doctor doctor = availableDoctors.get(choice - 1);

                                    // get appointment date 
                                    clearScreen();
                                    while (true){
                                        System.out.print("Enter appointment date (2025-04-25): ");
                                        try {
                                            LocalDate appointmentDate = LocalDate.parse(SCANNER.nextLine());

                                            // the earliest appointment date is the next day
                                            // prompt user to select a future date if the date is today or earlier
                                            if(!appointmentDate.isAfter(LocalDate.now())){
                                                clearScreen();
                                                System.out.println("The earliest appointment date is tomorrow.\nPlease select a future date.");
                                            }
                                            // check if the date is a weekend
                                            else if (appointmentDate.getDayOfWeek() == DayOfWeek.SATURDAY || appointmentDate.getDayOfWeek() == DayOfWeek.SUNDAY){
                                                clearScreen();
                                                System.out.println("The hospital is closed on weekends.\nPlease select a weekday.");
                                            } else {
                                                // get appointment time
                                                System.out.println("Select appointment time: ");

                                                int hour;
                                                int minutes;

                                                while(true){
                                                    clearScreen();
                                                    System.out.println("Selected Date: " + appointmentDate);
                                                    System.out.print("Enter hour (9 - 19): ");
                                                    try {
                                                        hour = SCANNER.nextInt();
                                                        SCANNER.nextLine(); // consume the newline character
                                                        // appointments must be between 9am and 8pm
                                                        if (hour >= 9 && hour <= 19){
                                                            while(true){
                                                                System.out.print("Enter minutes (0, 15, 30, 45): ");
                                                                minutes = SCANNER.nextInt();
                                                                SCANNER.nextLine(); // consume the newline character
                                                                if (minutes == 0 || minutes == 15 || minutes == 30 || minutes == 45){
                                                                    LocalDateTime appointmentDateTime = appointmentDate.atTime(hour, minutes);

                                                                    // option to select another time

                                                                    // check doctor availability at given date and time
                                                                    if(!doctorAvailability(doctor, appointmentDateTime)){
                                                                        System.out.println("This doctor is not available at the selected time.");
                                                                    } else {
                                                                        // find available room
                                                                        readRooms();
                                                                        Room room = getNextAvailableRoom("Consultation", appointmentDateTime);

                                                                        // no available room check
                                                                        if(room == null){
                                                                            System.out.println("No rooms available.\nPlease select another date or time.");
                                                                            break;
                                                                        }

                                                                        clearScreen();
                                                                        System.out.println("\nAppointment Details:");
                                                                        System.out.println("---------------------");
                                                                        System.out.println("Appointment Date: " + appointmentDateTime.toLocalDate());
                                                                        System.out.println("Appointment Time: " + appointmentDateTime.toLocalTime());
                                                                        System.out.println("Doctor: " + doctor.getName());
                                                                        System.out.println("Patient Name: " + patient.getName());
                                                                        System.out.println("Patient IC: " + patient.getIc());
                                                                        System.out.println("---------------------");
                                                                        System.out.println("\nBook Appointment?");

                                                                        if (getYesOrNoInput()){
                                                                            // create appointment
                                                                            try {
                                                                                Appointment appointment = new Appointment(appointmentDateTime, doctor, patient, room);

                                                                                //link appointment with DOCTOR and PATIENT
                                                                                doctor.addAppointment(appointment);
                                                                                patient.addAppointment(appointment);
                                                                                
                                                                                APPOINTMENTS.add(appointment);
                                                                                storeRecord(APPOINTMENT_FILE, appointment.toFileFormat());

                                                                                clearScreen();
                                                                                System.out.println("\nAppointment Booked Successfully!");
                                                                                System.out.println("---------------------------------");
                                                                                System.out.println("Doctor: " + doctor.getName());
                                                                                System.out.println("Patient Name: " + patient.getName());
                                                                                System.out.println("Patient IC: " + patient.getIc());
                                                                                System.out.println("Appointment Room: " + room.getLocation());
                                                                                System.out.println("---------------------------------");
                                                                                System.out.println("Press <Enter> to return.");
                                                                                SCANNER.nextLine();
                                                                                return;
                                                                            } catch (Exception e){
                                                                                System.out.println("Unable to create the appointment.\nPlease try again." + e.getMessage());
                                                                            }
                                                                        } else {
                                                                            System.out.println("Press <Enter> to return.");
                                                                            SCANNER.nextLine();
                                                                            return;
                                                                        }
                                                                    }
                                                                } else {
                                                                    System.out.println("Invalid minute. Please re-enter.");
                                                                }
                                                            }
                                                            break;
                                                        } else {
                                                            clearScreen();
                                                            System.out.println("Invalid hour. Please re-enter.");
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Invalid input. Please enter a number.");
                                                        SCANNER.nextLine(); // clear the invalid input
                                                    }
                                                }
                                            }
                                            }
                                        catch (Exception e) {
                                            clearScreen();
                                            System.out.println("Invalid date format.\nPlease re-enter.");
                                        }
                                    }
                                } else if (choice == availableDoctors.size() + 1){
                                    break;
                                } else {
                                    System.out.println("Invalid selection. Please re-enter.");
                                }
                            }
                        }   
                    }
                }
            }  
        }
    }    


    // read medications
    private static void readMedications(){
        MEDICATIONS.clear();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(MEDICATION_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] medicationRecord = line.split("\\|");
                MEDICATIONS.add(new Medication(medicationRecord[0], Integer.parseInt(medicationRecord[1])));
            }
        } catch (FileNotFoundException e){

        } catch (IOException e){
            System.out.println("Error reading medication data: " + e.getMessage());
        }
    }

    // medical records management page for staff
    public static void medicalRecordManagement(){
        while(true){
            clearScreen();
            System.out.println("Medical Record Management");
            System.out.println("-------------------------");
            System.out.println("1. Generate Medical Record");
            System.out.println("2. View Medical Record");
            System.out.println("3. Delete Medical Record");
            System.out.println("4. Back");
            System.out.println("-------------------------");

            int choice = getChoice();

            switch(choice){
                case 1:
                    clearScreen();
                    generateMedicalRecord();
                    break;
                case 2:
                    clearScreen();
                    viewMedicalRecord();
                    break;
                case 3:
                    clearScreen();
                    deleteMedicalRecord();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Re-enter");
            }
        }
    }

    //generate medical records 
    public static void generateMedicalRecord(){
        clearScreen();
        while(true){
            System.out.println("============Generate Medical Records============");
            
            //list patients to choose 
            if(PATIENTS.isEmpty()){
                System.out.println("No patient informations are available");
                return;
            }

            while(true){
                // selected patient object
                Patient selectedPatient = searchPatient();

                // go back
                if(selectedPatient == null){
                    break;
                }

                // show patient details and ask for confirmation
                clearScreen();
                System.out.println("Patient Details:");
                System.out.println("-----------------");
                System.out.println(selectedPatient);
                System.out.println("-----------------");
                System.out.println("Create Medical Record? (Y/N): ");

                // go back
                if (!getYesOrNoInput()){
                    break;
                }

                while(true){
                    // selected doctor object
                    Doctor selectedDoctor = null;

                    clearScreen();
                    System.out.println("Select Doctor:\n");
                    Department department = selectDepartment();

                    // if department is null, user wants to go back
                    if(department == null){
                        break;
                    }

                    // list of available doctors in the selected department
                    List<Doctor> availableDoctors = getDoctorInDepartment(department);

                    // if no doctors available, prompt user to select another department
                    if(availableDoctors.isEmpty()){
                        System.out.println("No doctors available in this department.\nPlease select another department.");
                        continue;
                    }
                    else {
                        while(true){                                                   
                            displayDoctorInDepartment(availableDoctors, department);

                            int choice = getChoice();
                            if (choice >= 1 && choice <= availableDoctors.size()){
                                selectedDoctor = availableDoctors.get(choice - 1);
                                break;
                            } else if (choice == availableDoctors.size() + 1){
                                break;
                            } else {
                                System.out.println("Invalid selection. Please re-enter.");
                            }
                        }
                    }

                    // go back
                    if(selectedDoctor == null){
                        break;
                    }

                    //medical records new object
                    MedicalRecords medicalRecord = new MedicalRecords(selectedPatient, selectedDoctor);

                    clearScreen();
                    while(true){

                        // diagnoses record
                        System.out.print("Enter diagnoses (or -1 to go back, -2 to proceed with the next step): ");
                        String diagnoses = SCANNER.nextLine();

                        // go back
                        if (diagnoses.equals("-1")){
                            clearScreen();
                            break;
                        }
                        else if (diagnoses.equals("-2")){   
                            if (medicalRecord.getDiagnoses().isEmpty()){
                                clearScreen();
                                System.out.println("Diagnosis cannot be empty.");
                                continue;
                            }
                            // display all medications and select to prescribed medications
                            // allow user to go back to select more
                            readMedications();
                            clearScreen();
                            while(true){
                                System.out.println("Select medications:");
                                System.out.println("-------------------");
                                for (int i = 0; i < MEDICATIONS.size(); i++){
                                    System.out.println((i + 1) + ". " + MEDICATIONS.get(i));
                                }

                                System.out.println("-------------------");
                                System.out.print("Enter medications (or -1 to go back -2 to proceed with the next step): ");
                                String medication = SCANNER.nextLine();

                                // go back
                                if (medication.equals("-1")){
                                    clearScreen();
                                    break;
                                }

                                // proceed to next step
                                else if (medication.equals("-2")){
                                    if (medicalRecord.getPrescribedMedications().isEmpty()){
                                        clearScreen();
                                        System.out.println("No medications prescribed. Please select at least one medication.");
                                        continue;
                                    }
                                    clearScreen();
                                    while(true){
                                        // select / search for treatment history 
                                        // treatment history records 
                                        System.out.print("Enter treatment notes / history (or -1 to go back, -2 to proceed with the next step): ");
                                        String treatment = SCANNER.nextLine();

                                        // go back
                                        if (treatment.equals("-1")){
                                            clearScreen();
                                            break;
                                        }
                                        else if (treatment.equals("-2")){
                                            if (medicalRecord.getTreatmentHistory().isEmpty()){
                                                clearScreen();
                                                System.out.println("Treatment notes cannot be empty.");
                                            }
                                            else {
                                                clearScreen();
                                                while(true){
                                                    // select / search for follow up date 
                                                    // follow up date records 
                                                    System.out.print("Enter follow up date (yyyy-mm-dd or -1 to go back or - to skip): ");
                                                    String followUp = SCANNER.nextLine();

                                                    // go back
                                                    if (followUp.equals("-1")){
                                                        break;
                                                    }

                                                    else if (followUp.equals("-")){
                                                        medicalRecord.addFollowUp(null);
                                                    }

                                                    else {
                                                        try {
                                                            LocalDateTime followUpDate = LocalDate.parse(followUp).atStartOfDay();
                                                            medicalRecord.addFollowUp(followUpDate);
                                                            
                                                            // select time

                                                            // date must be tomorrow or later, not on weekends
                                                            if (!followUpDate.toLocalDate().isAfter(LocalDate.now()) || followUpDate.getDayOfWeek() == DayOfWeek.SATURDAY || followUpDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                                                                clearScreen();
                                                                System.out.println("Invalid date. Please enter a date that is tomorrow or later and not on weekends.");
                                                                continue;
                                                            }
                                                        } catch (Exception e) {
                                                            clearScreen();
                                                            System.out.println("Invalid date format. Re-enter.");
                                                            continue;
                                                        }
                                                    }

                                                    // confirmation to save medical record
                                                    clearScreen();
                                                    System.out.println("Medical Record Details:");
                                                    System.out.println("------------------------");
                                                    System.out.println(medicalRecord);
                                                    System.out.println("------------------------");
                                                    System.out.println("Confirm to save medical record? (Y/N): ");

                                                    // cancel
                                                    if (!getYesOrNoInput()){
                                                        return;
                                                    }

                                                    // save medical records 
                                                    storeRecord(MEDICAL_RECORD_FILE, medicalRecord.toFileFormat());
                                                    clearScreen();
                                                    System.out.println("Medical Record created successfully.");
                                                    System.out.println("Press <Enter> to return.");
                                                    SCANNER.nextLine();
                                                    return;
                                                }
                                            }

                                        }
                                        else {
                                            if (treatment.isEmpty()){
                                                clearScreen();
                                                System.out.println("Invalid Input. Please re-enter.");
                                                continue;
                                            }
                                            medicalRecord.addTreatmentNote(treatment);
                                        }
                                    }
                                }
                                else {
                                    try {
                                        int medicationChoice = Integer.parseInt(medication) - 1;
                                        if (medicationChoice < 0 || medicationChoice >= MEDICATIONS.size()){
                                            clearScreen();
                                            System.out.println("Invalid choice. Please re-enter.");
                                            continue;
                                        }
                                        clearScreen();
                                        medicalRecord.prescribeMedications(MEDICATIONS.get(Integer.parseInt(medication) - 1));
                                        System.out.println("Prescribed " + MEDICATIONS.get(medicationChoice) + ".\n");
                                    } catch (NumberFormatException e) {
                                        clearScreen();
                                        System.out.println("Invalid input. Please enter a number.");
                                    }
                                }
                            }
                        }
                        else {
                            if (diagnoses.isEmpty()){
                                clearScreen();
                                System.out.println("Diagnoses cannot be empty.");
                                continue;
                            }
                            medicalRecord.addDiagnosis(diagnoses);
                        }
                    }
                }
            }
            break;
        }
    }

    // view medical records for patients 
    public static void viewMedicalRecord(){
        clearScreen();
        while(true){
            System.out.println("--------View Medical Record----------");

            Patient patient = searchPatient();

            // go back
            if(patient == null){
                break;
            }

            // List for a medical records based on patient id
            List<MedicalRecords> medicalRecords = getMedicalRecordFor(patient);

            // check if the medical records for a patient is it exist or not
            if(medicalRecords.isEmpty()){
                clearScreen();
                System.out.println("No medical records found for this patient.");
                System.out.println("Press <Enter> to return.");
                SCANNER.nextLine();
            }
            else {
                clearScreen();
                System.out.println("Patient Details:");
                System.out.println("-----------------");
                System.out.println("Name: " + patient.getName());
                System.out.println("IC: " + patient.getIc());
                System.out.println("-----------------\n");

                for(int i = 0; i < medicalRecords.size(); i++){
                    System.out.println("Medical Record #" + (i + 1));   
                    System.out.println("---------------------------------------------");
                    System.out.println(medicalRecords.get(i));
                    System.out.println("---------------------------------------------");
                }

                System.out.println("\nPress <Enter> to return.");
                SCANNER.nextLine();
            }
        }
    }

    // read medical records from file
    // search for medical records for a patient
    public static List<MedicalRecords> getMedicalRecordFor(Patient patient){
        List<MedicalRecords> medicalRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(MEDICAL_RECORD_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] medicalrecord = line.split("\\|");
                if(medicalrecord.length >= 7 && medicalrecord[2].equals(patient.getId())){
                    // find patient and doctor 
                    Doctor doctor = null;

                    //check patient ID 
                    for(Patient pat : PATIENTS){
                        if(pat.getId().equals(medicalrecord[2])){
                            patient = pat;
                            break;
                        }
                    }

                    //check doctor id 
                    for(Doctor doct : DOCTORS){
                        if(doct.getId().equals(medicalrecord[3])){
                            doctor = doct;
                            break;
                        }
                    }
                    
                    // check if doctor is null
                    if(doctor != null){
                        // create a new medical record object and add to the list
                        List<String> diagnoses = Arrays.asList(medicalrecord[4].split(","));

                        List<Medication> prescribedMedications = new ArrayList<>();
                        readMedications();
                        for (String medication : medicalrecord[5].split(",")){
                            System.out.println("Medication: " + medication);
                            prescribedMedications.add(Medication.findMedicationByName(medication, MEDICATIONS));
                        }
                        List<String> treatmentHistory = Arrays.asList(medicalrecord[6].split(","));

                        LocalDateTime nextFollowUp = null;
                        // check if localdatetime is null
                        if (!medicalrecord[7].equals("null")){
                            // parse the next follow up date
                            nextFollowUp = LocalDateTime.parse(medicalrecord[7]);
                        }

                        // create a new medical record object
                        MedicalRecords medicalRecord = new MedicalRecords(medicalrecord[0], LocalDateTime.parse(medicalrecord[1]), patient, doctor, diagnoses, prescribedMedications, treatmentHistory, nextFollowUp);
                        medicalRecords.add(medicalRecord);
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Medical Record file not found");
        } catch (IOException e){
            System.out.println("Error reading medical records" + e.getMessage());
        }

        return medicalRecords;
    }

    public static void deleteMedicalRecord(){
        clearScreen();
        while(true){
            System.out.println("--------Delete Medical Record--------");

            if(PATIENTS.isEmpty()){
                System.out.println("No patients is found in the file.");
                return;
            }

            Patient patient = searchPatient();

            // go back
            if(patient == null){
                break;
            }

            List<MedicalRecords> medicalRecords = getMedicalRecordFor(patient);

            // check if the medical records for a patient is it exist or not
            if(medicalRecords.isEmpty()){
                clearScreen();
                System.out.println("No medical records found for this patient.");
            }
            else {
                clearScreen();
                System.out.println("Patient Details:");
                System.out.println("-----------------");
                System.out.println(patient);
                System.out.println("-----------------\n");

                for(int i = 0; i < medicalRecords.size(); i++){
                    System.out.println("Medical Record #" + (i + 1));   
                    System.out.println("-------------------------");
                    System.out.println(medicalRecords.get(i));
                    System.out.println("-------------------------");
                }

                System.out.println("Select medical record to delete (or -1 to return): ");
                String input = SCANNER.nextLine();

                if (input.equals("-1")){
                    break;
                } else {
                    try{
                        int medicalRecordChoice = Integer.parseInt(input) -1;
                        if(medicalRecordChoice < 0 || medicalRecordChoice >= medicalRecords.size()){
                            System.out.println("Invalid choice.");
                        }
                        else {
                            // confirmation delete record 
                            System.out.println("Selected Medical Record");
                            System.out.println("-------------------------");
                            System.out.println(medicalRecords.get(medicalRecordChoice));
                            System.out.println("-------------------------");
                            System.out.println("Delete medical record? (Y/N): ");
                            if(!getYesOrNoInput()){
                                break;
                            }

                            // remove medical record 
                            String deleteMedicalRecord = medicalRecords.get(medicalRecordChoice).getId();
                            medicalRecords.remove(medicalRecordChoice);

                            //update file 
                            updateMedicalRecord(deleteMedicalRecord);
                            System.out.println("Medical Record deleted");
                            System.out.println("Press <Enter> to return.");
                            SCANNER.nextLine();
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input. Please enter a number.");
                    }
                }
            }
        }
    }

    // update medical record file after delete 
    public static void updateMedicalRecord(String medicalRecordID){
        List<String> updateRecord = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(MEDICAL_RECORD_FILE))){
            String line;
            while((line = br.readLine()) != null){
                if (!line.startsWith(medicalRecordID + "|")) {
                    updateRecord.add(line);
                }
            }
        } catch (IOException e){
            System.out.println("Unable to read records" + e.getMessage());
            return;
        }

        // return back update file 
        try(PrintWriter pr = new PrintWriter(new FileWriter(MEDICAL_RECORD_FILE))){
            for(String update : updateRecord){
                pr.println(update);
            }
        }catch(IOException e){
            System.out.println("Error saving medical recods" + e.getMessage());
        }
    }

    // reporting feature for total number of new patients registered in a selected year and month
    public static void reportNewPatients(){
        clearScreen();
        System.out.println("Report New Patients");
        System.out.println("--------------------");

        // get year and month from user
        int year = 0;
        int month = 0;

        while (true) {
            try {
                System.out.print("Enter year (YYYY): ");
                year = Integer.parseInt(SCANNER.nextLine());

                if (year < 2000 || year > LocalDate.now().getYear()) {
                    System.out.println("Invalid year. Please enter a valid year.");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter month (1-12): ");
                month = Integer.parseInt(SCANNER.nextLine());

                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a valid month.");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // count new patients registered in the selected year and month
        int newPatientsCount = 0;
        for (Patient patient : PATIENTS) {
            LocalDate registrationDate = patient.getRegistrationDate();
            if (registrationDate.getYear() == year && registrationDate.getMonthValue() == month) {
                newPatientsCount++;
            }
        }

        // display the result
        clearScreen();
        System.out.println("Total new patients registered in " + month + "/" + year + ": " + newPatientsCount);
    }

    private static List<Doctor> getTop3Doctors(Department department){
        List<Doctor> doctorDepartment = getDoctorInDepartment(department);

        // check based on experienced (big to small)
        for (int i = 0; i < doctorDepartment.size() - 1; i++) {
            for (int j = 0; j < doctorDepartment.size() - i - 1; j++) {
                if (doctorDepartment.get(j).getYearOfExp() < doctorDepartment.get(j + 1).getYearOfExp()) {
                    Doctor doct = doctorDepartment.get(j);
                    doctorDepartment.set(j, doctorDepartment.get(j + 1));
                    doctorDepartment.set(j + 1, doct);
                }
            }
        }

        // get top 3 
        List<Doctor> top3Doctors = new ArrayList<>();
        for (int i = 0; i < Math.min(3, doctorDepartment.size()); i++) {
            top3Doctors.add(doctorDepartment.get(i));
        }

        return top3Doctors;
    }

    private static void generateTop3ExperienceDoctorsReport(){
        Department department = selectDepartment();

        if(department == null){
            return;
        } 

        List<Doctor> top3Doctors = getTop3Doctors(department);
        
        clearScreen();
        System.out.println("Top 3 Doctors in " + department.getDepartmentName() + " Department:");
        if (top3Doctors.isEmpty()) {
            System.out.println("No doctors found in this department.");
        } else {
            for (int i = 0; i < top3Doctors.size(); i++) {
                Doctor doctor = top3Doctors.get(i);
                System.out.printf("%d. %s (%d years of experience)\n", i + 1, doctor.getName(), doctor.getYearOfExp());
            }
        }
    }

    // get the top 5 most used medication for the selected year and month using the map feature
    private static void generateTop5MedicationsReport(){
        clearScreen();
        System.out.println("Top 5 Medications Report");
        System.out.println("-------------------------");

        // get year and month from user
        int year = 0;
        int month = 0;

        while (true) {
            try {
                System.out.print("Enter year (YYYY): ");
                year = Integer.parseInt(SCANNER.nextLine());

                if (year < 2000 || year > LocalDate.now().getYear()) {
                    System.out.println("Invalid year. Please enter a valid year.");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        while (true) {
            try {
                System.out.print("Enter month (1-12): ");
                month = Integer.parseInt(SCANNER.nextLine());

                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a valid month.");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // read medical records from file
        List<MedicalRecords> medicalRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(MEDICAL_RECORD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] medicalRecordData = line.split("\\|");
                if (medicalRecordData.length >= 7) {
                    LocalDateTime creationDate = LocalDateTime.parse(medicalRecordData[1]);

                    List<Medication> prescribedMedications = new ArrayList<>();
                    readMedications();
                    for (String medicationName : medicalRecordData[5].split(",")) {
                        prescribedMedications.add(Medication.findMedicationByName(medicationName, MEDICATIONS)); // Assuming 0 is the default quantity
                    }
                    if (creationDate.getYear() == year && creationDate.getMonthValue() == month) {
                        MedicalRecords medicalRecord = new MedicalRecords(medicalRecordData[0], creationDate, null, null, null, prescribedMedications, null, null);
                        medicalRecords.add(medicalRecord);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading medical records: " + e.getMessage());
        }

        // count the number of times each medication is prescribed in the selected year and month
        Map<String, Integer> medicationCount = new HashMap<>();
        for (MedicalRecords medicalRecord : medicalRecords) {
            LocalDateTime creationDate = medicalRecord.getCreationDate();
            if (creationDate.getYear() == year && creationDate.getMonthValue() == month) {
                for (Medication medication : medicalRecord.getPrescribedMedications()) {
                    String medicationName = medication.getMedicationName();
                    medicationCount.put(medicationName, medicationCount.getOrDefault(medicationName, 0) + 1);
                }
            }
        }
        // sort the medications by count in descending order
        List<Map.Entry<String, Integer>> sortedMedications = new ArrayList<>(medicationCount.entrySet());
        sortedMedications.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // get top 5 medications
        List<String> top5Medications = new ArrayList<>();
        for (int i = 0; i < Math.min(5, sortedMedications.size()); i++) {
            Map.Entry<String, Integer> entry = sortedMedications.get(i);
            top5Medications.add(entry.getKey() + " (" + entry.getValue() + " times)");
        }

        // display the result
        clearScreen();
        System.out.println("Top 5 Medications in " + month + "/" + year + ":");
        if (top5Medications.isEmpty()) {
            System.out.println("No medications found for this month.");
        } else {
            for (int i = 0; i < top5Medications.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, top5Medications.get(i));
            }
        }
    }

    private static List<Appointment> getMonthlyAppointments(int year, int month){
        List<Appointment> monthlyAppointments = new ArrayList<>();

        for(Appointment appointment: APPOINTMENTS){
            if(appointment.getAppointmentDateTime().getYear() == year && appointment.getAppointmentDateTime().getMonthValue() == month){
                monthlyAppointments.add(appointment);
            }
        }

        return monthlyAppointments;
    }

    //generate monthly appointment report 
    private static void generateMonthlyAppointmentReport(){
        System.out.print("Enter year (e.g 2025): ");
        int year = SCANNER.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = SCANNER.nextInt();
        SCANNER.nextLine();

        List<Appointment> monthlyAppointments = getMonthlyAppointments(year, month);

        System.out.println("Total appointments in " + month + "/" + year + ": " + monthlyAppointments.size());

    }
}
