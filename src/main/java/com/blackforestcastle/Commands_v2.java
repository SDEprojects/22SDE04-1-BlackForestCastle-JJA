package com.blackforestcastle;

import com.blackforestcastle.JSON_Objects.*;

import java.awt.*;

import static com.blackforestcastle.JSON_Objects.NPC.isTaunting;

public class Commands_v2 {

    //private String previousCommand;
    public Commands_v2() {
    }

    public void interact(Building theBuilding, Player thePlayer, String[] theInput, GameInfo theGameInfo) {

        String firstStatement = "";
        String secondStatement = "";

        if (theInput.length == 1) {
            firstStatement = theInput[0].toLowerCase();
        }
        if (theInput.length == 2) {
            firstStatement = theInput[0].toLowerCase();
            secondStatement = theInput[1].toLowerCase();
        }
        switch (firstStatement) {
            case "go":
            case "move":
                go(theBuilding, secondStatement);
                break;

            case "bag":
            case "inventory":
                thePlayer.showInventory();
                break;
            case "get":
            case "grab":
            case "take":
            case "pick":
                get(theBuilding, secondStatement, thePlayer);
                break;
            case "drop":
            case "yeet":
            case "release":
                drop(theBuilding, secondStatement, thePlayer);
                break;
            case "attack":
            case "fight":
            case "combat":
                battle(theBuilding, thePlayer, secondStatement);
                break;
            case "look":
            case "show":
            case "see":
                System.out.println(theBuilding.getCurrentRoom().toString());
                UI.textPrint(theBuilding.getCurrentRoom().toString());
                ;
                break;
            case "teleport":
            case "warp":
                teleport(theBuilding, secondStatement);
                break;
            case "?":
            case "help":
                System.out.println(theGameInfo.getInfo());
                UI.textPrint(theGameInfo.getInfo());

                ;
                break;
            case "quit":
            case "exit":
            case "terminate":
                quitGame(thePlayer);
                break;
            case "use":
                use(thePlayer, secondStatement,theBuilding);
                break;
            case "scores":
                GameInfo.viewScores();
                break;
            case "heal":
                thePlayer.heal();
                break;
            default:
                System.out.println("invalid command");
                UI.textPrint("invalid command");
        }


    }


    private boolean checkValidDirection(Building theBuilding, int theChangeInX, int theChangeInY) {
        boolean solution = false;
        try {
            int x = (int) theBuilding.getCurrentRoomCoordinates().getX() + theChangeInX;
            int y = (int) theBuilding.getCurrentRoomCoordinates().getY() + theChangeInY;
            Room_v2 validRoom = theBuilding.getRooms()[x][y];
            //if this is reached is a valid room set return true;
            solution = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("nothing is in that direction...");
        }


        return solution;
    }

    private void go(Building theBuilding, String theDirection) {
        int theCurrentX = (int) theBuilding.getCurrentRoomCoordinates().getX();
        int theCurrentY = (int) theBuilding.getCurrentRoomCoordinates().getY();

        if (theDirection.matches("north|east|south|west")) {
            switch (theDirection) {
                case "north":
                    if (checkValidDirection(theBuilding, -1, 0)) {
                        theCurrentX += -1;
                        theCurrentY += 0;
                        theBuilding.setCurrentRoom(theBuilding.getRooms()[theCurrentX][theCurrentY]);
                        theBuilding.setCurrentRoomCoordinates(new Point(theCurrentX, theCurrentY));
                        System.out.println("Moved to the " + theBuilding.getCurrentRoom().getName());
                        UI.textPrint("Moved to the " + theBuilding.getCurrentRoom().getName());
                    }
                    break;
                case "south":
                    if (checkValidDirection(theBuilding, 1, 0)) {
                        theCurrentX += 1;
                        theCurrentY += 0;
                        theBuilding.setCurrentRoom(theBuilding.getRooms()[theCurrentX][theCurrentY]);
                        theBuilding.setCurrentRoomCoordinates(new Point(theCurrentX, theCurrentY));
                        System.out.println("Moved to the " + theBuilding.getCurrentRoom().getName());
                        UI.textPrint("Moved to the " + theBuilding.getCurrentRoom().getName());
                    }
                    break;
                case "east":
                    if (checkValidDirection(theBuilding, 0, 1)) {
                        theCurrentX += 0;
                        theCurrentY += 1;
                        theBuilding.setCurrentRoom(theBuilding.getRooms()[theCurrentX][theCurrentY]);
                        theBuilding.setCurrentRoomCoordinates(new Point(theCurrentX, theCurrentY));
                        System.out.println("Moved to the " + theBuilding.getCurrentRoom().getName());
                        UI.textPrint("Moved to the " + theBuilding.getCurrentRoom().getName());
                    }
                    break;
                case "west":
                    if (checkValidDirection(theBuilding, 0, -1)) {
                        theCurrentX += 0;
                        theCurrentY += -1;
                        theBuilding.setCurrentRoom(theBuilding.getRooms()[theCurrentX][theCurrentY]);
                        theBuilding.setCurrentRoomCoordinates(new Point(theCurrentX, theCurrentY));
                        System.out.println("Moved to the " + theBuilding.getCurrentRoom().getName());
                        UI.textPrint("Moved to the " + theBuilding.getCurrentRoom().getName());
                    }
                    break;
            }
        }
    }

    private void get(Building theBuilding, String theItemName, Player thePlayer) {
        for (Item itemInTheRoom :
                theBuilding.getCurrentRoom().getItems()) {
            if (itemInTheRoom.getName().equalsIgnoreCase(theItemName)) {
                thePlayer.getInventory().add(itemInTheRoom);
                theBuilding.getCurrentRoom().getItems().remove(itemInTheRoom);
                System.out.println("Player added " + itemInTheRoom.getName() + " to inventory");
                UI.textPrint("Player added " + itemInTheRoom.getName() + " to inventory");
                break;
            }
        }
    }

