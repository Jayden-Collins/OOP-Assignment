import java.util.ArrayList;
import java.util.List;

public class Nurse extends Staff{
    private final List<Patient> PATIENTS = new ArrayList<>(); // list of patients assigned to the nurse
    private static int nurseCount = 0; // count of nurses

    // default constructor for new nurses
    public Nurse(String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, Department department, int yearOfExp){
        this(IdGenerator.generateNurseId(), nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // constructur for file loading
    public Nurse(String nurseId, String nurseIc, String nurseName, String nurseGender, String nurseContactNumber, String nurseAddress, Department department, int yearOfExp){
        super(Role.NURSE, nurseId, nurseIc, nurseName, nurseGender, nurseContactNumber, nurseAddress, department, yearOfExp);
    }

    // add patient to the nurse's list of patients
    public void addPatient(Patient patient){
        PATIENTS.add(patient);
    }
    
    public List<Patient> getPatients(){
        return PATIENTS;
    }

    // get method for nurseCount
    public static int getNurseCount(){
        return nurseCount;
    }

    // increment method for nurseCount
    public static void incrementNurseCount(){
        nurseCount++;
    }
    
    // set method for nurseCount
    public static void setNurseCount(int nurseCount){
        Nurse.nurseCount = nurseCount;
    }

    @Override
    public String toString(){
        return super.toString();
    }
        
}
