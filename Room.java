import java.util.ArrayList;

public class Room
{
    String name;
    String description;
    ArrayList<String> exits;
    boolean hasItem;
    Item roomItem;
    
    /**
     * Initialize a room
     * @param name the name of the room
     * @param description the description of the room
     */
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        hasItem = false;
        exits = new ArrayList<String>();
    }

    /* Generate getteand setters for the names and descriptions */
    public void roomSetName(String name) {
        this.name = name;
    }
    
    //returns the room name
    public String roomGetName() {
        return this.name;
    }

    //sets the room description
    public void roomSetDesc(String description) {
        this.description = description;
    }

    //returns the room description
    public String roomGetDesc() {
        return this.description;
    }

    //marks the room as having an item
    public void roomHasItem() {
        hasItem = true;
    }

    //returns if the room has an item
    public Boolean getIfItem() {
        return hasItem;
    }

    //adds an item to the room
    public void presentItem(Item currentItem) {
        roomItem = currentItem;
        roomHasItem();
    }

    //returns the item from the room
    public Item getRoomItem() {
        return roomItem;
    }

    //sets the item flag to false
    public void removeRoomItem() {
        hasItem = false;
    }

    //adds an exit to the room
    public void addExit(String exit) {
        exits.add(exit);
    }

    //list all of the exits of a room
    public String listExits() {
        String exitsList = "";
        for(int i = 0; i < exits.size(); i++) {
            exitsList += exits.get(i) + "\n";
        }
        return exitsList;
    }

    
    //generates a string representation of the room using the name and description and lists all of the exits
    public String toString() {
        String listExits = listExits();

        String roomString = this.name + ": " + this.description;
        roomString += "\n\nExits:\n" + listExits;

        return roomString;
    }
}
