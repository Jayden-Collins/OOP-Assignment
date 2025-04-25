public class Menu {
    private final Hospital hospital;

    public Menu(Hospital hospital){
        this.hospital = hospital;
    }

    // display staff or patient menu based on user role
    public void displayMenu(){
        if(Role.isStaff(hospital.getUserRole())){
            displayStaffMenu();
        } else {
            displayPatientMenu();
        }
    }

    // display staff menu
    public void displayStaffMenu(){
        hospital.clearScreen();
        System.out.println("Staff Page");
        System.out.println("1. Doctor Management ");
        System.out.println("2. Nurse Management ");
        System.out.println("3. Patient Management");
        System.out.println("4. Generate Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // display patient menu 
    public void displayPatientMenu(){
        hospital.clearScreen();
        System.out.println("Patient Page");
        System.out.println("1. View Doctor");
        System.out.println("2. Check own Information.");
        System.out.println("3. Book Appoinments");
        System.out.println("4. View Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // calls the relevent choice handling methods based on user role
    public void choiceSelection(int choice){
        if(Role.isStaff(hospital.getUserRole())){
            staffChoice(choice);
        } else{
            patientChoice(choice);
        }
    }

    // handles choice selection for staff
    public void staffChoice(int choice){    
        switch (choice){
            case 1:
                hospital.doctorManagement();
                System.out.println("Press enter to continue");
                hospital.scanner.nextLine();
                hospital.clearScreen();
                break;
            case 2:
                hospital.nurseManagement();
                hospital.clearScreen();
                break;
            case 3:
                hospital.patientManagement();
                hospital.clearScreen();
                break;
            case 4:
                System.out.println("Closed Program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Re-enter");
        }
    }

    // handles choice selection for patients
    public void patientChoice(int choice){
        switch(choice){
            case 1:
                System.out.println("Closed Program");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice. Re-enter");
        }
    }
}
