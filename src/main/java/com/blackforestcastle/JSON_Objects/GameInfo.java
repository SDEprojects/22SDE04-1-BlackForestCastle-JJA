package com.blackforestcastle.JSON_Objects;

import java.io.*;

public class GameInfo
{
    private String info;

    // Constructors
    public GameInfo(String info)
    {
        setInfo(info);
    }

    // Setters/Getters


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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