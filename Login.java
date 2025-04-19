import java.util.Scanner;

public class Login{
    private static final String STAFF_USERNAME = "StaffAccess";
    private static final String STAFF_PASSWORD = "Staff12345";
    private static final String PATIENT_USERNAME = "PatientAccess";
    private static final String PATIENT_PASSWORD = "Patient12345";

    public void displayLoginMenu(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();
            

            if(username == STAFF_USERNAME){
            }
        }
    }
}