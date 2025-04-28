import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hospital {
    // scanner for user input
    public static final Scanner scanner = new Scanner(System.in);
    private static Role userRole = null;
    private static final String STAFF_USERNAME = "Staff";
    private static final String STAFF_PASSWORD = "12345";
    private static final String PATIENT_USERNAME = "Patient";
    private static final String PATIENT_PASSWORD = "123456";

    // file paths 
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";

    //consultation room 
    private List<Room> consultationRooms;
    
    Scanner scanner = new Scanner(System.in);
    private String userAccess;

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
        Hospital hospital = new Hospital();
        hospital.combination();
    }

    // add room information 
    public void addRoomInformation(){
        consultationRooms.add(new Room("CR-001", "General"));
        consultationRooms.add(new Room("CR-002", "General"));
        consultationRooms.add(new Room("CR-003", "General"));
    }

    //clear screen method 
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

    // patient access page 
    public void patientPage(){
        clearScreen();
        System.out.println("Hi");
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
                System.out.println("\nInvalid Name format. Please re-enter: ");
            }
        }

        //doctor gender 
        String doctorGender;
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

        // doctor contact number 
        String doctorContactNumber;
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

        // doctor address 
        String doctorAddress;
        while(true){
            System.out.print("Enter " + role.getRoleName() + " Address (3, Western Avenue, 11900, Bayan Lepas, Penang): ");
            address = scanner.nextLine();
            if(ValidationCheck.validateAddress(address)){
                personInfo.add(address);
                break;
            } else {
                System.out.println("\nInvalid address format. Please re-enter");
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

        for (String[] doctor : doctors){
            System.out.println("ID: " + doctor[0]);
            System.out.println("Name: " + doctor[1]);
            System.out.println("Gender: " + doctor[2]);
            System.out.println("Contact Number: " + doctor[3]);
            System.out.println("Address: " + doctor[4]);
            System.out.println("Year of Experience: " + doctor[5]);
            System.out.println("Department: " + doctor[6]);
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

        for (String[] nurse: nurses){
            System.out.println("ID: " + nurse[0]);
            System.out.println("Name: " + nurse[1]);
            System.out.println("Gender: " + nurse[2]);
            System.out.println("Contact Number: " + nurse[3]);
            System.out.println("Address: " + nurse[4]);
            System.out.println("Year of Experience: " + nurse[5]);
            System.out.println("Department: " + nurse[6]);
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
        } catch (FileNotFoundException e ){

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

        for (String[] patient : patients){
            System.out.println("ID: " + patient[0]);
            System.out.println("Name: " + patient[1]);
            System.out.println("Gender: " + patient[2]);
            System.out.println("Contact Number: " + patient[3]);
            System.out.println("Address: " + patient[4]);
            System.out.println("Year of Experience: " + patient[5]);
            System.out.println("Department: " + patient[6]);
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
                System.out.println("ID: " + patient[0]);
                System.out.println("Name: " + patient[1]);
                System.out.println("Gender: " + patient[2]);
                System.out.println("Contact Number: " + patient[3]);
                System.out.println("Address: " + patient[4]);
                System.out.println("Year of Experience: " + patient[5]);
                System.out.println("Department: " + patient[6]);
                exist = true;
                break;
            }
        }

        if(!exist){
            System.out.println("Information is not found.");
        }
    }

    //patient management page 


    //View doctor 
    public void viewDoctorList(){
        ArrayList<String[]> doctorLists = getDoctors();

        if(doctorLists.isEmpty()){
            System.out.println("No doctor information is added.");
            return;
        }

        for (String[] doctorList : doctorLists){
            System.out.println("ID: " + doctorList[0]);
            System.out.println("Name: " + doctorList[1]);
            System.out.println("Gender: " + doctorList[2]);
            System.out.println("Contact Number: " + doctorList[3]);
            System.out.println("Address: " + doctorList[4]);
            System.out.println("Year of Experience: " + doctorList[5]);
            System.out.println("Department: " + doctorList[6]);
        }

    }

    //check own information 
    public void checkPersonalInformation(){
        System.out.print("Enter your own id or name to check own information (E.g Desmond/PA): ");
        String search = scanner.nextLine();

        ArrayList<String[]> ownLists = getPatient();
        boolean exist = false;
        boolean update = false;

        for(String[] ownList : ownLists){
            if(ownList[0].equalsIgnoreCase(search) || ownList[0].equalsIgnoreCase(search)){
                System.out.println("ID: " + ownList[0]);
                System.out.println("Name: " + ownList[1]);
                System.out.println("Gender: " + ownList[2]);
                System.out.println("Contact Number: " + ownList[3]);
                System.out.println("Address: " + ownList[4]);
                System.out.println("Year of Experience: " + ownList[5]);
                System.out.println("Department: " + ownList[6]);
                exist = true;
            }
        }

        if(!exist){
            System.out.println("Information not been stored.");
        }
    }

    //check doctor is it available 
    public boolean doctorAvailability(Doctor doctor, LocalDate date){
        List<Appointment> appointments = doctor.getAppointments();
        for(Appointment appointment : appointments){
            if(appointment != null && appointment.getAppointmentDate().equals(date)){
                return false;
            }
        }
        return true;
    }

    // get available rooms 
    public List<Room> getAvailableRoom(String roomType){
        List<Room> available = new ArrayList<>();
        for(Room room : consultationRooms){
            if(room.getAvailable() && (roomType != null || room.getRoomType().equals(roomType))){
                available.add(room);
            }
        }
        return available;
    }

    // find patient by id 
    public Patient findPatientID(String patientID){
        ArrayList<String[]> patients = new ArrayList<>();
        for(String[] patient : patients){
            if(patient[0].equals(patientID)){
                return new Patient(patient[0], patient[1], patient[2], patient[3], patient[4]);
            }
        }
        return null;
    }

    // Book Appointment page
    // (Check patient is it new or exist) (Check available department) (Check available doctor) (Get appointment date and check with available doctor) (check available room) 
    public void bookAppointment(){
        clearScreen();
        System.out.println("Booking Appointment Page");
        
        System.out.println("Visit before our hospital");
        System.out.println("1. New Patient");
        System.out.println("2. Existing Patient");
        System.out.println("3. Exit Appointment page");
        System.out.print("Select your choice: ");
        int patientChoice = scanner.nextInt();
        scanner.nextLine();

        Patient patient;
        //check the choice is it new or exist patient 
        if(patientChoice == 1){
            patient = registerNewPatient();
        } else if (patientChoice ==2 ){
            patient = findExistPatient();
        } else{
            return;
        }

        // check departments, show and select 
        System.out.println("\n Available Department");
        System.out.println("1. Cardiology");
        System.out.println("2. Oncology");
        System.out.println("3. Radiology");
        System.out.print("Select your choice: ");
        int departmentChoice = scanner.nextInt();
        scanner.nextLine();

        String department = getDepartmentChoice(departmentChoice);
        if(department == null){
            System.out.println("Invalid Department selection");
            return;
        }

        // check available doctor 
        System.out.println("Available doctor in " + department + " : ");

        ArrayList<String[]> doctors = getDoctorDepartment(department);
        if(doctors.isEmpty()){
            System.out.println("No doctor is available on this department");
            return;
        }

        // read the doctor array list, get the doctor id , doctor year of exp 
        for (int i = 0; i < doctors.size(); i++){
            System.out.println((i+1) + doctors.get(i)[1] + doctors.get(i)[5] + " years of experience.");
        }
        System.out.println("Select doctor: ");
        int doctorSelection = scanner.nextInt();
        
        // check the doctor selection is it match the size of the doctors array 
        if(doctorSelection < 1 || doctorSelection > doctors.size()){
            System.out.println("Invalid doctor selection");
            return;
        }

        // create array string for selected data to be present 
        String[] selectedDoctor = doctors.get(doctorSelection -1);

        // create a object for selected doctor present
        Doctor doctor = new Doctor(selectedDoctor[0], selectedDoctor[1], selectedDoctor[2], selectedDoctor[3], selectedDoctor[4], selectedDoctor[5], selectedDoctor[6]);

        // get appointment date 
        System.out.print("Enter appointment date (yyyy-mm-dd): ");
        // get date in string format;
        String appointmentDate = scanner.nextLine(); 
        LocalDate appointmentDate1; // default format yyyy-mm-dd 


        //compare the date given is it follow the local date format
        try {
            appointmentDate1 = LocalDate.parse(appointmentDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Re-enter: ");
            return;
        }

        // check doctor is it available
        if(!doctorAvailability(doctor, appointmentDate1)){
            System.out.println("This doctor is not available in this appointment date.");
            return;
        }

        // find available room 
        List<Room> availableRooms = getAvailableRoom(department);

        // no available room check 
        if(availableRooms.isEmpty()){
            System.out.println("No available room in this " + department);
            return;
        }

        // show available room 
        System.out.println("Available Rooms: ");
        for(int i =0;i < availableRooms.size();i++){
            System.out.println((i+1) + ". " + availableRooms.get(i).getRoomID());
        }

        System.out.print("Select Room: ");
        int roomSelection = scanner.nextInt();
        scanner.nextLine();

        //check choice fulfill the size
        if(roomSelection < 1 || roomSelection > availableRooms.size()){
            System.out.println("Invalid room selection");
            return;
        }

        Room selectedRoom = availableRooms.get(roomSelection -1);

        // create appointment
        try {
            Appointment appointment = new Appointment(doctor, patient, appointmentDate1, selectedRoom);

            System.out.println("Appointment Successful");
            System.out.println("AppointmentID: " + appointment.getAppointmentID());
            System.out.println("Doctor: " + doctor.getName());
            System.out.println("Patient: " + patient.getName());
            System.out.println("Appointment Room: " + selectedRoom.getRoomID());
        } catch (Exception e){
            System.out.println("Unable to create the appointment." + e.getMessage());
        }
    }

    //register new patient information
    public Patient registerNewPatient(){
        System.out.println("New Patient Registration\n");

        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();

        System.out.println("Enter Patient Gender (Male/Female): ");
        String patientGender = scanner.nextLine();

        System.out.println("Enter Patient Contact Number: ");
        String patientContactNumber = scanner.nextLine();

        System.out.println("Enter Patient Address: ");
        String patientAddress = scanner.nextLine();

        return new Patient(patientName, patientGender, patientAddress, patientContactNumber, patientAddress);
    }

    // find exist patient 
    public Patient findExistPatient(){
        System.out.print("Enter your patient id: ");
        String compareID = scanner.nextLine();

        Patient patient = findPatientID(compareID);
        if(patient == null){
            System.out.println("Invalid patient if or patient not found");
            return null;
        }

        System.out.println("Information found: " + patient.getName());
        return patient;
    }

    // convert department choice 
    public String getDepartmentChoice(int choice){
        switch (choice){
            case 1:
                return "Cardiology";
            case 2:
                return "Oncology";
            case 3:
                return "Radiology";
            default:
                return null;
        }
    }

    // department choice
    public ArrayList<String[]> getDoctorDepartment(String department){
        ArrayList<String[]> doctors = getDoctors();
        ArrayList<String[]> departmentDoctors = new ArrayList<>();

        for (String[] doctor : doctors){
            if(doctor[6].equalsIgnoreCase(department)){
                departmentDoctors.add(doctor);
            }
        }
        return departmentDoctors;
    }

    // add list for prescribed medications (cardiology, neurology, emergency, oncology, pediatrics)
    public List<String> getPresrcibedMedications(){
        List<String> medications = new ArrayList<>();
        // cardiology 3 medicines
        medications.add("Cardiology : Statin");
        medications.add("Cardiology : ACE Inhibitors");
        medications.add("Cardiology : Beta-Blockers");
        // neurology 3 medicines 
        medications.add("Neurology : Anticonvulsants");
        medications.add("Neurology : Benzodiazepines");
        medications.add("Neurology : Gabapentin");
        // emerngecy have 3 medicines 
        medications.add("Emergency : Glucagon");
        medications.add("Emergency : Glyceryl trinitrate");
        medications.add("Emergency : Epinephrine (adrenaline) pre-filled syringe");
        // oncology have 3 meidicines 
        medications.add("Oncology : cyclophosphamide");
        medications.add("Oncology : doxorubicin");
        medications.add("Oncology : doxorubicin");
        // pediatrics have 3 medicines 
        medications.add("Pediatrics : Cephalexin");
        medications.add("Pediatrics : Amoxicillin");
        medications.add("Pediatrics : Cefdinir");

        return medications;
    }

    //compare all medications and department medications , show specific medications with department
    public List<String> getMedicationDepartment(String department){
        List<String> medications = getPresrcibedMedications();
        List<String> specificMedications = new ArrayList<>();

        for(String medication : medications){
            if(medication.startsWith(department + " : ")){
                specificMedications.add(medication);
            }
        }

        return specificMedications;
    }

    //
    public List<String> getRoomType(){
        List<String> roomTypes = new ArrayList<>();
        // consultation room have 15 rooms 
        // room 1 
        roomTypes.add("Floor 1 : Consultation Room 1");
        roomTypes.add("Floor 1 : Consultation Room 2");
        roomTypes.add("Floor 1 : Consultation Room 3");

        // room 2 
        roomTypes.add("Floor 2 : Consultation Room 4");
        roomTypes.add("Floor 2 : Consultation Room 5");
        roomTypes.add("Floor 2 : Consultation Room 6");
        roomTypes.add("Floor 2 : Consultation Room 7");
        roomTypes.add("Floor 2 : Consultation Room 8");
        roomTypes.add("Floor 2 : Consultation Room 9");

        // room 3 
        roomTypes.add("Floor 3 : Consultation Room 10");
        roomTypes.add("Floor 3 : Consultation Room 11");
        roomTypes.add("Floor 3 : Consultation Room 12");
        roomTypes.add("Floor 3 : Consultation Room 13");
        roomTypes.add("Floor 3 : Consultation Room 14");
        roomTypes.add("Floor 3 : Consultation Room 15");

        return roomTypes;
    }

    // specific floor and consultation room 
    public List<String> getRoomFloor(String id){
        List<String> roomTypes = getRoomType();
        List<String> specificRoom = new ArrayList<>();

        for(String roomType : roomTypes){
            if(roomType.startsWith(id + ":")){
                specificRoom.add(roomType);
            }
        }

        return specificRoom;
    }

    

}
