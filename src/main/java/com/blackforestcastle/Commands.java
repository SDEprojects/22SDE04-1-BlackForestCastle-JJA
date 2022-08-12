package com.blackforestcastle;

import com.blackforestcastle.JSON_Objects.*;
import com.blackforestcastle.utility.JSONReader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.blackforestcastle.JSON_Objects.NPC.isTaunting;

public class Commands {
    //controller should be top level
    private Controller controller;
    //NPC npc = new NPC();
    private JSONReader jsonReader;
    //Room[] rooms = jsonReader.makeRoomObjects();
    private Player player;
    private String previousCommand = "";


    //Parse input text and return as an array split into verb and noun
    private String[] input(Scanner theScanner) {
        String[] splitInput = null;
        try {

            System.out.print(">>");
            String input = theScanner.nextLine();
            previousCommand = input;
            splitInput = input.trim().split(" ");// Read user input and split into an array based off of regex.

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return splitInput;
    }

    public void interact() {

        System.out.println("------------");
        System.out.println(player.getCurrentRoom().roomInfo(player));
        System.out.println("What would you like to do?");
        //????? whyyyyyyyyyyyyyy
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
            System.out.println("Please enter a valid command, such as: ");
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
                attemptCombat();
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
                controller.quitGame(player);
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
                break;
            case "scores":
                GameInfo.viewScores();
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
                    System.out.println("A page suggest a key is located somewhere in the bedroom.");
                    break;
                case "lever":
                    if (player.getCurrentRoom().equals(rooms[0]) && itemObject.getName().equals("lever")) {
                        System.out.println("You insert the lever into the pulley and begin to crank clockwise, the portcullis raises opening the way you got in.\n" + "You hastily escape through the entrance to freedom.");
                        System.out.println("Congratulations you win the game!");
                        controller.quitGame(player);
                        wonGame = true;
                    }
                    break;
                case "key":

                    if (player.getCurrentRoom().equals(rooms[5])) {
                        System.out.println("After using the key, you see a lever in the chest.");
                        rooms[5].getItemObjects().add(jsonReader.makeItemObjects()[7]);
                    }
                    break;
            }

            if (!player.getCurrentRoom().equals(rooms[0]) && itemObject.getName().equals("lever"))
                //do not remove the lever
                //this allows the player to win the game
                System.out.println("You cannot use the lever in this room.");
            else player.getInventory().remove(itemObject);


            if (!wonGame) {
                System.out.println("Used: " + itemObject.getName());
            }
        }
    }

    void go(String direction) {
        if (direction.matches("north|east|south|west")) {
            goToRoom(direction);
        } else {
            System.out.println("Invalid direction, enter a valid direction.");
        }
    }

    //Helper functions
    private void goToRoom(String direction) {
        for (Room room : rooms) {
            if (room.getName().equals(player.getCurrentRoom().getDirection(direction))) {
                player.setCurrentRoom(room);
                break;
            }
        }
    }


    void get(String item) {
        Item itemObject = player.getCurrentRoom().checkRoomForItem(item);
        if (itemObject != null && itemObject.getName().equals(item)) {
            player.getInventory().add(itemObject);
            System.out.println("Picked up: " + itemObject.getName());

            int attackBoost = itemObject.getAttackPointsForItem();
            int defenseBoost = itemObject.getDefensePointsForItem();
            int hpBoost = itemObject.getHpPointsForItem();

            if (attackBoost != 0) {
                player.addAttackPower(attackBoost);
                System.out.println("Gained: " + attackBoost + " attack points.");
            }
            if (defenseBoost != 0) {
                player.addDefensePoints(defenseBoost);
                System.out.println("Gained: " + defenseBoost + " defense points.");
            }
            if (hpBoost != 0) {
                player.addHP(hpBoost);
                System.out.println("Gained: " + attackBoost + " health points.");
            }

            player.getCurrentRoom().getItemObjects().remove(itemObject);
        }
    }

    void drop(String item) {
        Item itemObject = player.checkInventoryForItem(item);
        if (itemObject != null && itemObject.getName().equals(item)) {
            player.getCurrentRoom().getItemObjects().add(itemObject);
            player.getInventory().remove(itemObject);
            System.out.println("Dropped: " + itemObject.getName());
        }
    }

    void map() {
        try {
            String result = IOUtils.toString(new InputStreamReader(Commands.class.getResourceAsStream("/map.txt"), StandardCharsets.UTF_8));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bag() {
        player.showInventory();
    }

    private void attemptCombat() {
        if (!player.getCurrentRoom().getNpcObjects().isEmpty())
            battle();
        else
            System.out.println("No combatants in this room");
    }


    private void battle() {
        NPC npc = player.getCurrentRoom().getNpcObjects().get(0);
        boolean battleOngoing = true;
        while (battleOngoing) {
            player.attack(npc);
            if (npc.getHP() <= 0) {
                System.out.println("You won the battle and gained 100 experience points!!");
                player.addExperiencePoints(100);
                //add random item to enemy npc
                npc.addRandomItemToInventory();
                for (Item npcItem :
                        npc.getInventory()) {
                    System.out.println(npc.getName() + " dropped " + npcItem.getName());
                    player.getCurrentRoom().getItemObjects().add(npcItem);
                }

                player.getCurrentRoom().getNpcObjects().remove(npc);
                break;
            }
            if (isTaunting())
                npc.printRandomTaunt();
            npc.attack(player);
            if (player.getHP() <= 0) {
                System.out.println("*** You're dead.. *** :(\n");
                EndGame.saveScore(player);
                controller.newGame();
            }

            if (!player.keepsFighting()) {
                System.out.println("You lost the battle but did not lose the war");
                break;
            }
        }
    }

    void look() {
        System.out.println(player.getCurrentRoom().roomInfo(player));
    }

    void teleport(String room) {
        boolean hasRing = false;
        for (Item itemObj : player.getInventory()) {
            if (itemObj.getName().equals("ring")) {
                hasRing = true;
                break;
            }
        }
        for (Room roomX : rooms) {
            if (roomX.getName().toLowerCase().equals(room) && hasRing) {
                player.setCurrentRoom(roomX);
                System.out.println("Teleported to: " + roomX.getName());
                break;
            }
        }
    }

    void help() {
        controller.commandsInstructions();
    }
}