package com.blackforestcastle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.blackforestcastle.Commands;

public class Room {

    private String name,desc;
    private String east,west,south,north;
    private List<Item> itemObjects;
    private List<NPC> npcObjects;
    private String[] NPC;
    private String[] items;

    public Room() {
        setItemObjects(new ArrayList<Item>());
        setNpcObjects(new ArrayList<NPC>());
    }


    public void makeNPCInstances() {
        JSONReader jsonReader = new JSONReader();
        NPC[] npcJSON = jsonReader.getNPC();
        Arrays.asList(npcJSON);
        for (String npc : NPC) {
            for (NPC npc0 : npcJSON) {
                if (npc.equals(npc0.getName())) {
                    npcObjects.add(npc0);
                }
            }
        }
    }


    public void makeItemInstances() {
        JSONReader jsonReader = new JSONReader();
        Item[] itemsJSON = jsonReader.getItems();
        for (String item : items) {
            for (Item itemO : itemsJSON) {
                if (item.equals(itemO.getName())) {
                    itemObjects.add(itemO);
                }
            }
        }
    }
    public String roomInfo(Character player) {
        String npcStringDesc = "";
        if (!npcObjects.isEmpty()){
            for (NPC npc : npcObjects){
                npcStringDesc = "\n" + npc.getDesc();
            }
        }
        return "Current Location: " + getName() + "\n" + "Player Health: " + player.getHP() + "\n" + "Description: " + getDesc() + "\n" + "Items: " + getItems() + "\n" + getValidDirections()
                + "\n" + "NPCs: " + getNPCs()  + npcStringDesc;

    }
    public Item checkRoomForItem(String item) {
        for (Item itemObject : itemObjects) {
            if (item.equals(itemObject.getName())) {
                return itemObject;
            }
        }
        return null;
    }


    public String getNPCs() {
        String npcValue = "";
        for (NPC npc : npcObjects) {
            npcValue += npc.getName() + ", ";
        }
        return npcValue;
    }

    public String getItems() {
        String stuff = "";
        for (Item item : itemObjects) {
            stuff += item.getName() + ", ";
        }
        return stuff;
    }

    public String getDirection(String dir) {
        switch (dir) {
            case "north":
                return north;
            case "south":
                return south;
            case "east":
                return east;
            case "west":
                return west;
            default:
                return "Not a valid direction";
        }
    }



    public String getValidDirections() {
        String direction1 = "";
        String direction2 = "";
        String direction3 = "";
        String direction4 = "";

        if (getDirection("north").length() > 0) {
            direction1 = "North: " + getDirection("north") + " ";
        }
        if (getDirection("south").length() > 0) {
            direction2 = "South: " + getDirection("south") + " ";
        }
        if (getDirection("west").length() > 0) {
            direction3 = "West: " + getDirection("west") + " ";
        }
        if (getDirection("east").length() > 0) {
            direction4 = "East: " + getDirection("east") + " ";
        }

        return "Directions available-- " + direction1 + direction2 + direction3 + direction4;

    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public List<Item> getItemObjects() {
        return itemObjects;
    }

    public void setItemObjects(List<Item> itemObjects) {
        this.itemObjects = itemObjects;
    }

    public List<com.blackforestcastle.NPC> getNpcObjects() {
        return npcObjects;
    }

    public void setNpcObjects(List<com.blackforestcastle.NPC> npcObjects) {
        this.npcObjects = npcObjects;
    }

    public String[] getNPC() {
        return NPC;
    }

    public void setNPC(String[] NPC) {
        this.NPC = NPC;
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

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
