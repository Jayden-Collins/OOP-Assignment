import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Hospital {
    private static final String STAFF_USERNAME = "Staff";
    private static final String STAFF_PASSWORD = "12345";
    private static final String PATIENT_USERNAME = "Patient";
    private static final String PATIENT_PASSWORD = "123456";

    // file paths 
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";

    //consultation room 
    private List<consultationRoom> consultationRooms;
    
    Scanner scanner = new Scanner(System.in);
    private String userAccess;

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.combination();
    }

    //set the rooms 
    private void addRooms(){
        
    }

    // check the available 
    public void 

    //clear screen method 
    public static void clearScreen(){
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

        // doctor id 
        String doctorID;
        while(true){
            System.out.print("Enter Doctor ID: (DC-24-04-001) ");
            doctorID = scanner.nextLine();
            if(ValidationCheck.validateID(doctorID)){
                break;
            } else{
                System.out.println("\nInvalid ID format. Please re-enter: ");
            }
        }

        // doctor name 
        String doctorName;
        while(true){
            System.out.print("Enter Doctor Name: (Desterriman) ");
            doctorName = scanner.nextLine();
            if(ValidationCheck.validateName(doctorName)){
                break;
            } else{
                System.out.println("\nInvalid Name format. Please re-enter: ");
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

        //store in string 
        String doctorRecord = String.join("|", doctorID, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctorYearOfExp, doctorDepartment);

        // store record in doctor file 
        try(FileWriter fw = new FileWriter(DOCTOR_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(doctorRecord);
            System.out.println("\n Doctor Information added successful.");
        } catch (IOException e){
            System.out.println("Error saving doctor information" + e.getMessage());
        }
    }

    // create an array list for the reading file 
    public ArrayList<String[]> getDoctors(){
        ArrayList<String[]> doctors = new ArrayList<>();

        // read from the text file 
        try(BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))){
    
            String line;
            while ((line = br.readLine()) != null){
                String[] doctorRecord1 = line.split("\\|");
                doctors.add(doctorRecord1);
            }
        } catch (FileNotFoundException e){
            
        } catch (IOException e){
            System.out.println("Error reading doctor data: " + e.getMessage());
        }

        return doctors;
    }

    // list doctor information 
    public void listdoctor(){
        ArrayList<String[]> doctors = getDoctors();
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
    public void searchDoctor(){
        System.out.print("Enter doctor id or name to search: ");
        String searchID_Name = scanner.nextLine();

        ArrayList<String[]> doctors = getDoctors();
        boolean exist = false;

        for (String[] doctor: doctors){
            if(doctor[0].equals(searchID_Name) || doctor[1].equals(searchID_Name)){
                System.out.println("Found the information");
                System.out.println("ID: " + doctor[0]);
                System.out.println("Name: " + doctor[1]);
                System.out.println("Gender: " + doctor[2]);
                System.out.println("Contact Number: " + doctor[3]);
                System.out.println("Address: " + doctor[4]);
                System.out.println("Year of Experience: " + doctor[5]);
                System.out.println("Department: " + doctor[6]);
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

        System.out.print("Enter Nurse ID: ");
        String nurseID = scanner.nextLine();

        System.out.print("Enter Nurse Name: ");
        String nurseName = scanner.nextLine();

        System.out.print("Enter Nurse Gender: ");
        String nurseGender = scanner.nextLine();

        System.out.print("Enter Nurse Contact Number: ");
        String nurseContactNumber = scanner.nextLine();

        System.out.print("Enter Nurse Address: ");
        String nurseAddress = scanner.nextLine();

        //join the string together
        String nurseRecord = String.join("|", nurseID, nurseName, nurseGender, nurseContactNumber, nurseAddress);

        // store record in doctor file 
        try(FileWriter fw = new FileWriter(NURSE_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(nurseRecord);
            System.out.println("\n Nurse Information added successful.");
        } catch (IOException e){
            System.out.println("Error saving nurse information" + e.getMessage());
        }

    }

    // read all nurse information and store it at the array list 
    public ArrayList<String[]> getNurse(){
        ArrayList<String[]> nurses = new ArrayList<>();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(NURSE_FILE))){
            
            String line;
            while((line = br.readLine()) != null){
                String[] nurseRecord = line.split("\\|");
                nurses.add(nurseRecord);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e){
            System.out.println("Error reading nurse data: " + e.getMessage());
        }

        return nurses;
    }

    // list nurse information 
    public void listNurse(){
        ArrayList<String[]> nurses = getNurse();
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

    // search nurse information
    public void searchNurse(){
        System.out.print("Enter nurse id or nurse name to search information: ");
        String searchID_Name = scanner.nextLine();

        ArrayList<String[]> nurses = getNurse();
        boolean exist = false;

        for(String[] nurse : nurses){
            if(nurse[0].equals(searchID_Name) || nurse[1].equals(searchID_Name)){
                System.out.println("Found the information");
                System.out.println("ID: " + nurse[0]);
                System.out.println("Name: " + nurse[1]);
                System.out.println("Gender: " + nurse[2]);
                System.out.println("Contact Number: " + nurse[3]);
                System.out.println("Address: " + nurse[4]);
                System.out.println("Year of Experience: " + nurse[5]);
                System.out.println("Department: " + nurse[6]);
                exist = true;
                break;
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

        System.out.println("Enter Patient ID: ");
        String patientID = scanner.nextLine();

        System.out.println("Enter Patient Name: ");
        String patientName = scanner.nextLine();

        System.out.println("Enter Patient Gender: ");
        String patientGender = scanner.nextLine();

        System.out.println("Enter Patient Contact Number: ");
        String patientContactNumber = scanner.nextLine();

        System.out.println("Enter Patient Address: ");
        String patientAddress = scanner.nextLine();

        String patientRecord = String.join("|", patientID, patientName, patientGender, patientContactNumber, patientAddress);

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
    public ArrayList<String[]> getPatient(){
        ArrayList<String[]> patients = new ArrayList<>();

        // read from the file 
        try(BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] patientRecord = line.split("\\|");
                patients.add(patientRecord);
            }
        } catch (FileNotFoundException e ){

        } catch (IOException e){
            System.out.println("Error reading patient information."+ e.getMessage());
        }

        return patients;
    }

    // list for all patient
    public void listPatient(){
        ArrayList<String[]> patients = getPatient();
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
    public void searchPatient(){
        System.out.print("Enter patient id or name to search information: ");
        String searchID_Name = scanner.nextLine();

        ArrayList<String[]> patients = getPatient();
        boolean exist = false;

        for (String[] patient : patients){
            if(patient[0].equals(searchID_Name) || patient[1].equals(searchID_Name)){
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

    
}
