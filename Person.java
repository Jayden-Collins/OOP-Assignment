public abstract class Person {
    private final String id;
    private final String ic;
    private String name;
    private String gender;
    private String contactNumber;
    private String address;

    protected Person(String id, String ic, String name, String gender, String contactNumber, String address){
        this.id = id; // Generate a unique ID for the person
        this.ic = ic;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // validate and set name
    public boolean setName(String name){
        if(name != null && name.matches("^[a-zA-Z ]+$")){
            this.name = name;
            return true;
        }
        return false;
    }

    // set and boolean method for staff gender
    public boolean setGender(String gender){
        if(ValidationCheck.validateGender(gender)){
            this.gender = gender;
            return true;
        }
        return false;
    }

    public boolean setContactNumber(String contactNumber){
        if(ValidationCheck.validateNumber(contactNumber)){
            this.contactNumber = contactNumber;
            return true;
        }
        return false;
    }

    // set and boolean method for staff address 
    public boolean setAddress(String address){
        if(ValidationCheck.validateAddress(address)){
            this.address = address;
            return true;
        }
        return false;
    }

    // get method 
    public String getId(){
        return id;
    }

    public String getIc(){
        return ic;
    }
    
    public String getName(){
        return name;
    }

    public String getGender(){
        return gender;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public String getAddress(){
        return address;
    }

    // toString method to return a string representation of the person
    public String toString() {
        return "ID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Contact Number: " + contactNumber + "\n" +
                "Address: " + address + "\n";
    }

/*     // equals method to compare two Person objects based on their ID
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check if the object is null or not of the same class
        Person person = (Person) obj; // Cast to Person
        return id.equals(person.id); // Compare IDs for equality
    }

    // hashCode method to generate a hash code based on the ID
    public int hashCode() {
        return id.hashCode(); // Generate hash code based on ID
    } */
}
