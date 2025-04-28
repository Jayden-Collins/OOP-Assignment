import java.util.ArrayList;
import java.util.List;

public class Nurse extends Staff{
    private final List<Patient> PATIENTS = new ArrayList<>(); // list of patients assigned to the nurse

    // default constructor for new nurses
    public Nurse(String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department, int yearOfExp){
        this(IdGenerator.generateNurseId(), nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // constructur for file loading
    public Nurse(String nurseId, String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, String department, int yearOfExp){
        super(Role.NURSE, nurseId, nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // to file format for file loading
    @Override
    public String toFileFormat(){
        return String.join("|", this.getId(), this.getIc(), this.getName(), this.getGender(), this.getContactNumber(), this.getAddress(), this.getDepartment(), String.valueOf(this.getYearOfExp()));
    }
    
    public List<Patient> getPatients(){
        return PATIENTS;
    }

    @Override
    public String toString(){
        return super.toString();
    }
        
}
