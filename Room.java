import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String ROOM_ID;
    private String roomType = "Consultation";
    private final List<String> AVAILABLE_ROOM_TYPES = new ArrayList<>();
    private boolean available;
    // as a reference variable
    private Appointment currentAppointment;

    // default constructor for new rooms
    public Room(int floor, String roomType){
        this(IdGenerator.generateRoomId(floor), roomType, true, null);
    }

    // constructor for file loading
    public Room(String roomID, String roomType, boolean available, Appointment currentAppointment){
        this.ROOM_ID = roomID;
        this.roomType = roomType;
        this.available = available;
        this.currentAppointment = currentAppointment;
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
