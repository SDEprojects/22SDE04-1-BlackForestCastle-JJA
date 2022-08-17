package com.blackforestcastle.JSON_Objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Building {
    //hold the linear representation of connected rooms
    // 0 = no room / 1 = north / 2 = south / 3 = west / 4 = east MAYBE
    //private int[][] roomsAdjacencyMatrix;
    private Room_v2 currentRoom;
    private Room_v2[][] rooms;
    private Point currentRoomCoordinates;


    public Building(Room_v2[][] theMapofRooms, Room_v2 theStartingRoom,Point currentRoomCoordinates) {
        setRooms(theMapofRooms);
        setCurrentRoom(theStartingRoom);
        setCurrentRoomCoordinates(currentRoomCoordinates);
    }
    //method to set room adjacency

    //method to build rooms out (maybe have this done by the controller and not here just feed the list in)

//    public int[][] getRoomsAdjacencyMatrix() {
//        return roomsAdjacencyMatrix;
//    }
//
//    public void setRoomsAdjacencyMatrix(int[][] roomsAdjacencyMatrix) {
//        this.roomsAdjacencyMatrix = roomsAdjacencyMatrix;
//    }

    public Room_v2[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room_v2[][] rooms) {
        this.rooms = rooms;
    }

    public Room_v2 getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room_v2 currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Point getCurrentRoomCoordinates() {
        return currentRoomCoordinates;
    }

    public void setCurrentRoomCoordinates(Point currentRoomCoordinates) {
        this.currentRoomCoordinates = currentRoomCoordinates;
    }
}
