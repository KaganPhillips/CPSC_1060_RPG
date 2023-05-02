import java.util.HashMap;

public class HouseMap {

    HashMap<String, Room> map;

    public HouseMap() {
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
