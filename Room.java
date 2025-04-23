public class Room {
    private final String roomID;
    private String roomType;
    private boolean available;
    // as a reference variable
    private Appointment currentAppointment;

    // constructor
    public Room(String roomID, String roomType){
        this.roomID = roomID;
        this.roomType = roomType;
        this.available = true;
        this.currentAppointment = null;
    }

    // get method 
    public String getRoomID(){
        return roomID;
    }

    public String getRoomType(){
        return roomType;
    }

    public boolean getAvailable(){
        return available;
    }

    public Appointment getCurrentAppointmentID(){
        return currentAppointment;
    }

    //check available for room 
    public boolean bookedRoom(Appointment appointment){
        if(available){
            this.currentAppointment = appointment;
            this.available = false;
            return true;
        } 
        return false;
    }

    // room to free 
    public void freeRoom(){
        this.currentAppointment = null;
        this.available = true;
    }

    // to String method 
    public String toString(){
        return String.format("Room ID: " + roomID + "\nRoom Type: " + roomType + "\nAvailable: " + available + "\nCurrentAppointment: " + currentAppointment);
    }

}
