public enum Role{
    STAFF("Staff"),
    DOCTOR("Doctor"),
    NURSE("Nurse"),
    PATIENT("Patient");

    private final String ROLE_NAME;

    Role(String ROLE_NAME){
        this.ROLE_NAME = ROLE_NAME;
    }

    public static boolean isStaff(Role role){
        return role == DOCTOR || role == NURSE || role == STAFF;
    }

    public String getRoleName(){
        return ROLE_NAME;
    }
}
