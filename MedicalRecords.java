
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedicalRecords {
<<<<<<< HEAD
    private final String medicalRecordID;
    private final LocalDate creationDate = LocalDate.now();
    private final Patient patient;
=======
    private final String MEDICAL_RECORD_ID;
    private final LocalDate CREATION_DATE;
    private final Patient PATIENT;
>>>>>>> jayden-branch
    private Doctor doctor;
    private List<String> diagnoses = new ArrayList<>();
    private List<String> prescribedMedications = new ArrayList<>();
    private List<String> treatmentHistory = new ArrayList<>();
    private LocalDateTime nextFollowUp;
    private static int medicalRecordCount = 0;

    // default constructor for new medical records
    public MedicalRecords(Patient patient, Doctor doctor){
<<<<<<< HEAD
        // generate medical record id 
        this.medicalRecordID = IdGenerator.generateMedicalRecordId();
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getID(){
        return medicalRecordID;
=======
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
>>>>>>> jayden-branch
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

<<<<<<< HEAD
    public String toString(){
        return String.format("Medical Record ID: %s\nRecord Date: %s\nPatient: %s\nDoctor: %s\nDiagnoses: %s\nMedication: %s\nTreatment History: %s\nNext Follow Up: %s\n", medicalRecordID, creationDate, patient.getName(), doctor.getName(), String.join(", ", diagnoses), String.join(", ",prescribedMedications), String.join(", ", treatmentHistory), nextFollowUp);
=======
    // get method for medical record count
    public static int getMedicalRecordCount() {
        return medicalRecordCount;
    }

    // increment method for medical record count
    public static void incrementMedicalRecordCount() {
        medicalRecordCount++;
    }

    // set method for medical record count
    public static void setMedicalRecordCount(int medicalRecordCount) {
        MedicalRecords.medicalRecordCount = medicalRecordCount;
>>>>>>> jayden-branch
    }
}
