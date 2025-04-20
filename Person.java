public abstract class Person {
    private final String id;
    private String name;
    private final String gender;
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

    public void setName(String name){
        this.name = name;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address){
        this.address = address;
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
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Gender:" + gender + ", Contact Number: " + contactNumber + ", Address: " + address;
    }

    // equals method to compare two Person objects based on their ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check if the object is null or not of the same class
        Person person = (Person) obj; // Cast to Person
        return id.equals(person.id); // Compare IDs for equality
    }

    // hashCode method to generate a hash code based on the ID
    @Override
    public int hashCode() {
        return id.hashCode(); // Generate hash code based on ID
    }
}
