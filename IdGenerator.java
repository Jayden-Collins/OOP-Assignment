import java.time.LocalDate;

public class IdGenerator {
    private static int doctorCount = 0;
    private static int nurseCount = 0;
    private static int patientCount = 0;
    private static int appointmentCount = 0;
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
        int seq = ++doctorCount;
        return String.format("DC-%02d-%03d", getYear(), seq);
    }

    //Nurse: NC-24-001
    public static String generateNurseId() {
        int seq = ++nurseCount;
        return String.format("NC-%02d-%03d", getYear(), seq);
    }

    //Patient: PC-24-04-001
    public static String generatePatientId() {
        int seq = ++patientCount;
        return String.format("PC-%02d-%02d-%03d", getYear(), LocalDate.now().getMonthValue(), seq);
    }

    //Appointment: AP-24-04-001
    public static String generateAppointmentId() {
        int seq = ++appointmentCount;
        return String.format("AP-%02d-%02d-%03d", getYear(), LocalDate.now().getMonthValue(), seq);
    }

    //Room: RM-1001
    public static String generateRoomId(int floor) {
        int seq = ++roomCount;
        return String.format("RM-%d%03d", floor, seq);
    }

    //Department: DT-001
    public static String generateDepartmentId() {
        int seq = ++departmentCount;
        return String.format("DT-%03d", seq);
    }
}
