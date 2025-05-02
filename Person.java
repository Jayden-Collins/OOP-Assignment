public abstract class Person {
    private final Role ROLE;
    private final String ID;
    private final String IC;
    private String name;
    private String gender;
    private String contactNumber;
    private String address;

    protected Person(Role role, String id, String ic, String name, String gender, String contactNumber, String address){
        this.ROLE = role;
        this.ID = id; // Generate a unique ID for the person
        this.IC = ic;
        this.name = name;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    // validate and set name
    public void setName(String name){
        this.name = name;
    }

    // set and boolean method for staff gender
    public void setGender(String gender){
        this.gender = gender;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    // set and boolean method for staff address 
    public void setAddress(String address){
        this.address = address;
    }

    // get method 
    public Role getRole(){
        return ROLE;
    }
    
    public String getId(){
        return ID;
    }

    public String getIc(){
        return IC;
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

    public String toFileFormat(){
        return String.join("|", ID, IC, name, gender, contactNumber, address);
    }

    // toString method to return a string representation of the person
    @Override
    public String toString() {
        return "ID: " + ID + "\n" +
                "IC: " + IC + "\n" +
                "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Contact Number: " + contactNumber + "\n" +
                "Address: " + address + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return ID.equals(person.ID) && IC.equals(person.IC);
    }

    @Override
    public int hashCode() {
        return 31 * ID.hashCode() + IC.hashCode();
    }
}
