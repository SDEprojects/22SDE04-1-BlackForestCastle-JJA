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

    public static String displayMap(String text) throws IOException {
        String results = IOUtils.toString(new InputStreamReader(Objects.requireNonNull(Commands.class.getResourceAsStream("/map.txt")), StandardCharsets.UTF_8));
        if(text.equalsIgnoreCase("Map")) {
            UI.textPrint(results);
        }
        return results;
    }
}
