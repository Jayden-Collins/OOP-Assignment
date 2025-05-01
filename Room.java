import java.util.ArrayList;
import java.util.List;

public class Room {
    private final String ROOM_ID;
    private String roomType = "Consultation";
    private final List<String> AVAILABLE_ROOM_TYPES = new ArrayList<>();
    // as a reference variable
    private Appointment currentAppointment;

    // default constructor for new rooms
    public Room(int floor){
        this(IdGenerator.generateRoomId(floor));
    }

    // constructor for file loading
    public Room(String roomID){
        this.ROOM_ID = roomID;
    }

    // get method 
    public String getRoomID(){
        return ROOM_ID;
    }

    // returns the floor a room is located on
    public int getFloor(){
        return Character.getNumericValue(ROOM_ID.charAt(3));
    }

    // checks whether a room is on a certain floor
    public boolean onFloor(int floor){
        return getFloor() == floor;
    }

    // return the location of the room as a string
    public String getLocation(){
        return "Floor " + getFloor() + ", Room " + ROOM_ID.substring(4);
    }

    public boolean setRoomType(String roomType){
        if (AVAILABLE_ROOM_TYPES.contains(roomType)){
            this.roomType = roomType;
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAvailableRoomTypes(){
        return AVAILABLE_ROOM_TYPES;
    }

    public String getRoomType(){
        return roomType;
    }

    public Appointment getCurrentAppointmentID(){
        return currentAppointment;
    }

    // room to free 
    public void freeRoom(){
        this.currentAppointment = null;
    }

    // to file format
    public String toFileFormat(){
        return String.join("|", ROOM_ID, roomType); 
    }

    // to String method 
    @Override
    public String toString(){
        return String.format("Room ID: " + ROOM_ID
        + "\nRoom Type: " + roomType);
    }
}
