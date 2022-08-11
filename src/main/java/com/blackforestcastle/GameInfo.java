package com.blackforestcastle;

import java.io.*;

public class GameInfo
{
    String info;

    // Constructors
    public GameInfo(String info)
    {
        this.info = info;
    }

    // Setters/Getters
    public String getInfo()
    {
        return info;
    }

    public static void viewScores(){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/scores.txt")));

            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}