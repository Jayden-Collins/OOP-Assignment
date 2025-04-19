import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        Doctordetails doctor1 = new Doctordetails("24DOC0001", "Dr John", "Male", "Heart", "016-4953351", "Jalan Hi");
        NurseDetails nurse1 = new NurseDetails("20NUR0001", "Nurse Joey", "Female", "0174953351", "Jalanzhi");
        Patientdetails patient1 = new Patientdetails("24P0001", "JohnDoe", "Male", "Jalan Hi", "01249533511","0144953351", "None");

        while (true){
            menu.display();
            int choice = scanner.nextInt();
            menu.choice(choice, doctor1, nurse1, patient1);
        }

    }
}