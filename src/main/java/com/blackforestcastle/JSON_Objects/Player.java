package com.blackforestcastle.JSON_Objects;

import com.blackforestcastle.JSON_Objects.Character;
import com.blackforestcastle.JSON_Objects.Item;
import com.blackforestcastle.JSON_Objects.Room;

import java.util.List;
import java.util.Scanner;

public class Player extends Character {

    //private Room_v2 currentRoom;
    private int experiencePoints;
    private String name;

    public Player() {
        setExperiencePoints(0);
    }

    public Player(Room_v2 theRoom) {
        this();
        setCurrentRoom(theRoom);
    }

    public Player(Room_v2 theCurrentRoom, int theHp) {
        this(theCurrentRoom);
        super.setHP(theHp);
    }

    public Player(Room_v2 theCurrentRoom, int theHp, String theName) {
        this(theCurrentRoom, theHp);
        setName(theName);
    }

    @Override
    public void attack(Character npc) {
        getPlayerAttackPower();
        int damageDone = getAttackPower() + randomNumber(10, 0);
        npc.setHP(npc.getHP() - damageDone);
        System.out.println("You did " + damageDone + " damage. The enemies health now is " + npc.getHP());
    }

    // helper for attack method
    public void getPlayerAttackPower() {
        //whyyyyyyyyyyyyyyyyyyyyyyyyyyy
        // knife, sword, bow, arrow
        for (Item item : super.getInventory()) {
            if (item.getName().equals("bow")) {
                for (Item item0 : super.getInventory()) {
                    if (item0.getName().equals("arrows")) {
                        setAttackPower(15);
                    }
                }

            } else if (item.getName().equals("sword")) {
                setAttackPower(10);
            } else if (item.getName().equals("knife")) {
                setAttackPower(5);
            }

        }
    }

    public void showInventory() {
        System.out.print("Inventory: ");
        for (Item item : super.getInventory()) {
            System.out.print("\t" + item.getName());
        }
        System.out.println();
    }

    public Item checkInventoryForItem(String theItem) {
        for (Item itemObject : super.getInventory()) {
            if (theItem.equals(itemObject.getName())) {
                return itemObject;
            }
        }
        return null;
    }

    public boolean keepsFighting() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \"Q\" to exit the battle\nTo continue, enter any other key.");
        String input = scanner.nextLine();
        if (input.trim().equalsIgnoreCase("q"))
            return false;
        return true;
    }

    public List<Item> getInventory() {
        return super.getInventory();
    }

//    //public Room_v2 getCurrentRoom() {
//        return currentRoom;
//    }

    //  //  public void setCurrentRoom(Room_v2 currentRoom) {
//        this.currentRoom = currentRoom;
//    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }


    public String getName() {
        if (this.name == null)
            return "Anonymous";
        else return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
