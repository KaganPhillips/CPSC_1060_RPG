import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class Driver {

    //generates the house for the user
    public static AdventureMap generateHouse() {
        AdventureMap mainMap = new AdventureMap();

        Room exitRoom = new Room("Exit", "The exit to the house. Theres nothing remarkable here, but theres a door with a small key hole and a comically large key hole");
        exitRoom.addExit("Kitchen");
        exitRoom.addExit("Entrance");
        mainMap.addRoom(exitRoom);

        Room entranceRoom = new Room("Entrance", "The entrance to the house. Theres nothing remarkable here, its just the starting point.");
        entranceRoom.addExit("Kitchen");
        entranceRoom.addExit("Exit");
        mainMap.addRoom(entranceRoom);

        Room Library = new Room("Library", "A room full to the brim with books. There are all different kinds covering all different subjects.");
        Library.addExit("Basement");
        Library.addExit("Puzzle Room");
        Library.addExit("Study");
        mainMap.addRoom(Library);

        Room Kitchen = new Room("Kitchen", "Its obviously supposed to be a kitchen, but since its a non-functioning house all the dangerous items have been removed.");
        Kitchen.addExit("Study");
        Kitchen.addExit("Entrance");
        Kitchen.addExit("Exit");
        mainMap.addRoom(Kitchen);

        Room Study = new Room("Study", "Basically a smaller library. It just has more objects than books. Chairs, tables, and a globe or two.");
        Study.addExit("Kitchen");
        Study.addExit("Library");
        Study.addExit("Bedroom");
        mainMap.addRoom(Study);

        Room Basement = new Room("Basement", "A short staircase leads to a basement. Its not much to speak of, relatively small");
        Basement.addExit("Library");
        mainMap.addRoom(Basement);

        Room puzzleRoom = new Room("Puzzle Room", "A room with an announcer goading you to complete a puzzle.");
        puzzleRoom.addExit("Bedroom");
        puzzleRoom.addExit("Library");
        mainMap.addRoom(puzzleRoom);

        Room Bedroom = new Room("Bedroom", "A small bedroom with a tiny bed and shelf. It almost looks like a minature set.");
        Bedroom.addExit("Study");
        Bedroom.addExit("Puzzle Room");
        mainMap.addRoom(Bedroom);

        return mainMap;
    }

    //creates the items for the map
    public static Item[] generateItems() {
        Item[] gameItems = new Item[2];

        //Item newItem = new Item(Name, Description)
        Item smallKey = new Item("A small key", "Its a small key...\nIt must be used to open something.");
        gameItems[0] = smallKey;

        Item paper = new Item("A scap of paper", "Its a small piece of paper...\nIt looks like it says 'chEfRE'\nWhat does it mean?");
        gameItems[1] = paper;

        return gameItems;
    }

    //places the two items in the map
    public static AdventureMap placeItems(Random rand, String[] roomNames) {
        AdventureMap mainMap = generateHouse();
        Item[] gameItems = generateItems();
        int randNum1 = rand.nextInt(6);
        int randNum2 = rand.nextInt(6);
        while (randNum1 == randNum2) {
            randNum2 = rand.nextInt(6);
        }

        mainMap.getRoom(roomNames[randNum1]).presentItem(gameItems[0]);
        mainMap.getRoom(roomNames[randNum2]).presentItem(gameItems[1]);

        return mainMap;
    }

    //outputs the exits and gets next input
    public static String getExits(String userInput, Scanner scnr, AdventureMap mainMap, String[] roomNames) {
        Boolean validInput = false;
        while (!validInput) {
            System.out.println("Please choose an exit");
            userInput = scnr.nextLine();
            for (int i = 0; i < 7; i++) {
                if (userInput.equalsIgnoreCase(roomNames[i]) || userInput.equalsIgnoreCase("exit")) {
                    validInput = true;
                }
            }
            if (!validInput) {
                System.out.println("Invalid exit.");
            }
        }

        return userInput;
    }

    //puzzle room
    public static Item puzzleRoom(Scanner scnr, Inventory playerInventory) {
        String userGuess;
        int totalGuesses = 0;
        String riddleAnswer = "Darkness";
        Boolean correctAns = false;
        Item largeKey = new Item("A comically large key", "Its a large key...\nIt must be used to open something.");

        System.out.println("You enter the room and hear a voice over an intercom:");
        System.out.println("Solve the puzzle in this room for an item to aid your escape!");
        System.out.println("You must solve the riddle!");
        System.out.println();
        System.out.println("The more of this there is, the less you see. What is it?");
        userGuess = scnr.next();
        while (!correctAns) {
            if(userGuess.equalsIgnoreCase(riddleAnswer)) {
                correctAns = true;
                System.out.println("From the intercom:\nCORRECT! You solved the riddle!\nHere is your prize!");
                System.out.println();
                System.out.println("A comically large key falls from the ceiling.\nIt must open something...");
            }
            else if (totalGuesses >= 3) {
                correctAns = true;
                System.out.println("From the intercom:\nIt doesn't look like you're going to get it...");
                System.out.println("The answer was Darkness.");
                System.out.println("Here, just take your prize.");
                System.out.println();
                System.out.println("A comically large key falls from the ceiling.\nIt must open something...");
            }
            else {
                System.out.println("From the intercom:\nINCORRECT!\nHeres a hint: When you can't see, what do you see?");
                System.out.println("Take another guess");
                userGuess = scnr.next();
            }
            totalGuesses += 1;
        }
        return largeKey;                
    }

    //exit room
    public static Boolean exitRoom(Scanner scnr, Inventory playerInventory) {
        Boolean playerHasSmallKey = false;
        Boolean playerHasLargeKey = false;
        Boolean playerCanExit = false;

        for(int i = 0; i < playerInventory.getSize(); i++) {
            if(playerInventory.getItem(i).getItemName().equalsIgnoreCase("A small key")){
                playerHasSmallKey = true;
            }
            else if(playerInventory.getItem(i).getItemName().equalsIgnoreCase("A comically large key")) {
                playerHasLargeKey = true;
            }
        }

        if(playerHasLargeKey == true && playerHasSmallKey == true) {
            System.out.println("You have found the materials to leave!");
            System.out.println("You will exit the house");

            playerCanExit = true;
        }
        else{
            System.out.println("It looks like youre still missing some things before you can exit.");
        }
        
        return playerCanExit;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();

        //log room names for easy compare
        String[] roomNames = new String[8];
        roomNames[0] = "Bedroom";
        roomNames[1] = "Library";
        roomNames[2] = "Kitchen";
        roomNames[3] = "Study";
        roomNames[4] = "Holodeck";
        roomNames[5] = "Puzzle Room";
        //make sure this room is last and isnt in rand gen
        roomNames[6] = "Entrance";
        roomNames[7] = "Exit";

        AdventureMap mainMap = placeItems(rand, roomNames);
        Inventory playerInventory = new Inventory();


        Boolean validInput;
        String userInput = "Entrance";
        String userYesNo = "";
        Room currentRoom;
        String userName;

        System.out.println("Please give your name:");
        userName = scnr.nextLine();
        System.out.println("\nWelcome to the Escape House! To leave the house, find the items to unlock the exit door!\n");

        do {
            currentRoom = mainMap.getRoom(userInput);
            System.out.println("\n" + mainMap.getRoom(userInput).toString());

            //search the room for items
            System.out.println("Would you like to search the room?");
            userYesNo = scnr.next();
            validInput = false;
            //validate input
            while (!validInput) { 
                if (userYesNo.equalsIgnoreCase("yes")){
                    validInput = true;
                    //if the room has an item
                    if (currentRoom.getIfItem()) {
                        System.out.println("There seems to be something in the room that could help you,\nYou found...");
                        System.out.println(currentRoom.getRoomItem().getItemName() + "!");
                        System.out.println(currentRoom.getRoomItem().getItemDesc());
                        //add item to inventory
                        playerInventory.addItem(currentRoom.getRoomItem());
                        //"remove" item from room
                        currentRoom.removeRoomItem();
                    }
                    //if there is no item
                    else {
                        System.out.println("There doesn't seem to be anything in the room that will help.");
                    }
                }
                //if the user says no
                else if(userYesNo.equalsIgnoreCase("no")){
                    validInput= true;
                    System.out.println("You didn't search the room.\nHopefully there wasn't anything important...");
                }
                //if user gives a bad input
                else {
                    System.out.println("Please make a valid selection.");
                    userYesNo = scnr.next();
                }
            }
            scnr.nextLine();
            //outputs the exits and takes next input
            userInput = getExits(userInput, scnr, mainMap, roomNames);
            
            if (userInput.equalsIgnoreCase("Puzzle Room")) {
                playerInventory.addItem(puzzleRoom(scnr, playerInventory));
            }

            if (userInput.equalsIgnoreCase("Exit")) {
                playerInventory.setIfEscape(exitRoom(scnr, playerInventory));
            }

            playerInventory.addStep();

        } while (!playerInventory.getEscape());

        String fileName = String.format("%s's_Escape_Room_Result.txt", userName);
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find " + fileName);
        }
        PrintWriter outFS = new PrintWriter(fileStream);
        outFS.printf("Congratulations %s! You completed the Escape House!\n", userName);
        outFS.printf("%s you took %d turns to escape the house!\n", userName, playerInventory.getSteps());
        outFS.println("Compare to your friends and see how you did!");
        outFS.close();
    }
}
