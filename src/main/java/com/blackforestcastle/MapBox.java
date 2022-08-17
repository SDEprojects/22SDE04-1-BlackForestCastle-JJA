package com.blackforestcastle;

import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class MapBox extends JPanel {

    private final JTextArea mapBox;

    MapBox() {
        mapBox = new JTextArea();
        setLayout(new BorderLayout());
        mapBox.setBackground(Color.ORANGE);
    }


    static String getText()
    {
        String result = "";
        try
        {
            result = IOUtils.toString(new InputStreamReader(Commands_v2.class.getResourceAsStream("/map.txt"), StandardCharsets.UTF_8));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
