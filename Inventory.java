//class to hold the player inventory
//acts like receipt class of pizza

import java.util.ArrayList;


public class Inventory {
    
    private String playerName;
    private ArrayList<Item> playerItems;
    private int totalSteps;
    private Boolean playerEscape;

    //generate an array to hold player inventory
    public Inventory() {
        playerItems = new ArrayList<Item>();
        playerEscape = false;
    }

    //set player name
    public void setName(String playerName) {
        this.playerName = playerName;
    }
        
    //add item to player inventory
    public void addItem(Item newItem) {
        playerItems.add(newItem);
    }

    //remove item from player inventory
    public void removeItem(int removedItem) {
        playerItems.remove(removedItem);
    }

    //get item from player inventory
    public Item getItem(int val) {
        return playerItems.get(val);
    }

    //show total steps
    public void addStep() {
        totalSteps += 1;
    }

    //get total steps
    public int getSteps() {
        return totalSteps;
    }

    //set if escape value
    public void setEscape() {
        playerEscape = true;
    }

    //set if escape value
    public void setIfEscape(Boolean canEscape) {
        playerEscape = canEscape;
    }


    //get if escape value
    public Boolean getEscape() {
        return playerEscape;
    }

    public int getSize() {
        return playerItems.size();
    }
}