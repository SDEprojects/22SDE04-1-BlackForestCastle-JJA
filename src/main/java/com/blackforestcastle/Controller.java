package com.blackforestcastle;

import com.blackforestcastle.JSON_Objects.Building;
import com.blackforestcastle.JSON_Objects.GameInfo;
import com.blackforestcastle.JSON_Objects.Player;
import com.blackforestcastle.utility.JSONReader;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Controller {

    private boolean gameRunning;
    //private static Controller controller_instance = null;
    private JSONReader jsonReader = new JSONReader();
    private Scanner scanner;
    private GameInfo gameInfo;
    private Building building;
    private Player player;

    public Controller() {
        setGameRunning(true);
        setJsonReader(new JSONReader());
        setScanner(new Scanner(System.in));
        setGameInfo(jsonReader.makeGameInfoObjects());
        //change these to reflect a default state current place holder
        setBuilding(new Building());
        setPlayer(new Player());
    }


//    public static Controller getInstance() {
//        if (controller_instance == null) {
//            controller_instance = new Controller();
//        }
//        return controller_instance;
//    }



    public void gameLoop() {
        Commands commands = new Commands();
        //print welcome message at start
        welcome();
        //basic_info();
        //print acceptable commands at start
        commandsInstructions();
        while (gameRunning) {

            commands.interact();
        }
    }

//    private void basic_info() {
//        System.out.println(gameInfo.getInfo());
//    }

    private void welcome() {
        try {
            String result = IOUtils.toString(new InputStreamReader(Controller.class.getResourceAsStream("/title.txt"), StandardCharsets.UTF_8));
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commandsInstructions() {
        System.out.println(gameInfo.getInfo());
    }

    public void quitGame(Player player) {
        System.out.println("Thanks for playing!");
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