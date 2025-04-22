public class ValidationCheck {
    //validation check for id is it null and match the criteria
    public static boolean validateID(String id){
        return id != null && id.matches("^DC-\\d{2}-\\d{2}-\\d{3}$");
    }

    //validation check for name is it alphabet 
    public static boolean validateName(String name){
        return name != null && name.matches("^[a-zA-Z ]+$");
    }

    //validation for gender 
    public static boolean validateGender(String gender){
        return (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Others"));
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
        return address != null && address.matches("^[a-zA-z0-9 ]+$");
    }

    // validation for year of experience
    public static boolean validateYearOfExp(String yearOfExp){
        return yearOfExp != null && yearOfExp.matches("^[0-9 ]+$");
    }

    //validation for patient medical history
    public static boolean validatePatientMedicalHistory(String medicalHistory){
        return medicalHistory != null && medicalHistory.matches("^[a-zA-Z0-9 ]+$");
    }

    //validation for appointment status 
    public static boolean validateAppointmentStatus(String status){
        return status != null && status.matches("^[a-zA-z ]+$");
    }
}
