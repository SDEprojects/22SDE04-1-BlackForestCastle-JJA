package com.blackforestcastle;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Controller {
    private boolean gameRunning;

    private static Controller controller_instance = null;
    JSONReader jsonReader = new JSONReader();

    private Controller() {
        gameRunning = true;
    }

    public static Controller getInstance() {
        if (controller_instance == null) {
            controller_instance = new Controller();
        }
        return controller_instance;
    }

    void gameLoop() {
        Commands commands = new Commands();
        welcome();
        basic_info();
        commandsInstructions();
        while (gameRunning) {
            commands.interact();
        }
    }

    private void basic_info() {
        String basicInfo = jsonReader.getGameInfo()[1].getInfo();
        UI.textPrint(basicInfo);
    }

    private void welcome() {
        try {
            String result = IOUtils.toString(new InputStreamReader(Controller.class.getResourceAsStream("/title.txt"), StandardCharsets.UTF_8));
            UI.textPrint(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commandsInstructions() {
        String basicInfo = jsonReader.getGameInfo()[0].getInfo();
        UI.textPrint(basicInfo);
    }

    public void quitGame() {
        UI.textPrint("Thanks for playing!");
        gameRunning = false;
    }

    public void newGame() {
        gameLoop();
    }
}