package com.blackforestcastle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class UI implements KeyListener {
    public static JFrame frame;
    public static Controller controller;
    public static TextBox textBox;
    public static JTextField textField;
    static boolean pressed_enter = false;

    public UI() {
        startUI();
    }

    public UI(TextBox textBox, JTextField textField) {
        this.textBox = textBox;
        this.textField = textField;
    }

    private void startUI() {

        frame.setTitle("Black Forest Castle");
        frame.setSize(768, 800);
        frame.setBackground(Color.GRAY);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        textBox = new TextBox();
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pressed_enter = true;
            }
        });

        frame.add(textBox, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
    }

    public static void textPrint(String text) {
        textBox.append(text+"\n");
    }

    public static void graphicPrint(){
        ;
    }

    public static void main(String[] args) {
        frame = new JFrame();

        UI ui = new UI();
        frame.setVisible(true);
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