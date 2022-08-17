package com.blackforestcastle.JSON_Objects;

import java.util.Scanner;

public class Player extends Character {

    //private Room_v2 currentRoom;
    private int experiencePoints;
    final private int DEFAULT_HEALTH_POINTS = 100;

    public Player() {

        setHP(DEFAULT_HEALTH_POINTS);
        setExperiencePoints(0);
        setHP(100);
        setAttackPower(10);
        setDefendPower(10);

    }

    public Player(String theName) {
        this();
        super.setName(theName);
    }

    public Player(String theName, int theHp) {
        this(theName);
        super.setHP(theHp);
    }


    @Override
    public void attack(Character npc) {
        int damageDone = getAttackPower() + randomNumber(10, 0);
        npc.setHP(npc.getHP() - damageDone);
        System.out.println("You did " + damageDone + " damage. The enemies health now is " + npc.getHP());
    }

    public void showInventory() {
        System.out.print("Inventory: ");
        for (Item item : super.getInventory()) {
            System.out.print("\t" + item.getName());
        }
        System.out.println();
    }


    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Player's name = " + super.getName() + "\n");
        stringBuilder.append("Attack power = " + super.getAttackPower());
        stringBuilder.append("\t Defense power = " + super.getDefendPower());
        stringBuilder.append("\t Health Points = " + super.getHP());
        stringBuilder.append("\n");
        stringBuilder.append("Items in your inventory... " + "\t");
        for (Item item :
                super.getInventory()) {
            stringBuilder.append(item.getName() + "\t");
        }

        return stringBuilder.toString();
    }

}
