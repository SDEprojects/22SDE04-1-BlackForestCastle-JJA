package com.blackforestcastle.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;

public class TextBox extends JPanel {

    private final JTextArea textField;

    public TextBox() {
        textField = new JTextArea();
        setLayout(new BorderLayout());
        add(new JScrollPane(textField), BorderLayout.CENTER);
    }

    public void append(String text) {
        textField.append(text);
    }

    public String getText() {
        return textField.getText();
    }
}
/*
Created a text-field to place the text for the game in.
 */