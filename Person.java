public abstract class Person {
    private final String id;
    private String name;
    private String gender;
    private String contactNumber;
    private String address;

    protected Person(String id, String name, String gender, String contactNumber, String address){
        this.id = id; // Generate a unique ID for the person
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // setter methods
    public boolean setName(String name){
        if(name != null && name.matches("^[a-zA-Z ]+$")){
            this.name = name;
            return true;
        }
        return false;
    }

    //set and boolean method for staff gender
    public boolean setGender(String gender){
        if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female") || gender.equalsIgnoreCase("Others")){
            this.gender = gender;
            return true;
        }
        return false;
    }

    public boolean setContactNumber(String contactNumber){
        if(contactNumber.matches("^\\d{3}-\\d{7}$") || contactNumber.matches("^\\d{3}-\\d{8}$") || contactNumber.matches("^\\d{10}$") || contactNumber.matches("^\\d{11}$")){
            this.contactNumber = contactNumber;
        }
        return false;
    }

    // set and boolean method for staff address 
    public boolean setAddress(String address){
        if(address != null && address.matches("^[a-zA-z0-9 ]+$")){
            this.address = address;
            return true;
        }
        return false;
    }

    // get method 
    public String getId(){
        return id;
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

    // abstract method to be implemented in subclasses to return specific details about the person
    public abstract String getDetails();

    // toString method to return a string representation of the person
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Gender:" + gender + ", Contact Number: " + contactNumber + ", Address: " + address;
    }

    // equals method to compare two Person objects based on their ID
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check if the object is null or not of the same class
        Person person = (Person) obj; // Cast to Person
        return id.equals(person.id); // Compare IDs for equality
    }

    // hashCode method to generate a hash code based on the ID
    public int hashCode() {
        return id.hashCode(); // Generate hash code based on ID
    }
}
