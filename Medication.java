import java.util.List;

public class Medication {
    private String medicationName;
    private int dosage;

    public Medication(String medicationName, int dosage) {
        this.medicationName = medicationName;
        this.dosage = dosage;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return medicationName + " (" + dosage + "mg)";
    }

    public String toFileFiledFormat(){
        return medicationName + ":" + dosage;
    }

    public static Medication findMedicationByName(String name, List<Medication> medications) {
        for (Medication medication : medications) {
            if (medication.getMedicationName().equalsIgnoreCase(name)) {
                return medication;
            }
        }
        return null; // Return null if not found
    }

    public String toFileFormat() {
        return medicationName + "|" + dosage;
    }
}
