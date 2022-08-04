package com.blackforestcastle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

class EndGame {
    public static void saveScore(Player player){
        try
        {
            BufferedWriter SaveScore = new BufferedWriter(new FileWriter("src/main/resources/scores.txt", true));
            LocalDateTime now = LocalDateTime.now();
            String score = now + " PLAYER-NAME:" + player.getName() + " SCORE:" + player.getExperiencePoints() + "\n";
            SaveScore.append(score);
            SaveScore.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
