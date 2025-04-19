public abstract class Person {
    private String personID;
    private String personName;
    private String personGender;
    private String personContactNumber;
    private String personAddress;

    public Person(){

    }

    public Person(String personID, String personName, String personGender, String personContactNumber, String personAddress){
        this.personID = personID;
        this.personName = personName;
        this.personGender = personGender;
        this.personContactNumber = personContactNumber;
        this.personAddress = personAddress;
    }

    //set method 
    public void setPersonID(String personID){
        this.personID = personID;
    }

    public void setPersonName(String personName){
        this.personName = personName;
    }

    public void setPersonGender(String personGender){
        this.personGender = personGender;
    }

    public void setPersonContactNumber(String personContactNumber){
        this.personContactNumber = personContactNumber;
    }

    public void setPersonAddress(String personAddress){
        this.personAddress = personAddress;
    }

    // get method 


}
