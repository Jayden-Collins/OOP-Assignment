import java.io.*;
<<<<<<< HEAD
=======
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
>>>>>>> 978cb1f123113df25016e3b744263f835844585c
import java.time.LocalDate;
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

<<<<<<< HEAD
    //consultation room 
    private List<Room> consultationRooms;
    
=======
    // lists for consultation rooms, departments, doctors, nurses, and patients
    private final List<consultationRoom> consultationRooms;
    private final List<Department> departments = new ArrayList<>(); // list of departments
    private final List<Doctor> doctors = new ArrayList<>(); // list of doctors
    private final List<Nurse> nurses = new ArrayList<>(); // list of nurses
    private final List<Patient> patients = new ArrayList<>(); // list of patients

    // scanner for user input
>>>>>>> 978cb1f123113df25016e3b744263f835844585c
    Scanner scanner = new Scanner(System.in);
    private String userAccess;

    public static void main(String[] args) {
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

    // check either is patient or staff 
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

<<<<<<< HEAD
    // patient access page 
    public void patientPage(){
        clearScreen();
        System.out.println("Hi");
    }

    //doctor management system 
=======
    // return a list of departments
    public List<Department> getDepartments(){
        return departments;
    }

    // doctor management system 
>>>>>>> 978cb1f123113df25016e3b744263f835844585c
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
                readDoctors();
                listdoctor();
                break;
            case 3:
                searchDoctor();
                break;
            default:
                System.out.println("Invalid selection");
        }
    }

    // get person information
    public List<String> getPersonInformation(Role role){
        List<String> personInfo = new ArrayList<>();

        System.out.println("Enter " + role + " Information: ");

        String name;
        while(true){
            System.out.print("Enter " + role + " Name (e.g. John Smith): ");
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
            System.out.print("Enter " + role + " IC (e.g. 123456-01-0123): ");
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
            System.out.print("Enter " + role + " Gender (Male/Female): ");
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
            System.out.print("Enter " + role + " Contact Number (012-3456789): ");
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
            System.out.print("Enter " + role + " Address (3, Western Avenue, 11900, Bayan Lepas, Penang): ");
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
                System.out.print("Enter/Select" + role + " Department: ");
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
                System.out.print("Enter " + role + " Year of Experience: ");
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
                System.out.print("Enter " + role + " Emergency Contact Number (012-3456789): ");
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
                addNurseInformation();
                break;
            case 2:
                readNurse();
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
                addPatientInformation();
                break;
            case 2:
                readPatient();
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
        System.out.print("Enter your own id or name to check own information");
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
            }
        }

        if(!exist){
            System.out.println("Information not been stored.");
        }
    }
<<<<<<< HEAD

    //check doctor is it available 
    public boolean doctorAvailability(Doctor doctor, LocalDate date){
        Appointment[] appointments = doctor.getAppointments();
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

=======
>>>>>>> 978cb1f123113df25016e3b744263f835844585c
}
