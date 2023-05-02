import java.util.HashMap;

public class AdventureMap {

    HashMap<String, Room> map;

    public AdventureMap() {
        map = new HashMap<>();

    }

    //adds a room to the hashmap
    public void addRoom(Room room) {
        map.put(room.roomGetName().toLowerCase(), room);
    }

    //returns the room from the hashmap
    public Room getRoom(String roomName) {
        return map.get(roomName.toLowerCase());
    }
}
