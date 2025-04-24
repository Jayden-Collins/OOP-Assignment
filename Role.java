public enum Role{
    DOCTOR,
    NURSE,
    PATIENT;

    public static boolean isStaff(Role role){
        return role == DOCTOR || role == NURSE;
    }
}
