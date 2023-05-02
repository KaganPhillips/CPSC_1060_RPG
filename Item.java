//class to generate a item the player may find during their journey
public class Item {
    String itemName;
    String itemDescription;

    //generate an item
    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    //return the name of an item
    public String getItemName() {
        return itemName;
    }

    //return the description of an item
    public String getItemDesc() {
        return itemDescription;
    }
}