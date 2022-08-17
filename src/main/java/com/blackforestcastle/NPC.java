package com.blackforestcastle;

import java.util.HashMap;
import java.util.Random;

class NPC extends Character {

    private String name;
    private String desc;
    private boolean isFriendly;
    private HashMap<Integer, String> taunt = new HashMap<>();

    //keep empty json reader will overwrite
    public NPC(){
        initializeTauntMap();
    }
    public NPC(String theName){
        this();
        setName(theName);
    }
    public NPC(String theName, String theDesc){
        this(theName);
        setDesc(theDesc);
    }

    @Override
    public void attack(Character player) {
        int damageDone = getAttackPower() + randomNumber(10,1);
        player.setHP(player.getHP()-damageDone);
        UI.textPrint("The enemy did " + damageDone + " damage. Your health is now " + player.getHP());
        UI.mapTabGraphicPrint(String.valueOf(player.getHP()));
    }
    //adds a random item to the NPC inventory
    public void addRandomItemToInventory(){
        JSONReader jsonReader = new JSONReader();
        //retrieve all items possible from json file
        Item[] allItems = jsonReader.getItems();
        //add one random item pulled from the allItems list generated based on the size of the list
        super.getInventory().add(allItems[super.randomNumber(allItems.length,0)]);
    }

    public void printRandomTaunt(){
        Random random = new Random();
        Object[] values = getTauntMap().values().toArray();
        UI.textPrint((String) values[random.nextInt(values.length)]);
    }
    public static boolean isTaunting()
    {
        return new Random().nextBoolean();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    private void initializeTauntMap(){
        this.taunt.put(1, "Go home and be a family man!");
        this.taunt.put(2, "My attacks will tear you apart.");
        this.taunt.put(3, "Run, coward!");
        this.taunt.put(4, "Look at you, hacker. A pathetic creature of meat and bone, panting and sweating as you run through my corridors. How can you challenge a perfect, immortal machine?");
        this.taunt.put(5, "You weak, pathetic fool.”");
        this.taunt.put(6, "You spoony bard!");
        this.taunt.put(7, "I salute my fallen enemy!");
        this.taunt.put(8, "*laugh*");
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    private HashMap<Integer, String> getTauntMap(){
        return this.taunt;
    }
}
