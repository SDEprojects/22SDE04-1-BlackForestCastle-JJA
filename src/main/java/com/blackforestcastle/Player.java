package com.blackforestcastle;

import java.util.ArrayList;
import java.util.List;

class Player extends Character {

    private Room currentRoom;

    public Player() {

    }
    public Player(Room theRoom){
        setCurrentRoom(theRoom);
    }

    public Player(Room theCurrentRoom, int theHp) {
        this(theCurrentRoom);
        super.setHP(theHp);

    }

    @Override
    public void attack(Character npc) {
        getPlayerAttackPower();
        int damageDone = getAttackPower() + randomNumber(10,0);
        npc.setHP(npc.getHP() - damageDone);
        System.out.println("You did " + damageDone + " damage. The enemies health now is " + npc.getHP());
    }

    // helper for attack method
    public void getPlayerAttackPower() {
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
            System.out.print(item.getName() + ", ");
        }
        System.out.println();
    }

    public Item checkInventoryForItem(String item) {
        for (Item itemObject : super.getInventory()) {
            if (item.equals(itemObject.getName())) {
                return itemObject;
            }
        }
        return null;
    }

    public List<Item> getInventory() {
        return super.getInventory();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
