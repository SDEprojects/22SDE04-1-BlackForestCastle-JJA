package com.blackforestcastle;

import com.blackforestcastle.JSON_Objects.Building;
import com.blackforestcastle.JSON_Objects.Player;

import java.util.Scanner;

public class Commands_v2 {

    //private String previousCommand;
    public Commands_v2(){

    }

    //Parse input text and return as an array split into verb and noun
    private String[] input(Scanner theScanner) {
        String[] splitInput = null;
        try {

            System.out.print(">>");
            String input = theScanner.nextLine();
            //previousCommand = input;
            splitInput = input.trim().split(" ");// Read user input and split into an array based off of regex.

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return splitInput;
    }

    public void interact(Building theBuilding, Player thePlayer,Scanner theScanner){

        System.out.println("------------");

        System.out.println(thePlayer.getCurrentRoom().toString());
        System.out.println("What would you like to do?");

    }
}
