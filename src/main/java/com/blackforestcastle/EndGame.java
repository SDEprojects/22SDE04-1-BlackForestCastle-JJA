package com.blackforestcastle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EndGame {
    public static void saveScore(Player player){
        try
        {
            BufferedWriter SaveScore = new BufferedWriter(new FileWriter("src/main/resources/scores.txt", true));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String score = "PLAYER-NAME:" + player.getName() + " SCORE:" + player.getExperiencePoints() + "   "+ dtf.format(now) +"\n";
            SaveScore.append(score);
            SaveScore.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