    private void drop(Building theBuilding, String theItemName, Player thePlayer) {
        for (Item itemInTheRoom :
                theBuilding.getCurrentRoom().getItems()) {
            if (itemInTheRoom.getName().equalsIgnoreCase(theItemName)) {
                thePlayer.getInventory().remove(itemInTheRoom);
                theBuilding.getCurrentRoom().getItems().add(itemInTheRoom);
                System.out.println("Player added " + itemInTheRoom.getName() + " to inventory");
                UI.textPrint("Player added " + itemInTheRoom.getName() + " to inventory");
                break;
            }
        }
    }

    private void battle(Building theBuilding, Player thePlayer, String theNPCName) {
        if (theBuilding.getCurrentRoom().getNpcs().isEmpty()) {
            System.out.println("there's nothing to attack in here");
            UI.textPrint("there's nothing to attack in here");
        } else {
            for (NPC enemyInTheRoom :
                    theBuilding.getCurrentRoom().getNpcs()) {
                if (enemyInTheRoom.getName().equalsIgnoreCase(theNPCName)) {
                    boolean bothAlive = true;
                    while (bothAlive) {
                        //enemy taunt random
                        if (isTaunting()) {
                            enemyInTheRoom.printRandomTaunt();
                        }
                        //player attacks
                        thePlayer.attack(enemyInTheRoom);
                        //enemy attacks
                        enemyInTheRoom.attack(thePlayer);
                        //check if player is alive
                        if (thePlayer.getHP() <= 0) {
                            System.out.println("*** You're dead.. *** :(\n");
                            UI.textPrint("*** You're dead.. *** :(\n");
                            EndGame.saveScore(thePlayer);
                            bothAlive = false;
                        }
                        //check if enemy alive
                        else if (enemyInTheRoom.getHP() <= 0) {
                            bothAlive = false;
                            System.out.println("You won the battle and gained 100 experience points!!");
                            UI.textPrint("You won the battle and gained 100 experience points!!");

                            thePlayer.setExperiencePoints(thePlayer.getExperiencePoints() + 100);
                            if (!enemyInTheRoom.getInventory().isEmpty()) {
                                System.out.println("Enemy dropped " + enemyInTheRoom.getInventory().get(0).getName() + " on the ground!");
                                UI.textPrint("Enemy dropped " + enemyInTheRoom.getInventory().get(0).getName() + " on the ground!");
                                theBuilding.getCurrentRoom().getItems().add(enemyInTheRoom.getInventory().get(0));
                            }
                            theBuilding.getCurrentRoom().getNpcs().remove(enemyInTheRoom);


                        }

                        //loop
                    }
                    break;
                }
            }
        }
    }

    private void use(Player thePlayer, String theItemName, Building theBuilding) {
        for (Item item :
                thePlayer.getInventory()) {
            if (item.getName().equalsIgnoreCase(theItemName)) {
                switch (item.getType()) {
                    case "attack":
                        thePlayer.addAttackPower(item.getPower());
                        thePlayer.getInventory().remove(item);
                        break;
                    case "defense":
                        thePlayer.addDefensePoints(item.getPower());
                        thePlayer.getInventory().remove(item);
                        break;
                    case "health":
                        thePlayer.addHP(item.getPower());
                        thePlayer.getInventory().remove(item);
                        break;
                    case "special":
                        thePlayer.addAttackPower(item.getPower());
                        thePlayer.addDefensePoints(item.getPower());
                        thePlayer.addHP(item.getPower());
                        break;
                    case "key":
                        if (theItemName.equalsIgnoreCase("lever") && theBuilding.getCurrentRoom().getName().equalsIgnoreCase("Entrance")) {
                            System.out.println("You insert the lever into the pulley and begin to crank clockwise, the portcullis raises opening the way you got in.\n" + "You hastily escape through the entrance to freedom.");
                            UI.textPrint("You insert the lever into the pulley and begin to crank clockwise, the portcullis raises opening the way you got in.\n" + "You hastily escape through the entrance to freedom.");
                            System.out.println("Congratulations you win the game!");
                            UI.textPrint("Congratulations you win the game!");
                            quitGame(thePlayer);
                        }
                    default:
                        if (item.getName().equalsIgnoreCase("book")) {
                            System.out.println("A page suggest a key is located somewhere in the bedroom.");
                            UI.textPrint("A page suggest a key is located somewhere in the bedroom.");
                        } else {
                            System.out.println("The item either isn't usable or doesn't exist..");
                            UI.textPrint("The item either isn't usable or doesn't exist..");
                        }
                }
                break;
            }
        }
    }

    private void quitGame(Player thPlayer) {
        System.out.println("Thanks for playing!");
        UI.textPrint("Thanks for playing!");
        EndGame.saveScore(thPlayer);
        System.exit(0);
    }




    private void teleport(Building theBuilding, String theRoomName) {
        for (int i = 0; i < theBuilding.getRooms().length; i++) {
            int y = 0;
            for (Room_v2 room :
                    theBuilding.getRooms()[i]) {
                if (room.getName().equalsIgnoreCase(theRoomName)) {
                    theBuilding.setCurrentRoom(room);
                    theBuilding.setCurrentRoomCoordinates(new Point(i, y));
                    System.out.println("Teleported to " + theRoomName);
                    UI.textPrint("Teleported to " + theRoomName);
                    System.out.println(room.toString());
                    UI.textPrint(room.toString());


                }
                y++;
            }
        }


    }


}