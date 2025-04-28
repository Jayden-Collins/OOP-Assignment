import java.util.List;

public class Room {
    private final String roomID;
    private List<String> roomType;
    private boolean available;
    // as a reference variable
    private Appointment currentAppointment;

    // constructor
    public Room(String roomID){
        this.roomID = roomID;
        this.available = true;
        this.currentAppointment = null;
    }

    // get method 
    public String getRoomID(){
        return roomID;
    }

    public void setRoomType(String roomType){
        this.roomType.add(roomType);
    }

    public List<String> getRoomType(){
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
