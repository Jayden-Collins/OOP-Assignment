import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String ROOM_ID;
    private String roomType = "Consultation";
    private final List<String> AVAILABLE_ROOM_TYPES = new ArrayList<>();
    private boolean available;
    // as a reference variable
    private Appointment currentAppointment;

    // constructor
    public Room(int floor, String roomType){
        this.ROOM_ID = IdGenerator.generateRoomId(floor);
        this.available = true;
        this.currentAppointment = null;
    }

    // get method 
    public String getRoomID(){
        return ROOM_ID;
    }

    public boolean setRoomType(String roomType){
        if (AVAILABLE_ROOM_TYPES.contains(roomType)){
            this.roomType = roomType;
            return true;
        } else {
            return false;
        }
    }

    public List<String> getRoomType(){
        return AVAILABLE_ROOM_TYPES;
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
        return String.format("Room ID: " + ROOM_ID
        + "\nRoom Type: " + roomType
        + "\nAvailability: " + available
        + "\nCurrentAppointment: " + currentAppointment);
    }

}
