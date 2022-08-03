package com.blackforestcastle;

import java.util.ArrayList;
import java.util.List;

class Player extends Character {

    Room currentRoom;
    int experiencePoints = 0;

    public Player() {

    }

    public Player(Room currentRoom, int HP) {
        this.currentRoom = currentRoom;
        super.setHP(HP);
    }

    @Override
    public void attack(Character npc) {
        getPlayerAttackPower();
        int damageDone = getAttackPower() + randomNumber(10);
        npc.setHP(npc.getHP() - damageDone);
        System.out.println("You did " + damageDone + " damage. The enemies health now is " + npc.getHP());
    }

    // helper for attack method
    public void getPlayerAttackPower() {
        // knife, sword, bow, arrow
        for (Item item : inventory) {
            if (item.getName().equals("bow")) {
                for (Item item0 : inventory) {
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
        for (Item item : inventory) {
            System.out.print(item.getName() + ", ");
        }
        System.out.println();
    }

    public Item checkInventoryForItem(String item) {
        for (Item itemObject : inventory) {
            if (item.equals(itemObject.getName())) {
                return itemObject;
            }
        }
        return null;
    }

    public List<Item> getInventory() {
        return inventory;
    }
    public int getExperiencePoints()
    {
        return experiencePoints;
    }

    public void addExperiencePoints(int experiencePoints)
    {
        this.experiencePoints += experiencePoints;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
