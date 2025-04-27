import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    // scanner for user input
    public static final Scanner scanner = new Scanner(System.in);
    private static Role userRole = null;

    public static void main(String[] args) {

        // constants file paths 
        final String DEPARTMENT_FILE = "department.txt";
        final String DOCTOR_FILE = "doctor.txt";
        final String NURSE_FILE = "nurse.txt";
        final String PATIENT_FILE = "patient.txt";

        // lists constants for consultation rooms, departments, doctors, nurses, and patients
        final List<Department> departments = new ArrayList<>();
        final List<Doctor> doctors = new ArrayList<>();
        final List<Nurse> nurses = new ArrayList<>();
        final List<Patient> patients = new ArrayList<>();
        final List<Room> consultationRooms = new ArrayList<>();

        // log in page
        checkUserAccess();

        while(true){
            // display staff or patient menu based on user role
            if(Role.isStaff(userRole)){
                displayStaffMenu();
            } else {
                displayPatientMenu();
            }

            // read user input for page selection
            int choice = scanner.nextInt();
            scanner.nextLine(); // continue new line 

            // display page based on choice selected
            if(Role.isStaff(userRole)){
                switch (choice){
                    case 1:
                        doctorManagement();

                        choice = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (choice){
                            // add doctor
                            case 1:
                                clearScreen();
                                Doctor newDoctor = getNewDoctorDetails();
                                doctors.add(newDoctor);
                                storeRecord(DOCTOR_FILE, newDoctor.toFileFormat());
                                break;
                            // list all doctors
                            case 2:
                                clearScreen();
                                // clear doctors array
                                doctors.clear();
                                doctors.addAll(readDoctors(DOCTOR_FILE));
                                listdoctor(doctors);
                                break;
                            // search for doctor
                            case 3:
                                clearScreen();
                                searchDoctor(doctors);
                                break;
                            default:
                                System.out.println("Invalid selection.");
                        }

                        System.out.println("Press <Enter> to continue.");
                        scanner.nextLine();
                        clearScreen();
                        break;
                    case 2:
                        nurseManagement();

                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice){
                            // add nurse
                            case 1:
                                clearScreen();
                                Nurse newNurse = getNewNurseDetails();
                                nurses.add(newNurse);
                                storeRecord(NURSE_FILE, newNurse.toFileFormat());
                                break;
                            // list all nurses
                            case 2:
                                clearScreen();
                                // clear nurse array
                                nurses.clear();
                                nurses.addAll(readNurse(NURSE_FILE));
                                listNurse(nurses);
                                break;
                            // search for nurse
                            case 3:
                                clearScreen();
                                searchNurse(nurses);
                                break;
                            default:
                                System.out.println("Invalid selection. Re-enter");
                        }

                        clearScreen();
                        break;
                    case 3:
                        patientManagement();

                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice){
                            case 1:
                                clearScreen();
                                Patient newPatient = getNewPatientDetails();
                                patients.add(newPatient);
                                storeRecord(PATIENT_FILE, newPatient.toFileFormat());
                                break;
                            case 2:
                                clearScreen();
                                // clear patient array
                                patients.clear();
                                patients.addAll(readPatient(PATIENT_FILE));
                                listPatient(patients);
                                break;
                            case 3:
                                clearScreen();
                                searchPatient(patients);
                                break;
                            case 4:
                                clearScreen();
                                System.exit(0);
                            default:
                                System.out.println("Invalid selection. Please re-enter");
                        }

                        clearScreen();
                        break;
                    case 5:
                        System.out.println("Closed Program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid selection. Re-enter");
                }
            } else {
                switch(choice){
                    case 1:
                        clearScreen();
                        System.out.println("Closed Program");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid selection. Re-enter");
                }
            }
        }
    }

    // set the rooms 
    private static void addRooms(){ 
    }

    // clear screen method 
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void makeAppointment()
    {
        System.out.println("Appointment created successfully.");
    }

    // log in page that checks whether the user is a patient or staff 
    public static void checkUserAccess(){

        // constans for username and password for staff and patient
        final String STAFF_USERNAME = "Staff";
        final String STAFF_PASSWORD = "12345";
        final String PATIENT_USERNAME = "Patient";
        final String PATIENT_PASSWORD = "123456";
        
        clearScreen();
        System.out.println("Hospital Login System.");

        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if(username.equals(STAFF_USERNAME) && password.equals(STAFF_PASSWORD)){
                userRole = Role.STAFF;
                clearScreen();
                System.out.println("Log In Successful!");
                break;
            }
            else if(username.equals(PATIENT_USERNAME) && password.equals(PATIENT_PASSWORD)){
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

    // display staff menu
    public static void displayStaffMenu(){
        Hospital.clearScreen();
        System.out.println("Staff Page");
        System.out.println("1. Doctor Management ");
        System.out.println("2. Nurse Management ");
        System.out.println("3. Patient Management");
        System.out.println("4. Generate Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // display patient menu
    public static void displayPatientMenu(){
        Hospital.clearScreen();
        System.out.println("Patient Page");
        System.out.println("1. View Doctor");
        System.out.println("2. Check own Information.");
        System.out.println("3. Book Appoinments");
        System.out.println("4. View Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // get new person information and return as string list
    public static List<String> getPersonInformation(Role role){
        List<String> personInfo = new ArrayList<>();

        System.out.println("Enter " + role.getRoleName() + " Information: ");

        personInfo.add(getPersonName(role));

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
    
    // get person name
    public static String getPersonName(Role role){
        String name;

        while(true){
            System.out.print("Enter " + role.getRoleName() + " Name (e.g. John Smith): ");
            name = scanner.nextLine();
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
            System.out.print("Enter " + role.getRoleName() + " ID (e.g. 123456): ");
            id = scanner.nextLine();
            if(ValidationCheck.validateID(id)){
                return id;
            } else {
                System.out.println("\nInvalid ID format. Please re-enter: ");
            }
        }
    }

    // store record in file
    public static void storeRecord(String file, String record){
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(record);
            System.out.println("\nNew doctor information added successfully.");
        } catch (IOException e){
            System.out.println("Error saving doctor information." + e.getMessage());
        }
    }

    // add department
    public static void addDepartment(){
        // read department name

        // create department object

        // write department to file
    }
    
    // doctor management system 
    public static void doctorManagement(){
        clearScreen();
        System.out.println("Doctor Management");
        System.out.println("1. Add Doctor Information.");
        System.out.println("2. List all doctor");
        System.out.println("3. Search Doctor");
        System.out.print("Choose option: ");
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
    public static void searchDoctor(List<Doctor> doctors){
        String search;

        while (true){
            System.out.println("Search by:");
            System.out.println("1. Doctor ID");
            System.out.println("2. Doctor Name");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")){
                search = getPersonId(Role.DOCTOR);
                break;
            } else if (choice.equals("2")){
                search = getPersonName(Role.DOCTOR);
                break;
            } else {
                System.out.println("Invalid choice. Please re-enter.");
            }
        }

        boolean exist = false;

        for (Doctor doctor: doctors){
            if(doctor.getId().equals(search) || doctor.getName().equalsIgnoreCase(search)){
                System.out.println("Found the information.");
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
    public static void deleteDoctor(){

    }

    //nurse management system
    public static void nurseManagement(){
        System.out.println("Nurse Management");
        System.out.println("1. Add Nurse Information");
        System.out.println("2. List all nurse");
        System.out.println("3. Search Nurse");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter choice: : ");
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
    public static void searchNurse(List<Nurse> nurses){
        String search;

        while (true){
            System.out.println("Search by:");
            System.out.println("1. Nurse ID");
            System.out.println("2. Nurse Name");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")){
                search = getPersonId(Role.NURSE);
                break;
            } else if (choice.equals("2")){
                search = getPersonName(Role.NURSE);
                break;
            } else {
                System.out.println("Invalid choice. Please re-enter.");
            }
        }

        boolean exist = false;

        for (Nurse nurse: nurses){
            if(nurse.getId().equals(search) || nurse.getName().equalsIgnoreCase(search)){
                System.out.println("Found the information.");
                System.out.println(nurse);
                exist = true;
                break;
            }
        }

        if (!exist){
            System.out.println("Information is not found.");
        }
    }
    
    //patient managment system
    public static void patientManagement(){
        System.out.println("Patient Management");
        System.out.println("1. Add Patient Information.");
        System.out.println("2. List all patient information");
        System.out.println("3. Search Information");
        System.out.println("4. Back to Main Menu");
        System.out.println("Enter choice: ");
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
    public static void searchPatient(List<Patient> patients){
        String search;

        while (true){
            System.out.println("Search by:");
            System.out.println("1. Patient ID");
            System.out.println("2. Patient Name");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")){
                search = getPersonId(Role.PATIENT);
                break;
            } else if (choice.equals("2")){
                search = getPersonName(Role.PATIENT);
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

}
