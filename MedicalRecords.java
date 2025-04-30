
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalRecords {
    private final String medicalRecordID;
    private final LocalDate creationDate = LocalDate.now();
    private final Patient patient;
    private Doctor doctor;
    private List<String> diagnoses = new ArrayList<>();
    private List<String> prescribedMedications = new ArrayList<>();
    private List<String> treatmentHistory = new ArrayList<>();
    private LocalDateTime nextFollowUp;

    public MedicalRecords(String medicalRecordID, Patient patient, Doctor doctor){
        // generate medical record id 
        this.medicalRecordID = medicalRecordID;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getID(){
        return medicalRecordID;
    }

    public void addDiagnosis(String diagnoses){
        this.diagnoses.add(diagnoses);
    }

    public void prescribeMedications(String medication){
        this.prescribedMedications.add(medication);
    }

    public void addTreatmentNote(String treatmentNote){
        this.treatmentHistory.add(treatmentNote);
    }

    public void addFollowUp(LocalDateTime nextFollowUp){
        this.nextFollowUp = nextFollowUp;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public List<String> getDiagnoses() {
        return Collections.unmodifiableList(diagnoses);
    }

    public List<String> getPrescribedMedications() {
        return Collections.unmodifiableList(prescribedMedications);
    }

    public List<String> getTreatmentHistory() {
        return Collections.unmodifiableList(treatmentHistory);
    }

    public LocalDateTime getNextFollowUp() {
        return nextFollowUp;
    }

    public String toString(){
        return String.format("Medical Record ID: %s\nRecord Date: %s\nPatient: %s\nDoctor: %s\nDiagnoses: %s\nMedication: %s\nTreatment History: %s\nNext Follow Up: %s\n", IdGenerator.generateMedicalRecordId(), creationDate, patient.getName(), doctor.getName(), prescribedMedications, treatmentHistory, nextFollowUp);
    }
}
