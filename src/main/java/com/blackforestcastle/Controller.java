package com.blackforestcastle;

import com.blackforestcastle.JSON_Objects.*;
import com.blackforestcastle.utility.JSONReader;
import org.apache.commons.io.IOUtils;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private boolean gameRunning;
    //private static Controller controller_instance = null;
    private JSONReader jsonReader;
    private Scanner scanner;
    private GameInfo gameInfo;
    private Building building;
    private Player player;

    public Controller() {
        initializeDefaults();
    }

    private void initializeDefaults() {
        setGameRunning(true);
        setJsonReader(new JSONReader());
        setScanner(new Scanner(System.in));
        setGameInfo(jsonReader.makeGameInfoObjects());
        //change these to reflect a default state current place holder
        setBuilding(buildBuildingDefault());
        setPlayer(new Player("Bob"));
    }


    public void gameLoop() {
        //initialize anything needed here
        Commands_v2 command_controller = new Commands_v2();
        //print initial welcome message
        welcome();
        //show available instructions
        System.out.println(gameInfo.getInfo());
        //start game loop

        while (gameRunning) {
            //display room info
            System.out.println("------------");
            //print out room info
            System.out.println(building.getCurrentRoom().toString());
            //print out player info
            System.out.println(player.toString());
            System.out.println("What would you like to do?");

            ConsoleUtilities.clearConsole();

            //grab user input
            String[] userInput = input();
            //if new game re run loop
            if (userInput == null && userInput[0].matches("new|restart")) {
                initializeDefaults();
                gameLoop();
            }
            //react to input
            command_controller.interact(building, player, userInput, gameInfo);
            //update objects

            //repeat loop
        }


    }

    private String[] input() {
        String[] splitInput = null;
        try {
            System.out.print(">>");
            String input = scanner.nextLine();
            //previousCommand = input;
            splitInput = input.trim().split(" ");// Read user input and split into an array based off of regex.
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private Building buildBuildingDefault() {
        Room_v2[] allRooms = jsonReader.makeRoomObjects();
        List<Item> allItems = jsonReader.makeItemObjects();
        List<NPC> allNpcs = jsonReader.makeNPCObjects();
        for (Room_v2 room :
                allRooms) {

            //default add key to the bedroom
            if (room.getName().equalsIgnoreCase("bedroom")) {
                for (Item item :
                        allItems) {
                    if (item.getName().equalsIgnoreCase("key")) {
                        room.getItems().add(item);
                        break;
                    }

                }
            }
            //add one random item and npc to each room
            room.addRandomItem(allItems);
            room.addRandomNpc(allNpcs);
            room.getNpcs().get(0).addRandomItemToInventory(allItems);

        }
        //explicit easier to read instiation of a default building. Hard codeed and Modeled after map.txt
        Room_v2[][] defaultMap =
                {
                        {null, allRooms[0], null},
                        {allRooms[1], allRooms[2], allRooms[3]},
                        {allRooms[4], allRooms[5], allRooms[6]},
                        {allRooms[7], allRooms[8], allRooms[9]}
                };
        Building building = new Building(defaultMap, defaultMap[0][1],new Point(0,1));

        return building;
    }

    private void welcome() {
        try {
            String result = IOUtils.toString(new InputStreamReader(Controller.class.getResourceAsStream("/title.txt"), StandardCharsets.UTF_8));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    private void basic_info() {
        String basicInfo = jsonReader.getGameInfo()[1].getInfo();
        UI.textPrint(basicInfo);
    }


    public void printCommandsInstructions() {
        System.out.println(gameInfo.getInfo());
        UI.textPrint(gameInfo.getInfo());
    }

    public void quitGame(Player player) {
        System.out.println("Thanks for playing!");
        UI.textPrint("Thanks for playing!");
        EndGame.saveScore(player);
        gameRunning = false;
    }

    public void newGame() {
        gameLoop();
    }


    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public JSONReader getJsonReader() {
        return jsonReader;
    }

    public void setJsonReader(JSONReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}