import java.io.*;
import java.time.DayOfWeek;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.print.attribute.standard.Media;

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

    // constants file paths 
    private static final String DEPARTMENT_FILE = "department.txt";
    private static final String DOCTOR_FILE = "doctor.txt";
    private static final String NURSE_FILE = "nurse.txt";
    private static final String PATIENT_FILE = "patient.txt";
    private static final String MEDICAL_RECORD_FILE = "medical_records.txt";

    //consultation room 
    private List<Room> consultationRooms;
    private static final String ROOM_FILE = "room.txt";
    private static final String APPOINTMENT_FILE = "appointment.txt";

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
                readAppointments();
                readDepartments();
                readDoctors();
                readNurse();
                readPatient();
                readRooms();

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
                                    System.out.println("\nNew Doctor Details:");
                                    System.out.println(newDoctor);
                                    System.out.println("Save new doctor information?");
                                    if (getYesOrNoInput()){
                                        DOCTORS.add(newDoctor);
                                        storeRecord(DOCTOR_FILE, newDoctor.toFileFormat());
                                        System.out.println("\nNew doctor added successfully.");
                                    }
                                }

                                // list all doctors
                                else if (choice == 2){
                                    clearScreen();
                                    readDepartments();
                                    readDoctors();
                                    listdoctor();
                                }

                                // search for doctor
                                else if (choice == 3){
                                    clearScreen();
                                    readDoctors();
                                    Doctor doctor = searchDoctor();
                                    if (doctor != null){
                                        System.out.println("\nMatching record found!");
                                        System.out.println("-----------------------");
                                        System.out.println(doctor);
                                        System.out.println("-----------------------");
                                        System.out.print("Press any key to continue.");
                                        SCANNER.nextLine();
                                    }
                                }

                                // delete doctor record
                                else if (choice == 4){
                                    readDoctors();
                                    Doctor doctor = searchDoctor();
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
                                        }
                                    } else {
                                        System.out.println("Doctor information not found.");
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
                                                System.out.println("\nPress any key to continue.");
                                                SCANNER.nextLine();
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

                                if (choice != 6 || choice != 3){
                                    System.out.print("\nPress any key to continue.");
                                    SCANNER.nextLine();
                                }
                            }
                            if (choice != 6){
                                System.out.print("\nPress any key to continue.");
                                SCANNER.nextLine();
                                break;
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
                                    System.out.println("\nNew Nurse Details:");
                                    System.out.println(newNurse);
                                    System.out.println("Save new nurse information?");
                                    if (getYesOrNoInput()){
                                        NURSES.add(newNurse);
                                        storeRecord(NURSE_FILE, newNurse.toFileFormat());
                                        System.out.println("\nNew nurse registered successfully.");
                                    }
                                }

                                // list all nurses
                                else if (choice == 2){
                                    clearScreen();
                                    readNurse();
                                    listNurse();
                                }

                                // search nurse
                                else if (choice == 3){
                                    clearScreen();
                                    readNurse();
                                    Nurse nurse = searchNurse();
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
                                    readNurse();
                                    Nurse nurse = searchNurse();
                                    if(nurse != null){
                                        clearScreen();
                                        System.out.println("Matching Record Found!");
                                        System.out.println("\n Nurse Details:");
                                        System.out.println(nurse);
                                        System.out.println("Delete nurse record?");
                                        if (getYesOrNoInput()){
                                            NURSES.remove(nurse);
                                            overwriteFile(NURSE_FILE, convertToFileFormat(new ArrayList<>(NURSES)));
                                            System.out.println("\nNurse information deleted successfully.");
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
                                System.out.print("\nPress any key to continue.");
                                SCANNER.nextLine();
                                break;
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
                                        break;
                                    } else {
                                    PATIENTS.add(newPatient);
                                    storeRecord(PATIENT_FILE, newPatient.toFileFormat());
                                    System.out.println("\nNew patient registered successfully.");
                                    }
                                }

                                // list all patient
                                else if (choice == 2){
                                    clearScreen();
                                    readPatient();
                                    listPatient();
                                }
                                
                                // search for patient
                                else if (choice == 3){
                                    clearScreen();
                                    readPatient();
                                    Patient patient = searchPatient();
                                    if (patient == null){
                                        break;
                                    }
                                    else {
                                        System.out.println("Matching record found!");
                                        System.out.println(patient);
                                    }
                                }

                                // delete patient record
                                else if (choice == 4){
                                    clearScreen();
                                    readPatient();
                                    Patient patient = searchPatient();
                                    if (patient == null){
                                        break;
                                    }
                                    else {
                                        clearScreen();
                                        System.out.println("Matching Record Found!");
                                        System.out.println("\nPatient Details:");
                                        System.out.println(patient);
                                        System.out.println("Delete patient record?");
                                        if (getYesOrNoInput()){
                                            PATIENTS.remove(patient);
                                            overwriteFile(PATIENT_FILE, convertToFileFormat(new ArrayList<>(PATIENTS)));
                                            System.out.println("\nPatient information deleted successfully.");
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

                                if (choice != 5){
                                    System.out.print("\nPress any key to continue.");
                                    SCANNER.nextLine();
                                }
                            }

                            if (choice != 5){
                                System.out.print("\nPress any key to continue.");
                                SCANNER.nextLine();
                                break;
                            }
                        }
                        // department management page
                        else if (choice == 5){
                            while(true){
                                departmentManagement();    
                                choice = getChoice();

                                // add department
                                if (choice == 1){
                                    clearScreen();
                                    Department newDepartment = getNewDepartmentDetails();
                                    System.out.println(newDepartment);
                                    if (getYesOrNoInput()){
                                        DEPARTMENTS.add(newDepartment);
                                        storeRecord(DEPARTMENT_FILE, newDepartment.toFileFormat());
                                        System.out.println("\nNew department registered successfully.");
                                    }
                                }

                                // list all departments
                                else if (choice == 2) {
                                    clearScreen();
                                    // clear department array
                                    readDepartments();
                                    listDepartments(DEPARTMENTS);
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
                                System.out.print("\nPress any key to continue.");
                                SCANNER.nextLine();
                                break;
                            }
                        }
                        else if (choice == 6){
                            System.out.println("\nLog Out?\n");
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
                                    System.out.println("\nMatching record found!");
                                    System.out.println(patient);
                                    System.out.println("------------------------");
                                    System.out.println("1. Update Personal Information");
                                    System.out.println("2. Back");
                                    System.out.println("------------------------");

                                    choice = getChoice();

                                    // update personal information
                                    if (choice == 1){
                                        while(true){
                                            clearScreen();
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

                                    choice = getChoice();

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
                                        break;
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
                                                continue;
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
                                                                                                    break;
                                                                                                } catch (Exception e){
                                                                                                    System.out.println("Unable to create the appointment.\nPlease try again." + e.getMessage());
                                                                                                }
                                                                                            } else {
                                                                                                break;
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
                                                                    break;
                                                                }
                                                                }
                                                            catch (Exception e) {
                                                                clearScreen();
                                                                System.out.println("Invalid date format.\nPlease re-enter.");
                                                            }
                                                        }
                                                        break;
                                                    } else if (choice == availableDoctors.size() + 1){
                                                        break;
                                                    } else {
                                                        System.out.println("Invalid selection. Please re-enter.");
                                                    }
                                                }
                                                break;
                                            }   
                                        }
                                        break;
                                    }
                                }  
                                
                                //if (patient == null){
                                //    break;
                                //}

                                break;
                            }
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
                                        System.out.println("Patient's Appointments:");
                                        System.out.println("-----------------------");
                                        for (int i = 0; i < appointments.size(); i++){
                                            System.out.println("Appointment #" + (i + 1));
                                            System.out.println(appointments.get(i));
                                            System.out.println("-----------------------");
                                        }
                                        System.out.println("\nPress any key to continue.");
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

                        if (choice != 5 || choice != 3){
                            System.out.print("\nPress any key to continue.");
                            SCANNER.nextLine();
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
                System.out.println("Invalid choice input.\nPlease enter a number.\n");
            }
        }
    }

    // add the rooms 
    private static void addRooms(){
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
                System.out.println("\nOperation cancelled.");
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
        System.out.println("---------Hospital Login System-----------");

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
        System.out.println("4. Generate Medical Report");
        System.out.println("5. Department Management");
        System.out.println("6. Log Out");
        System.out.println("----------");
        System.out.println("4. Medical Record Management");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
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
            System.out.print("Enter " + role.getRoleName() + " ID (e.g. " + role.getRoleIdExample() + "): ");
            String id = SCANNER.nextLine().trim();
            if(ValidationCheck.validateID(id, role.getRoleName())){
                return id;
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
            System.out.print("Enter " + role.getRoleName() + " Name (e.g. John Smith): ");
            String name = SCANNER.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return name;
            } else{
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
            System.out.print("Enter " + role.getRoleName() + " IC (e.g. 123456-01-0123): ");
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
            if(ValidationCheck.validateGender(gender)){
                return gender;
            } else{
                clearScreen();
                System.out.println("\nInvalid Gender format. Please re-enter: ");
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

    // get staff department
    private static Department getStaffDepartment(Role role){
        System.out.print("Select" + role.getRoleName() + "Department");
        return selectDepartment();
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
    private static void listDepartments(List<Department> departments){
        if(departments.isEmpty()){
            System.out.println("No departments added yet.");
            return;
        }

        for (Department department : departments){
            System.out.println(department);
        }
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
            System.out.print("Enter New Department Name (e.g. Orthopedics): ");
            name = SCANNER.nextLine().trim();
            if(ValidationCheck.validateName(name)){
                return new Department(name);
            } else{
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
        String doctorIc = getPersonIc(Role.DOCTOR);
        String doctorGender = getPersonGender(Role.DOCTOR);
        String doctorContactNumber = getPersonContactNumber(Role.DOCTOR);
        String doctorAddress = getPersonAddress(Role.DOCTOR);
        Department doctorDepartment = getStaffDepartment(Role.DOCTOR);
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

        TablePrinter table = new TablePrinter(Arrays.asList("ID", "IC", "Name", "Gender", "Contact Number", "Address", "Year of Experience", "Department"));

        // Print each doctor's information
        for(Doctor doctor : doctors) {
            table.addRow(Arrays.asList(doctor.getId(), doctor.getIc(), doctor.getName(), doctor.getGender(), doctor.getContactNumber(), doctor.getAddress(), String.valueOf(doctor.getYearOfExp()), doctor.getDepartment()));
        }

        table.print();
        for (int i = 0; i < DOCTORS.size(); i++){
            System.out.println("Doctor #" + (i + 1));
            System.out.println(DOCTORS.get(i));
        }
    }

    // search doctor 
    private static Doctor searchDoctor(){
        String search;

        while (true){
            System.out.println("Search by:");
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
                System.out.println("Invalid choice. Please re-enter.");
            }
        }

        for (Doctor doctor: DOCTORS){
            if(doctor.getId().equals(search)
            || doctor.getIc().equals(search)
            || doctor.getName().equalsIgnoreCase(search)){
                return doctor;
            }
        }

        System.out.println("Doctor record not found.\nPlease try again.\n");
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
        String nurseIC = getPersonIc(Role.NURSE);
        String nurseGender = getPersonGender(Role.NURSE);
        String nurseContactNumber = getPersonContactNumber(Role.NURSE);
        String nurseAddress = getPersonAddress(Role.NURSE);
        Department nurseDepartment = getStaffDepartment(Role.NURSE);
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
                NURSES.add(new Nurse(nurseRecord[0], nurseRecord[1], nurseRecord[2], nurseRecord[3], nurseRecord[4], findDepartmentById(nurseRecord[5]), Integer.parseInt(nurseRecord[6])));
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

        for (Nurse nurse: NURSES){
            System.out.println(nurse);
        }
    }

    // search nurse 
    private static Nurse searchNurse(){
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
                search = getPersonIc(Role.NURSE);
                break;
            } else if (choice == 3){
                search = getPersonName(Role.NURSE);
                break;
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

        return null;
    }
    
    //patient managment system
    private static void patientManagement(){
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
    private static Patient getNewPatientDetails(){
        String patientName = getPersonName(Role.PATIENT);
        String patientIC = getPersonIc(Role.PATIENT);
        String patientGender = getPersonGender(Role.PATIENT);
        String patientContactNumber = getPersonContactNumber(Role.PATIENT);
        String patientAddress = getPersonAddress(Role.PATIENT);
        String patientEmergencyContact = getPersonEmergencyContactNumber(Role.PATIENT);

        Patient newPatient = new Patient(patientIC, patientName, patientGender, patientContactNumber, patientAddress, patientEmergencyContact);

        clearScreen();
        System.out.println("\nNew Patient Details:");
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
                PATIENTS.add(new Patient(patientRecord[0], patientRecord[1], patientRecord[2], patientRecord[3], patientRecord[4], patientRecord[5], patientRecord[6]));
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

        for (Patient patient : PATIENTS){
            System.out.println(patient);
        }
    }

    // search for patient 
    private static Patient searchPatient(){
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

    /*
    Book Appointment page
    Check whether patient is new or exist
    Check available department
    Check available doctor
    Get appointment date and check with available doctor
    Check available room
    */
    private static void bookAppointment(){
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

    // medication list 
    public List<String> medicationLists(){
        List<String> medicationLists = new ArrayList<>();
        medicationLists.add("Statin");
        medicationLists.add("ACE Inhibitors");
        medicationLists.add("Beta-Blockers");
        medicationLists.add("Anticonvulsants");
        medicationLists.add("Benzodiazepines");
        medicationLists.add("Gabapentin");
        medicationLists.add("Glucagon");
        medicationLists.add("Glyceryl trinitrate");
        medicationLists.add("Epinephrine (adrenaline) pre-filled syringe");
        medicationLists.add("cyclophosphamide");
        medicationLists.add("doxorubicin");
        medicationLists.add("doxorubicin");
        medicationLists.add("Cephalexin");
        medicationLists.add("Amoxicillin");
        medicationLists.add("Cefdinir");

        return medicationLists;
    }

    // add list for prescribed medications (cardiology, neurology, emergency, oncology, pediatrics)
    // search medication first, enter dosage store in list
    // default dosage and custom dosage
    public List<String> getPresrcibedMedications(){
        List<String> medicationLists = medicationLists();
        // store the medication the l
        List<String> medications = new ArrayList<>();
        // search by medication 
        System.out.print("Enter your medication: ");
        String medication = scanner.nextLine();
        
        // for each loop for the medication lists 
        for(String medicationList : medicationLists){
            if(medication == medicationList){
                // enter default dose or custom dose 
                System.out.println("Enter your doses(default dose = 1 / custom dose): ");
                String dose = scanner.nextLine();
                try {
                    if(dose == "1"){
                        //cardiology 3 medicines 
                        medications.add("Cardiology : Statin, dosage = 1mg");
                        medications.add("Cardiology : ACE Inhibitors, dosage = 1mg");
                        medications.add("Cardiology : Beta-Blockers, dosage = 1mg");
                        
                        // neurology 3 medicines 
                        medications.add("Neurology : Anticonvulsants, dosage = 1mg");
                        medications.add("Neurology : Benzodiazepines, dosage = 1mg");
                        medications.add("Neurology : Gabapentin, dosage = 1mg");
                        
                        // emerngecy have 3 medicines 
                        medications.add("Emergency : Glucagon, dosage = 1mg");
                        medications.add("Emergency : Glyceryl trinitrate, dosage = 1mg");
                        medications.add("Emergency : Epinephrine (adrenaline) pre-filled syringe, dosage = 1ml");
                        
                        // oncology have 3 meidicines 
                        medications.add("Oncology : cyclophosphamide, dosage = 1ml");
                        medications.add("Oncology : doxorubicin, dosage = 1ml");
                        medications.add("Oncology : rituximab, dosage = 1ml");

                        // pediatrics have 3 medicines 
                        medications.add("Pediatrics : Cephalexin, dosage = 1ml");
                        medications.add("Pediatrics : Amoxicillin, dosage = 1ml");
                        medications.add("Pediatrics : Cefdinir, dosage = 1ml");

                    } else if (medication == "Statin"){
                        medications.add("Cardiology : Statin, dosage = " + dose + "mg");
                    } else if (medication == "ACE Inhibitors"){
                        medications.add("Neurology : Benzodiazepines, dosage = " + dose + "mg");
                    } else if (medication == "Beta-Blockers"){
                        medications.add("Cardiology : Beta-Blockers, dosage = " + dose + "mg");
                    } else if (medication == "Anticonvulsants"){
                        medications.add("Neurology : Anticonvulsants, dosage = " + dose + "mg");
                    } else if (medication == "Benzodiazepines"){
                        medications.add("Neurology : Benzodiazepines, dosage = " + dose + "mg");
                    } else if (medication == "Gabapentin"){
                        medications.add("Neurology : Gabapentin, dosage = " + dose + "mg");
                    } else if (medication == "Glucagon"){
                        medications.add("Emergency : Glucagon, dosage = " + dose + "mg");
                    } else if (medication == "Glyceryl trinitrate"){
                        medications.add("Emergency : Glyceryl trinitrate, dosage = " + dose + "mg");
                    } else if (medication == "Epinephrine (adrenaline) pre-filled syringe"){
                        medications.add("Emergency : Epinephrine (adrenaline) pre-filled syringe, dosage = " + dose + "mg");
                    } else if (medication == "cyclophosphamide"){
                        medications.add("Oncology : cyclophosphamide, dosage = " + dose + "mg");
                    } else if (medication == "doxorubicin"){
                        medications.add("Oncology : doxorubicin, dosage = " + dose + "mg");
                    } else if (medication == "rituximab"){
                        medications.add("Oncology : rituximab, dosage = " + dose + "mg");
                    } else if (medication == "Cephalexin"){
                        medications.add("Pediatrics : Cephalexin, dosage = " + dose + "mg");
                    } else if (medication == "Amoxicillin"){
                        medications.add("Pediatrics : Amoxicillin, dosage = " + dose + "mg");
                    } else if (medication == "Cefdinir"){
                        medications.add("Pediatrics : Cefdinir, dosage = " + dose + "mg");
                    }

                } catch (Exception e){
                    System.out.println("Invalid String format input");
                }
            }
        }
        
        return medications;
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
    private static List<Room> getRoomFloor(int floor){
        List<Room> roomsOnFloor = new ArrayList<>();
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

    // consultation room list 
    public List<String> roomTypeList(){
        List<String> roomTypeList = new ArrayList<>();

        // store information into the array list 
        roomTypeList.add("Consultation Room 1");
        roomTypeList.add("Consultation Room 2");
        roomTypeList.add("Consultation Room 3");
        roomTypeList.add("Consultation Room 4");
        roomTypeList.add("Consultation Room 5");
        roomTypeList.add("Consultation Room 6");
        roomTypeList.add("Consultation Room 7");
        roomTypeList.add("Consultation Room 8");
        roomTypeList.add("Consultation Room 9");
        roomTypeList.add("Consultation Room 10");
        roomTypeList.add("Consultation Room 11");
        roomTypeList.add("Consultation Room 12");
        roomTypeList.add("Consultation Room 13");
        roomTypeList.add("Consultation Room 14");
        roomTypeList.add("Consultation Room 15");

        return roomTypeList;
    }

    //list of floor by room 
    public List<String> getRoomType(){
        // room type lists from the previous room type method 
        List<String> roomLists = roomTypeList();
        
        // store custom room type lists 
        List<String> roomTypeLists = new ArrayList<>();

        //String for room type needed
        System.out.print("Enter your room: ");
        String roomType = scanner.nextLine();

        // consultation room have 15 rooms 
        // room 1 
        roomTypeLists.add("Floor 1 : Consultation Room 1");
        roomTypeLists.add("Floor 1 : Consultation Room 2");
        roomTypeLists.add("Floor 1 : Consultation Room 3");

        // room 2 
        roomTypeLists.add("Floor 2 : Consultation Room 4");
        roomTypeLists.add("Floor 2 : Consultation Room 5");
        roomTypeLists.add("Floor 2 : Consultation Room 6");
        roomTypeLists.add("Floor 2 : Consultation Room 7");
        roomTypeLists.add("Floor 2 : Consultation Room 8");
        roomTypeLists.add("Floor 2 : Consultation Room 9");

        // room 3 
        roomTypeLists.add("Floor 3 : Consultation Room 10");
        roomTypeLists.add("Floor 3 : Consultation Room 11");
        roomTypeLists.add("Floor 3 : Consultation Room 12");
        roomTypeLists.add("Floor 3 : Consultation Room 13");
        roomTypeLists.add("Floor 3 : Consultation Room 14");
        roomTypeLists.add("Floor 3 : Consultation Room 15");

        return roomTypeLists;
    }

    // specific floor and consultation room 
    public List<String> getRoomFloor(String id){
        List<String> roomTypes = getRoomType();
        List<String> specificRoom = new ArrayList<>();

        for(Room room : CONSULTATION_ROOMS){
            if(room.onFloor(floor)){
                roomsOnFloor.add(room);
            }
        }

        return roomsOnFloor;
    }

    // medical records management page for staff
    public static void medicalRecordManagment(){
        clearScreen();
        System.out.println("--------Medical Record Management--------");
        System.out.println("1. Generate Medical Record");
        System.out.println("2. View Medical Record");
        System.out.println("3. Delete Medical Record");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice){
            case 1:
                clearScreen();
                generateMedicalRecord();
                System.out.println("Press <Enter> to continue");
                scanner.nextLine();
                break;
            case 2:
                clearScreen();
                viewMedicalRecord();
                System.out.println("Press <Enter> to continue");
                scanner.nextLine();
                break;
            case 3:
                clearScreen();
                deleteMedicalRecord();
                System.out.println("Press <Enter> to continue");
                scanner.nextLine();
                break;
            case 4:
                System.out.println("Closed Program");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Re-enter");

        }
    
    }

    //generate medical records 
    public static void generateMedicalRecord(){
        clearScreen();
        System.out.println("============Generate Medical Records============");
        
        //list patients to choose 
        List<Patient> patients = readPatient(PATIENT_FILE);
        if(patients.isEmpty()){
            System.out.println("No patient informations are available");
            return;
        }

        // list out patients information, key in patient id 
        listPatient(patients);
        System.out.print("Select patient ID: ");
        String patientID = scanner.nextLine();

        // selected patient object 
        Patient selectedPatient = null;

        for(Patient patient : patients){
            if(patient.getId().equals(patientID)){
                selectedPatient = patient;
                break;
            }
        }

        if(selectedPatient == null){
            System.out.println("Patient not found");
            return;
        }

        //List doctors to choose 
        List<Doctor> doctors = readDoctors(DOCTOR_FILE);
        if(doctors.isEmpty()){
            System.out.println("No doctor informations are available");
            return;
        }

        listdoctor(doctors);
        System.out.print("Select doctor ID: ");
        String doctorID = scanner.nextLine();

        //selected doctor object 
        Doctor selectedDoctor = null;

        for(Doctor doctor : doctors){
            if(doctor.getId().equals(doctorID)){
                selectedDoctor = doctor;
                break;
            }
        }

        if(selectedDoctor == null){
            System.out.println("Doctor not found");
            return;
        }

        //medical records new object
        MedicalRecords medicalRecord = new MedicalRecords(selectedPatient, selectedDoctor);

        // diagnoses record 
        System.out.print("Enter diagnoses: ");
        String diagnoses = scanner.nextLine();
        medicalRecord.addDiagnosis(diagnoses);

        // medications record 
        System.out.print("Enter medications: ");
        String medication = scanner.nextLine();
        medicalRecord.prescribeMedications(medication);

        // treatment history records 
        System.out.print("Enter treatment history: ");
        String treatment = scanner.nextLine();
        medicalRecord.addTreatmentNote(treatment);

        // follow up date records 
        System.out.print("Enter follow up date(yyyy-mm-dd)");
        String followUp = scanner.nextLine();
        if(!followUp.isEmpty()){
            try {
                LocalDateTime followUpDate = LocalDate.parse(followUp).atStartOfDay();
                medicalRecord.addFollowUp(followUpDate);
            } catch (Exception e) {
                System.out.println("Invalid date format. Re-enter");
            }
        }

        // save medical records 
        saveMedicalRecord(medicalRecord);
        System.out.println("==================================");
        System.out.println("Medical Record create successfully.");
    }

    // save medical records 
    public static void saveMedicalRecord(MedicalRecords medicalRecords){
        try(FileWriter fw = new FileWriter(MEDICAL_RECORD_FILE, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){
            out.println(medicalRecords.getID() + "|" + medicalRecords.getPatient().getId() + "|" + medicalRecords.getDoctor().getId() + "|" + medicalRecords.getCreationDate() + "|" + medicalRecords.getDiagnoses() + "|" + medicalRecords.getPrescribedMedications() + "|" + medicalRecords.getNextFollowUp());
            System.out.println("\nNew medical record information added successfully.");
        } catch (IOException e){
            System.out.println("Error saving medical records." + e.getMessage());
        }
    }

    // view medical records for patients 
    public static void viewMedicalRecord(){
        clearScreen();
        System.out.println("--------View Medical Record----------");

        System.out.print("Enter own patient ID: ");
        String patientID = scanner.nextLine();

        // List for a medical records based on patient id
        List<MedicalRecords> medicalRecords = readPatientByPatientMedicalRecord(patientID);

        // check if the medical records for a patient is it exist or not
        if(medicalRecords.isEmpty()){
            System.out.println("No medical records found for this patient from it's own id");
            return;
        }

        System.out.println("--------Medical Record--------");
        for(MedicalRecords medicalRecord : medicalRecords){
            System.out.println(medicalRecord.toString());
        }
    }

    // read patient by patient medical records based on the patient id 
    public static List<MedicalRecords> readPatientByPatientMedicalRecord(String patientID){
        List<MedicalRecords> medicalRecords = new ArrayList<>();
        List<Patient> patients = readPatient(PATIENT_FILE);
        List<Doctor> doctors = readDoctors(DOCTOR_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(MEDICAL_RECORD_FILE))){
            String line;
            while((line = br.readLine()) != null){
                String[] medicalrecord = line.split("\\|");
                if(medicalrecord.length >= 7 && medicalrecord[1].equals(patientID)){
                    // find patient and doctor 
                    Patient patient = null;
                    Doctor doctor = null;

                    //check patient ID 
                    for(Patient pat : patients){
                        if(pat.getId().equals(medicalrecord[1])){
                            patient = pat;
                            break;
                        }
                    }

                    //check doctor id 
                    for(Doctor doct : doctors){
                        if(doct.getId().equals(medicalrecord[2])){
                            doctor = doct;
                            break;
                        }
                    }

                    if(patient != null && doctor != null){
                        MedicalRecords medicalRecord = new MedicalRecords(patient, doctor);

                        // check for the diagnoses
                        if(!medicalrecord[3].isEmpty()){
                            medicalRecord.addDiagnosis(medicalrecord[3]);
                        }

                        // check for medications
                        if(!medicalrecord[4].isEmpty()){
                            medicalRecord.prescribeMedications(medicalrecord[4]);
                        }

                        // check for treatment history 
                        if(!medicalrecord[5].isEmpty()){
                            medicalRecord.addTreatmentNote(medicalrecord[5]);
                        }

                        // check for follow up date 
                        if(!medicalrecord[6].isEmpty()){
                            try {
                                LocalDateTime followUpDate = LocalDate.parse(medicalrecord[6]).atStartOfDay();
                                medicalRecord.addFollowUp(followUpDate);
                            } catch (Exception e) {
                                System.out.println("Invalid date format. Re-enter");
                            }
                        }

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
        System.out.println("--------Delete Medical Record--------");

        List<Patient> patients = readPatient(PATIENT_FILE);
        if(patients.isEmpty()){
            System.out.println("No patients is found in the file");
            return;
        }

        listPatient(patients);
        System.out.print("Enter patient ID: ");
        String patientID = scanner.nextLine();

        List<MedicalRecords> medicalRecords = readPatientByPatientMedicalRecord(patientID);
        if(medicalRecords.isEmpty()){
            System.out.println("No medical record information is found ");
            return;
        }

        // show medical record 
        for(int i = 0; i < medicalRecords.size(); i++){
            System.out.printf("Medical Record ID: %s\nRecord Date: %s\nDiagnoses: %s\nMedication: %s\nTreatment History: %s\nNext Follow Up: ", medicalRecords.get(i).getID(), medicalRecords.get(i).getCreationDate(), medicalRecords.get(i).getDiagnoses(), medicalRecords.get(i).getPrescribedMedications(), medicalRecords.get(i).getTreatmentHistory(), medicalRecords.get(i).getNextFollowUp());
        }

        System.out.print("Select medical record to delete: ");
        int medicalRecordChoice = Integer.parseInt(scanner.nextLine()) -1;

        if(medicalRecordChoice < 0 || medicalRecordChoice >= medicalRecords.size()){
            System.out.println("Invalid selection. Re-enter");
            return;
        }

        // confirmation delete record 
        System.out.print("--------Confirm Delete Medical Record--------");
        String confirmation = scanner.nextLine();
        if(!confirmation.equalsIgnoreCase("Y")){
            System.out.println("Don't want delete");
            return;
        }

        // remove medical record 
        String deleteMedicalRecord = medicalRecords.get(medicalRecordChoice).getID();
        medicalRecords.remove(medicalRecordChoice);

        //update file 
        updateMedicalRecord(deleteMedicalRecord);
        System.out.println("Medical Record deleted");
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



}
