package com.blackforestcastle.JSON_Objects;

import java.util.ArrayList;
import java.util.List;

public class Building {
    //hold the linear representation of connected rooms
    private int[][] roomsAdjacencyMatrix;
    private List<Room_v2> rooms;
    private Room_v2 currentRoom;


    public Building() {
        setRooms(new ArrayList<>());
    }

    public Building(int theAmountOfRooms) {
        this();
        setRoomsAdjacencyMatrix(new int[theAmountOfRooms][theAmountOfRooms]);
    }

    //method to set room adjacency

    //method to build rooms out (maybe have this done by the controller and not here just feed the list in)

    public int[][] getRoomsAdjacencyMatrix() {
        return roomsAdjacencyMatrix;
    }

    public void setRoomsAdjacencyMatrix(int[][] roomsAdjacencyMatrix) {
        this.roomsAdjacencyMatrix = roomsAdjacencyMatrix;
    }

    public List<Room_v2> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room_v2> rooms) {
        this.rooms = rooms;
    }
}
