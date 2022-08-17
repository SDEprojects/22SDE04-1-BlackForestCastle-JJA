package com.blackforestcastle;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

class ResourcesTest
{
    @Test
    void gameInfoResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/gameInfo.json");
        assertTrue(file.exists());
    }
    @Test
    void imgResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/img.png");
        assertTrue(file.exists());
    }
    @Test
    void itemsResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/items.json");
        assertTrue(file.exists());
    }
    @Test
    void mapResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/map.txt");
        assertTrue(file.exists());
    }
    @Test
    void npcResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/npc.json");
        assertTrue(file.exists());
    }
    @Test
    void roomsResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/rooms.json");
        assertTrue(file.exists());
    }
    @Test
    void scoresResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/scores.txt");
        assertTrue(file.exists());
    }
    @Test
    void titleResourceFilesBeingRead()
    {
        File file = new File("src/main/resources/title.txt");
        assertTrue(file.exists());
    }
    @Test
    void musicFilesBeingRead()
    {
        File file = new File("cold-cinematic-landscape-116954.wav");
        assertTrue(file.exists());
    }
}