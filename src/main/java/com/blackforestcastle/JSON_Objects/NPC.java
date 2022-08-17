package com.blackforestcastle.JSON_Objects;

import com.blackforestcastle.utility.JSONReader;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NPC extends Character {
    private String desc;
    //private boolean isFriendly;
    private HashMap<Integer, String> taunt;

    //keep empty json reader will overwrite
    public NPC() {
        setTaunt(new HashMap<>());
        initializeTauntMap();

    }

    public NPC(String theName) {
        this();
        setName(theName);
    }

    public NPC(String theName, String theDesc) {
        this(theName);
        setDesc(theDesc);
    }

    @Override
    public void attack(Character player) {
        int damageDone = getAttackPower() + randomNumber(10,1);
        player.setHP(player.getHP()-damageDone);
        System.out.println("The enemy did " + damageDone + " damage. Your health is now " + player.getHP());
        UI.textPrint("The enemy did " + damageDone + " damage. Your health is now " + player.getHP());
        UI.mapTabGraphicPrint(String.valueOf(player.getHP()));

    }

    //adds a random item to the NPC inventory
    public void addRandomItemToInventory(List<Item> allThePossibleItems) {
        //add one random item pulled from the allItems list generated based on the size of the list
        super.getInventory().add(allThePossibleItems.get(super.randomNumber(allThePossibleItems.size(), 0)));
    }

    public void printRandomTaunt() {
        Random random = new Random();
        Object[] values = getTauntMap().values().toArray();
        System.out.println(values[random.nextInt(values.length)]);
        UI.textPrint((String) values[random.nextInt(values.length)]);
    }
    //hard coded default
    private void initializeTauntMap() {
        this.taunt.put(1, "Go home and be a family man!");
        this.taunt.put(2, "My attacks will tear you apart.");
        this.taunt.put(3, "Run, coward!");
        this.taunt.put(4, "Look at you, hacker. A pathetic creature of meat and bone, panting and sweating as you run through my corridors. How can you challenge a perfect, immortal machine?");
        this.taunt.put(5, "You weak, pathetic fool.”");
        this.taunt.put(6, "You spoony bard!");
        this.taunt.put(7, "I salute my fallen enemy!");
        this.taunt.put(8, "*laugh*");
    }

    public static boolean isTaunting() {
        return new Random().nextBoolean();
    }

    private HashMap<Integer, String> getTauntMap() {
        return this.taunt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public HashMap<Integer, String> getTaunt() {
        return taunt;
    }

    public void setTaunt(HashMap<Integer, String> taunt) {
        this.taunt = taunt;
    }
}
