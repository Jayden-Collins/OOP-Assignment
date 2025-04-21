import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Hospital {
    private static final String STAFF_USERNAME = "StaffAccess";
    private static final String STAFF_PASSWORD = "Staff12345";
    private static final String PATIENT_USERNAME = "PatientAccess";
    private static final String PATIENT_PASSWORD = "Patient12345";

    // file paths 
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";

    Scanner scanner = new Scanner(System.in);
    private String userAccess;

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        hospital.combination();
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
        System.out.println("Hospital Login System.");

        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            if(username.equals(STAFF_USERNAME) && password.equals(STAFF_PASSWORD)){
                userAccess = "Staff";
                System.out.println("Login Successful as staff.");
                break;
            }
            else if(username.equals(PATIENT_USERNAME) && password.equals(PATIENT_PASSWORD)){
                userAccess = "Patient";
                System.out.println("Login Successful as patient.");
                break;
            }
            else{
                System.out.println("Invalid username or password.");
            }

        }
    }

    //doctor management system 
    public void doctorManagement(){
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
        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine();

        // doctor name 
        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();

        //doctor gender 
        System.out.print("Enter Doctor Gender: ");
        String doctorGender = scanner.nextLine();

        // doctor contact number 
        System.out.print("Enter Doctor Contact Number: ");
        String doctorContactNumber = scanner.nextLine();

        // doctor address 
        System.out.print("Enter Doctor Address: ");
        String doctorAddress = scanner.nextLine();

        // doctor years of experience
        System.out.print("Enter Doctor Year of Experience: ");
        String doctoryearOfExp = scanner.nextLine();

        // doctor department
        System.out.print("Enter Doctor Department: ");
        String doctorDepartment = scanner.nextLine();

        //store in string 
        String doctorRecord = String.join("|", doctorID, doctorName, doctorGender, doctorContactNumber, doctorAddress, doctoryearOfExp, doctorDepartment);

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

    //
}