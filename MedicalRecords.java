import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalRecords {
    private final String MEDICAL_RECORD_ID;
    private final LocalDate CREATION_DATE;
    private final Patient PATIENT;
    private Doctor doctor;
    private List<String> diagnoses;
    private List<String> prescribedMedications = new ArrayList<>();
    private List<String> treatmentHistory;
    private LocalDateTime nextFollowUp;

    // default constructor for new medical records
    public MedicalRecords(Patient patient, Doctor doctor){
        this(IdGenerator.generateMedicalRecordId(), LocalDate.now(), patient, doctor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
    }

    // constructor for file loading
    public MedicalRecords(String medicalRecordId, LocalDate creationDate, Patient patient, Doctor doctor, List<String> diagnoses, List<String> prescribedMedications, List<String> treatmentHistory, LocalDateTime nextFollowUp){
        this.MEDICAL_RECORD_ID = medicalRecordId;
        this.CREATION_DATE = creationDate;
        this.PATIENT = patient;
        this.doctor = doctor;
        this.diagnoses = diagnoses;
        this.prescribedMedications = prescribedMedications;
        this.treatmentHistory = treatmentHistory;
        this.nextFollowUp = nextFollowUp;
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

    public String getMedicalRecordId() {
        return MEDICAL_RECORD_ID;
    }

    public LocalDate getCreationDate() {
        return CREATION_DATE;
    }

    public Patient getPatient() {
        return PATIENT;
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
