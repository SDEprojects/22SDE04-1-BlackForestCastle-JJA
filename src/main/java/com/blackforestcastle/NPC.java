package com.blackforestcastle;

import java.util.HashMap;
import java.util.Random;

class NPC extends Character {

    String name;
    String desc;
    boolean isFriendly;
    private HashMap<Integer, String> taunt = new HashMap<>();

    public NPC()
    {
        initializeTauntMap();
    }

    @Override
    public void attack(Character player) {
        int damageDone = getAttackPower() + randomNumber(10);
        player.setHP(player.getHP()-damageDone);
        System.out.println("The enemy did " + damageDone + " damage. Your health is now " + player.getHP());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc(){
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
    private HashMap<Integer, String> getTauntMap(){
        return this.taunt;
    }
    public void printRandomTaunt(){
        Object taunt = new Random().nextInt(getTauntMap().values().toArray().length);
        System.out.println(taunt);
    }
    public static boolean isTaunting()
    {
        return new Random().nextBoolean();
    }



}
