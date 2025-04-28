import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalRecords {
    private final LocalDate creationDate = LocalDate.now();
    private final Patient patient;
    private Doctor doctor;
    private List<String> diagnoses;
    private List<String> prescribedMedications = new ArrayList<>();
    private List<String> treatmentHistory;
    private LocalDateTime nextFollowUp;

    public MedicalRecords(Patient patient, Doctor doctor){
        this.patient = patient;
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
}
