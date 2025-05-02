import java.time.LocalDate;

public class IdGenerator {
    private static int doctorCount = 0;
    private static int nurseCount = 0;
    private static int patientCount = 0;
    private static int appointmentCount = 0;
    private static int medicalRecordCount = 0;
    private static int roomCount = 0;
    private static int departmentCount = 0;

    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    //Returns the last two digits of the current year
    private static int getYear() {
        return LocalDate.now().getYear() % 100;
    }

    //Doctor: DC-24-001
    public static String generateDoctorId() {
        Doctor.incrementDoctorCount();
        return String.format("DC-%02d-%03d", getYear(), Doctor.getDoctorCount());
    }

    //Nurse: NC-24-001
    public static String generateNurseId() {
        Nurse.incrementNurseCount();
        return String.format("NR-%02d-%03d", getYear(), Nurse.getNurseCount());
    }

    //Patient: PC-24-04-0001
    public static String generatePatientId() {
        Patient.incrementPatientCount();
        return String.format("PC-%02d-%02d-%04d", getYear(), LocalDate.now().getMonthValue(), Patient.getPatientCount());
    }

    //Appointment: AP-24-04-001
    public static String generateAppointmentId() {
        Appointment.incrementAppointmentCount();
        return String.format("AP-%02d-%02d-%03d", getYear(), LocalDate.now().getMonthValue(), Appointment.getAppointmentCount());
    }

    //Medical Record: MR-24-04-001
    public static String generateMedicalRecordId() {
        MedicalRecords.incrementMedicalRecordCount();
        return String.format("MR-%02d-%02d-%03d", getYear(), LocalDate.now().getMonthValue(), MedicalRecords.getMedicalRecordCount());
    }

    //Room: RM-1001
    public static String generateRoomId(int floor) {
        int seq = ++roomCount;
        return String.format("RM-%d%03d", floor, seq);
    }

    //Department: DT-001
    public static String generateDepartmentId() {
        Department.incrementDepartmentCount();
        return String.format("DT-%03d", Department.getDepartmentCount());
    }
}
