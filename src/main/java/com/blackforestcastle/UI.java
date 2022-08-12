package com.blackforestcastle;

import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class UI implements KeyListener {
    public static JFrame frame;
    public static JFrame mapFrame;
    public static Controller controller;
    public static TextBox textBox;
    public static JTextField textField;
    public static JTextArea textPanel1;
    public static MapBox mapBox;
    static boolean pressed_enter = false;

    public UI() {
        startUI();
    }

    public UI(TextBox textBox, JTextField textField, JTextArea textPanel1, MapBox mapBox) {
        UI.textBox = textBox;
        UI.textField = textField;
        UI.textPanel1 = textPanel1;
        UI.mapBox = mapBox;
    }

    private void startUI() {

        frame.setTitle("Black Forest Castle");
        frame.setSize(1400, 1100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(new BorderLayout());

        mapFrame.setTitle("Map");
        mapFrame.setSize(500, 500);
        mapFrame.setLocationRelativeTo(null);
        mapFrame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        mapFrame.getContentPane().setForeground(Color.cyan);
        mapFrame.setLayout(new BorderLayout());

        textBox = new TextBox();
        mapBox = new MapBox();


        textField = new JTextField();
        textField.setBackground(Color.LIGHT_GRAY);

        textField.addActionListener(arg0 -> pressed_enter = true);

        frame.add(textBox, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        mapFrame.add(mapBox, BorderLayout.EAST);
    }

    public static void textPrint(String text) {
        textBox.append(text + "\n");
    }

    public static void main(String[] args) {
        frame = new JFrame();
        mapFrame = new JFrame();

        UI ui = new UI();
        frame.setVisible(true);
        mapFrame.setVisible(true);
        controller = Controller.getInstance();
        controller.newGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

/*
Added override keyEvents to check if the enter was pressed in the game after you enter your command.
Create a boolean and set to false, to check if enter is pressed.
Created a seperate textbox for the input and added a actionListener.
 */