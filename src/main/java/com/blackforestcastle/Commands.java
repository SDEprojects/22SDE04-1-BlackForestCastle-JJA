package com.blackforestcastle;

import static com.blackforestcastle.NPC.isTaunting;


public class Commands {
    Controller controller = Controller.getInstance();
    NPC npc = new NPC();
    JSONReader jsonReader = new JSONReader();
    Room[] rooms = jsonReader.getRooms();
    Player player = new Player(rooms[0], 100);
    String previousCommand = "";


    //Parse input text and return as an array split into verb and noun
    String[] input() {

        UI.textPrint(">>");

        while(!UI.pressed_enter ){
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
        UI.mapTabGraphicPrint(String.valueOf(player.getHP()));

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
                    if (player.getCurrentRoom().equals(rooms[0]) && itemObject.getName().equals("lever")) {
                        UI.textPrint("You insert the lever into the pulley and begin to crank clockwise, the portcullis raises opening the way you got in.\n" + "You hastily escape through the entrance to freedom.");
                        UI.textPrint("Congratulations you win the game!");
                        controller.quitGame(player);
                        wonGame = true;
                    }
                    break;
                case "key":

                    if (player.getCurrentRoom().equals(rooms[5]))
                    {
                        UI.textPrint("After using the key, you see a lever in the chest.");
                        rooms[5].getItemObjects().add(jsonReader.getItems()[7]);
                    }
                    break;
            }

            if (!player.getCurrentRoom().equals(rooms[0]) && itemObject.getName().equals("lever"))
                //do not remove the lever
                //this allows the player to win the game
                UI.textPrint("You cannot use the lever in this room.");
            else player.getInventory().remove(itemObject);


            if (!wonGame)
            {
                UI.textPrint("Used: " + itemObject.getName());
            }
        }
    }

    void go(String direction)
    {
        if (direction.matches("north|east|south|west"))
        {
            goToRoom(direction);
        } else
        {
            UI.textPrint("Invalid direction, enter a valid direction.");
        }
    }

    //Helper functions
    private void goToRoom(String direction)
    {
        for (Room room : rooms)
        {
            if (room.getName().equals(player.getCurrentRoom().getDirection(direction)))
            {
                player.setCurrentRoom(room);
                break;
            }
        }
    }


    void get(String item)
    {
        Item itemObject = player.getCurrentRoom().checkRoomForItem(item);
        if (itemObject != null && itemObject.getName().equals(item))
        {
            player.getInventory().add(itemObject);
            UI.textPrint("Picked up: " + itemObject.getName());

            int attackBoost = itemObject.getAttackPointsForItem();
            int defenseBoost = itemObject.getDefensePointsForItem();
            int hpBoost = itemObject.getHpPointsForItem();

            if(attackBoost != 0){
                player.addAttackPower(attackBoost);
                UI.textPrint("Gained: " + attackBoost + " attack points.");
            }
            if (defenseBoost != 0){
                player.addDefensePoints(defenseBoost);
                UI.textPrint("Gained: " + defenseBoost + " defense points.");
            }
            if (hpBoost !=0 ){
                player.addHP(hpBoost);
                UI.textPrint("Gained: " + attackBoost + " health points.");
            }

            player.getCurrentRoom().getItemObjects().remove(itemObject);
        }
    }

    void drop(String item)
    {
        Item itemObject = player.checkInventoryForItem(item);
        if (itemObject != null && itemObject.getName().equals(item))
        {
            player.getCurrentRoom().getItemObjects().add(itemObject);
            player.getInventory().remove(itemObject);
            UI.textPrint("Dropped: " + itemObject.getName());
        }
    }


    void bag()
    {
        player.showInventory();
    }

    private void attemptCombat()
    {
        if (!player.getCurrentRoom().getNpcObjects().isEmpty())
            battle();
        else
            UI.textPrint("No combatants in this room");
    }


    private void battle()
    {
        NPC npc = player.getCurrentRoom().getNpcObjects().get(0);
        boolean battleOngoing = true;
        while (true)
        {
            player.attack(npc);
            if (npc.getHP() <= 0)
            {
                UI.textPrint("You won the battle and gained 100 experience points!!");
                player.addExperiencePoints(100);
                //add random item to enemy npc
                npc.addRandomItemToInventory();
                for (Item npcItem :
                        npc.getInventory())
                {
                    UI.textPrint(npc.getName() + " dropped " + npcItem.getName());
                    player.getCurrentRoom().getItemObjects().add(npcItem);
                }

                player.getCurrentRoom().getNpcObjects().remove(npc);
                break;
            }
            if(isTaunting())
                npc.printRandomTaunt();
            npc.attack(player);
            if (player.getHP() <= 0)
            {
                UI.textPrint("*** You're dead.. *** :(\n");
                EndGame.saveScore(player);
                controller.newGame();
            }

            if (!player.keepsFighting()) {
                UI.textPrint("You lost the battle but did not lose the war");
                break;
            }
        }
    }

    void look()
    {
        UI.textPrint(player.getCurrentRoom().roomInfo(player));
    }

    void teleport(String room)
    {
        boolean hasRing = false;
        for (Item itemObj : player.getInventory())
        {
            if (itemObj.getName().equals("ring"))
            {
                hasRing = true;
                break;
            }
        }
        for (Room roomX : rooms)
        {
            if (roomX.getName().toLowerCase().equals(room) && hasRing)
            {
                player.setCurrentRoom(roomX);
                UI.textPrint("Teleported to: " + roomX.getName());
                break;
            }
        }
    }
    void help()
    {
        controller.commandsInstructions();
    }
}