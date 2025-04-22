import java.util.ArrayList;
import java.util.List;

public class Nurse extends Staff{
    List<Patient> patients = new ArrayList<>(); // list of patients assigned to the nurse

    //constructor 
    public Nurse(String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department, int yearOfExp){
        super(IdGenerator.generateNurseId(), nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // 
    public String toFileFormat(){
        return String.join("|", this.getId(), this.getIc(), this.getName(), this.getGender(), this.getContactNumber(), this.getAddress(), this.getDepartment(), String.valueOf(this.getYearOfExp()));
    }
    
    public List<Patient> getPatients(){
        return patients;
    }

    //convert to string 
    public String toString(){
        return super.toString();
    }
        
}
