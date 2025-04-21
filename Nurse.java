import java.util.ArrayList;
import java.util.List;

public class Nurse extends Staff{
    List<Patient> patients = new ArrayList<>(); // list of patients assigned to the nurse

    //constructor 
    public Nurse(String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department){
        super(IdGenerator.generateNurseId(), nurseName, nurseGender, nurseContactNumber, nurseAddress, department);
    }

    //abstract method link with staff super class
    public String getDetails(){
        return "Nurse";
    }

    //convert to string 
    public String toString(){
        return super.toString();
    }
        
}
