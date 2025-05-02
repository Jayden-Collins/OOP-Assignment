
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private List<String> diagnoses = new ArrayList<>();
    private List<Medication> prescribedMedications = new ArrayList<>();
    private List<String> treatmentHistory = new ArrayList<>();
    private LocalDateTime nextFollowUp;
    private static int medicalRecordCount = 0;
    private static String file = "medical_records.txt";

    // default constructor for new medical records
    public MedicalRecords(Patient patient, Doctor doctor){
        this(IdGenerator.generateMedicalRecordId(), LocalDate.now(), patient, doctor, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
    }

    // constructor for file loading
    public MedicalRecords(String medicalRecordId, LocalDate creationDate, Patient patient, Doctor doctor, List<String> diagnoses, List<Medication> prescribedMedications, List<String> treatmentHistory, LocalDateTime nextFollowUp){
        this.MEDICAL_RECORD_ID = medicalRecordId;
        this.CREATION_DATE = creationDate;
        this.PATIENT = patient;
        this.doctor = doctor;
        this.diagnoses = diagnoses;
        this.prescribedMedications = prescribedMedications;
        this.treatmentHistory = treatmentHistory;
        this.nextFollowUp = nextFollowUp;
    }

    public String getId(){
        return MEDICAL_RECORD_ID;
    }

    public void addDiagnosis(String diagnoses){
        this.diagnoses.add(diagnoses);
    }

    public void prescribeMedications(Medication medication){
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

    public List<Medication> getPrescribedMedications() {
        return Collections.unmodifiableList(prescribedMedications);
    }

    public List<String> getTreatmentHistory() {
        return Collections.unmodifiableList(treatmentHistory);
    }

    public LocalDateTime getNextFollowUp() {
        return nextFollowUp;
    }

    // don't display null values in toString
    public String toString(){
        return String.format("Medical Record ID: %s\nCreation Date: %s\nPatient: %s\nDoctor: %s\nDiagnoses: %s\nPrescribed Medications: %s\nTreatment History: %s",
                MEDICAL_RECORD_ID, CREATION_DATE, PATIENT.getName(), doctor.getName(),
                String.join(", ", diagnoses),
                String.join(", ", prescribedMedications.stream()
                    .map(Medication::toString)
                    .toArray(String[]::new)),
                String.join(", ", treatmentHistory)) +
                 (nextFollowUp != null ? "\nNext Follow Up: " + nextFollowUp.toString() : "");
    }

    public String toFileFormat(){
        // join fields using |
        // for medications lists, join using comma
        // for medications, only store name without dosage
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s\n", MEDICAL_RECORD_ID, CREATION_DATE, PATIENT.getId(), doctor.getId(), String.join(",", diagnoses),
        String.join(",", prescribedMedications.stream().map(Medication::getMedicationName).toArray(String[]::new)), String.join(",", treatmentHistory), nextFollowUp);
    }

    // get method for medical record count
    public static int getMedicalRecordCount() {
        readMedicalRecordCount(file);
        return medicalRecordCount;
    }

    // read the number of medical records from the file based on the number of lines in the file
    public static void readMedicalRecordCount(String file) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            while((br.readLine()) != null){
                count++;
            }
        } catch (FileNotFoundException e){
            System.out.println("Medical Record file not found");
        } catch (IOException e){
            System.out.println("Error reading medical records" + e.getMessage());
        }

        while (hasIdSeq(count)){
            count++;
        }

        medicalRecordCount = count;
    }

    // checks whether an id has been used before
    private static boolean hasIdSeq(int seq){
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] fields = line.split("\\|");
                if(fields.length > 0 && fields[0].equals("MR-" + seq)){
                    return true;
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Medical Record file not found");
        } catch (IOException e){
            System.out.println("Error reading medical records" + e.getMessage());
        }
        return false;
    }

    // increment method for medical record count
    public static void incrementMedicalRecordCount() {
        readMedicalRecordCount(file);
        medicalRecordCount++;
    }

    // set method for medical record count
    public static void setMedicalRecordCount(int medicalRecordCount) {
        MedicalRecords.medicalRecordCount = medicalRecordCount;
    }
}
