public enum Role{
    STAFF("Staff"),
    DOCTOR("Doctor"),
    NURSE("Nurse"),
    PATIENT("Patient");

    private final String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }

    public static boolean isStaff(Role role){
        return role == DOCTOR || role == NURSE || role == STAFF;
    }

    public String getRoleName(){
        return roleName;
    }
}
