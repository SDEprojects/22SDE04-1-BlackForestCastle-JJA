package com.blackforestcastle;

import com.google.gson.Gson;

import java.io.*;

public class JSONReader {
    //one method to parse and return the corresponding json object


    // this method parses the rooms.json folder and returns a Rooms Array object
    public Room[] getRooms() {
        Room[] rooms = {};
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(Room.class.getResourceAsStream("/rooms.json"));
        rooms = gson.fromJson(reader, Room[].class);
        for (Room room : rooms) {
            room.makeItemInstances();
            room.makeNPCInstances();
        }
        return rooms;
    }

    // This method parses the gameInfo.json file and returns a GameInfo Object that contains the required information.
    public GameInfo[] getGameInfo() {
        GameInfo[] info = {};
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(GameInfo.class.getResourceAsStream("/gameInfo.json"));
        info = gson.fromJson(reader, GameInfo[].class);
        return info;
    }

    public Item[] getItems() {
        Item[] items = {};
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(Item.class.getResourceAsStream("/items.json"));
        items = gson.fromJson(reader, Item[].class);
        return items;
    }

    public NPC[] getNPC() {
        NPC[] npcs = {};
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(NPC.class.getResourceAsStream("/npc.json"));
        npcs = gson.fromJson(reader, NPC[].class);
        return npcs;
    }
}