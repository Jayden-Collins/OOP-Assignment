
public class Menu {
    private Hospital hospital;

    public Menu(Hospital hospital){
        this.hospital = hospital;
    }

    //display selection of menu
    public void displayMenu(){
        if(hospital.UserAccess().equals("Staff")){
            displayStaffMenu();
        } else {
            displayPatientMenu();
        }
    }

    //display staff menu
    public void displayStaffMenu(){
        System.out.println("Staff Page");
        System.out.println("1. Doctor Management ");
        System.out.println("2. Nurse Management ");
        System.out.println("3. Patient Management");
        System.out.println("4. Generate Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    //display patient menu 
    public void displayPatientMenu(){
        System.out.println("Patient Page");
        System.out.println("1. View Doctor");
        System.out.println("2. Check own Information.");
        System.out.println("3. Book Appoinments");
        System.out.println("4. View Medical Report");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // choice for method operations called 
    public void choiceSelection(int choice){
        if(hospital.UserAccess().equals("Staff")){
            staffChoice(choice);
        } else{
            patientChoice(choice);
        }
    }

    // staff choice method called 
    public void staffChoice(int choice){    
        switch (choice){
            case 1:
                hospital.doctorManagement();
                break;
            case 2:
                System.out.println("Closed Program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Re-enter");
        }
    }

    // patient choice method called 
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
