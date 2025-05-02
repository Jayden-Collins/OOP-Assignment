public class ValidationCheck {
    //validation check for id is it null and match the criteria
    public static boolean validateID(String id, String type){
        if (type.equals(Role.DOCTOR.getRoleName())) {
            return id != null && id.matches("^DC-\\d{2}-\\d{3}$");
        } else if (type.equals(Role.NURSE.getRoleName())) {
            return id != null && id.matches("^NR-\\d{2}-\\d{3}$");
        } else if (type.equals(Role.PATIENT.getRoleName())) {
            return id != null && id.matches("^PT-\\d{2}-\\d{2}-\\d{4}$");
        } else if (type.equals("Appointment")) {
            return id != null && id.matches("^AP-\\d{2}-\\d{2}-\\d{4}$");
        } else if (type.equals("Medical Record")) {
            return id != null && id.matches("^MR-\\d{2}-\\d{2}-\\d{4}$");
        } else if (type.equals("Department")) {
            return id != null && id.matches("^DT-\\d{2}-\\d{3}$");
        } else if (type.equals("Room")) {
            return id != null && id.matches("^RM-\\d{4}$");
        } else return false;
    }

    // validation check for ic matches the format 012345-67-8901 and is not null
    public static boolean validateIc(String ic){
        return ic != null && ic.matches("^[0-9]{6}-[0-9]{2}-[0-9]{4}$");
    }

    //validation check for name is it alphabet 
    public static boolean validateName(String name){
        return name != null && name.matches("^[a-zA-Z ]+$");
    }

    //validation for gender 
    public static boolean validateGender(String gender){
        return (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"));
    }

    //validation for doctor department 
    public static boolean validateDoctorDepartment(String doctorDepartment){
        return doctorDepartment != null && doctorDepartment.matches("^[a-zA-Z0-9 ]+$");
    }

    //validation for contact number 
    public static boolean validateNumber(String contactNumber){
        return (contactNumber.matches("^\\d{3}-\\d{7}$") || contactNumber.matches("^\\d{3}-\\d{8}$") || contactNumber.matches("^\\d{10}$") || contactNumber.matches("^\\d{11}$"));
    }

    //validation for address 
    public static boolean validateAddress(String address){
        return address != null && address.matches("^[a-zA-z0-9, ]+$");
    }

    // validation for year of experience
    public static boolean validateYearOfExp(String yearOfExp){
        return yearOfExp != null && yearOfExp.matches("^[0-9]+$") && Integer.parseInt(yearOfExp) > 0;
    }

    //validation for patient medical history
    public static boolean validatePatientMedicalHistory(String medicalHistory){
        return medicalHistory != null && medicalHistory.matches("^[a-zA-Z0-9 ]+$");
    }
}
