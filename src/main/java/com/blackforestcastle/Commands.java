package com.blackforestcastle;

import org.apache.commons.io.IOUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class Commands {
    Controller controller = Controller.getInstance();
    NPC npc = new NPC();
    JSONReader jsonReader = new JSONReader();
    Room[] rooms = jsonReader.getRooms();
    Player player = new Player(rooms[0], 100);
    String previousCommand = "";


    //Parse input text and return as an array split into verb and noun
    String[] input() {

        System.out.print(">>");
        while(UI.pressed_enter==false){

            System.out.print("");
        }

        UI.pressed_enter = false;
        String input = UI.textField.getText();
        UI.textField.setText("");
        previousCommand = input;

        String[] splitInput = input.split(" ");// Read user input and split into an array based off of regex.
        return splitInput;
    }

    public void interact() {
        UI.textPrint("------------");
        UI.textPrint(player.getCurrentRoom().roomInfo(player));
        UI.textPrint("What would you like to do?");
        String[] input = input();
        ConsoleUtilities.clearConsole();
        String verb = "";
        String noun = "";
        if (input.length == 1) {
            verb = input[0].toLowerCase();
        } else if (input.length == 2) {
            verb = input[0].toLowerCase();
            noun = input[1].toLowerCase();
        } else {
            UI.textPrint("Please enter a valid command, such as: ");
            controller.commandsInstructions();
            return;
        }

        switch (verb) {
            case "go":
            case "move":
                go(noun);
                break;

            case "bag":
            case "inventory":
                bag();
                break;
            case "get":
            case "grab":
            case "take":
            case "pick":
                get(noun);
                break;
            case "drop":
            case "yeet":
            case "release":
                drop(noun);
                break;
            case "attack":
            case "fight":
            case "combat":
                attack();
                break;
            case "look":
            case "show":
            case "see":
                look();
                break;
            case "teleport":
            case "warp":
                teleport(noun);
                break;
            case "?":
            case "help":
                help();
                break;
            case "quit":
            case "exit":
            case "terminate":
                controller.quitGame();
                break;
            case "map":
                map();
                break;
            case "new":
            case "restart":
                controller.newGame();
                break;
            case "use":
                use(noun);
        }
    }

    private void use(String noun) {
        Item itemObject = player.checkInventoryForItem(noun);
        boolean wonGame = false;
        if (itemObject != null && itemObject.getName().equals(noun)) {
            switch (noun) {
                case "mead":
                    player.setHP(player.getHP() + 15);
                    break;
                case "bread":
                    player.setHP(player.getHP() + 5);
                    break;
                case "turkey":
                    player.setHP(player.getHP() + 20);
                    break;
                case "potion":
                    player.setHP(player.getHP() + 30);
                    break;
                case "book":
                    UI.textPrint("A page suggest a key is located somewhere in the bedroom.");
                    break;
                case "lever":
                    if (player.currentRoom.equals(rooms[0]) && itemObject.getName().equals("lever")) {
                        UI.textPrint("You insert the lever into the pulley and begin to crank clockwise, the portcullis raises opening the way you got in.\n" + "You hastily escape through the entrance to freedom.");
                        UI.textPrint("Congratulations you win the game!");
                        controller.quitGame();
                        wonGame = true;
                    }
                    break;
                case "key":

                    if (player.currentRoom.equals(rooms[5])) {
                        UI.textPrint("After using the key, you see a lever in the chest.");
                        rooms[5].itemObjects.add(jsonReader.getItems()[7]);

                    }
                    break;
            }

            player.inventory.remove(itemObject);
            if (!wonGame) {
                UI.textPrint("Used: " + itemObject.getName());
            }
        }
    }

    void go(String direction) {
        if (direction.matches("north|east|south|west")) {
            goToRoom(direction);
        } else {
            UI.textPrint("Invalid direction, enter a valid direction.");
        }
    }

    void get(String item) {
        Item itemObject = player.currentRoom.checkRoomForItem(item);
        if (itemObject != null && itemObject.getName().equals(item)) {
            player.inventory.add(itemObject);
            UI.textPrint("Picked up: " + itemObject.getName());
            player.currentRoom.itemObjects.remove(itemObject);
        }
    }

    void drop(String item) {
        Item itemObject = player.checkInventoryForItem(item);
        if (itemObject != null && itemObject.getName().equals(item)) {
            player.currentRoom.itemObjects.add(itemObject);
            player.inventory.remove(itemObject);
            UI.textPrint("Dropped: " + itemObject.getName());
        }
    }

    void map() {
        try {
            String result = IOUtils.toString(new InputStreamReader(Commands.class.getResourceAsStream("/map.txt"), StandardCharsets.UTF_8));
            UI.textPrint(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bag() {
        player.showInventory();
    }

    void attack() {
        if (!player.currentRoom.getNpcObjects().isEmpty()) {
            battle();
        }
    }

    void battle() {
        Character npc = player.currentRoom.getNpcObjects().get(0);
        boolean battleOngoing = true;
        while (battleOngoing) {
            player.attack(npc);
            if (npc.getHP() <= 0) {
                UI.textPrint("You won the battle!!");
                player.currentRoom.getNpcObjects().remove(npc);
                break;
            }
            npc.attack(player);
            if (player.getHP() <= 0) {
                UI.textPrint("*** You're dead.. *** :(");
                UI.textPrint("\n");
                controller.newGame();

            }

        }
    }

    void look() {
        UI.textPrint(player.getCurrentRoom().roomInfo(player));
    }

    void teleport(String room) {
        boolean hasRing = false;
        for (Item itemObj : player.inventory) {
            if (itemObj.getName().equals("ring")) {
                hasRing = true;
                break;
            }
        }
        for (Room roomX : rooms) {
            if (roomX.getName().toLowerCase().equals(room) && hasRing) {
                player.setCurrentRoom(roomX);
                UI.textPrint("Teleported to: " + roomX.getName());
                break;
            }
        }
    }

    void help() {
        controller.commandsInstructions();
    }

    //Helper functions
    void goToRoom(String direction) {
        for (Room room : rooms) {
            if (room.getName().equals(player.getCurrentRoom().getDirection(direction))) {
                player.setCurrentRoom(room);
                break;
            }
        }
    }
}