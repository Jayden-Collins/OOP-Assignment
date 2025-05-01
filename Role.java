public enum Role{
    STAFF("Staff", ""),
    DOCTOR("Doctor", "DC-25-001"),
    NURSE("Nurse", "NR-25-001"),
    PATIENT("Patient", "PC-25-04-001");

    private final String ROLE_NAME;
    private final String ROLE_ID_EXAMPLE;

    Role(String ROLE_NAME, String ROLE_ID_EXAMPLE){
        this.ROLE_NAME = ROLE_NAME;
        this.ROLE_ID_EXAMPLE = ROLE_ID_EXAMPLE;
    }

    public static boolean isStaff(Role role){
        return role == DOCTOR || role == NURSE || role == STAFF;
    }

    public String getRoleName(){
        return ROLE_NAME;
    }

    public String getRoleIdExample(){
        return ROLE_ID_EXAMPLE;
    }
}
