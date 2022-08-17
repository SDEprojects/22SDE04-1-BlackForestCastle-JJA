package com.blackforestcastle;

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

    public void commandsInstructions() {
        String basicInfo = jsonReader.getGameInfo()[0].getInfo();
        UI.textPrint(basicInfo);
    }

    public void quitGame(Player player) {
        UI.textPrint("Thanks for playing!");
        EndGame.saveScore(player);
        gameRunning = false;
    }

    public void newGame() {
        gameLoop();
    }
}