import java.util.ArrayList;
import java.util.List;

public class Nurse extends Staff{
    List<Patient> patients = new ArrayList<>(); // list of patients assigned to the nurse

    // constructor for new nurses
    public Nurse(String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department, int yearOfExp){
        super(Role.NURSE, IdGenerator.generateNurseId(), nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // constructur for file reading
    public Nurse(String nurseId, String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department, int yearOfExp){
        super(Role.NURSE, nurseId, nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // to file format for file loading
    @Override
    public String toFileFormat(){
        return String.join("|", this.getId(), this.getIc(), this.getName(), this.getGender(), this.getContactNumber(), this.getAddress(), this.getDepartment(), String.valueOf(this.getYearOfExp()));
    }
    
    public List<Patient> getPatients(){
        return patients;
    }

    @Override
    public String toString(){
        return super.toString();
    }
        
}
