package com.blackforestcastle.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class StartButton extends JButton {
    private final JButton startButton;

    public StartButton() {
        startButton = new JButton();
        setLayout(new BorderLayout());
        setBackground(Color.red);
        add(startButton, BorderLayout.CENTER);

    }

    public void addButtonToJframe(JButton button) {
        startButton.add(button);
    }
/*
 Custom button component for the start button
 */
}
