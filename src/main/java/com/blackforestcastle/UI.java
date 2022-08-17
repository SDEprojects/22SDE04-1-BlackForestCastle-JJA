package com.blackforestcastle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class UI implements KeyListener {
    public static JFrame frame;
    public static JTabbedPane mapTab;
    public static Controller controller;
    public static TextBox textBox;
    public static JTextField textField;
    public static JTextArea textPanel1;
    public static JLabel imageLabel;
    public static  JTabbedPane imagePane;
    static boolean pressed_enter = false;

    public UI() {
        startUI();
    }

    public UI(TextBox textBox, JTextField textField, JTextArea textPanel1) {
        UI.textBox = textBox;
        UI.textField = textField;
        UI.textPanel1 = textPanel1;
    }

    private void startUI() {

        ImageIcon image = new ImageIcon("src/main/resources/img.png");

        frame.setTitle("Black Forest Castle");
        frame.setSize(1400, 1100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(new BorderLayout());

        textBox = new TextBox();
        imageLabel = new JLabel(image);
        imagePane = new JTabbedPane(JTabbedPane.TOP);

        textField = new JTextField();
        textField.setBackground(Color.LIGHT_GRAY);

        textField.addActionListener(arg0 -> pressed_enter = true);

        frame.add(textBox, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        imagePane.addTab("Black Forest Castle", imageLabel);
        frame.add(imagePane, BorderLayout.NORTH);
    }

    public static void textPrint(String text) {
        textBox.append(text + "\n");
    }


    public static void main(String[] args) {
        frame = new JFrame();
        mapTab = new JTabbedPane(JTabbedPane.TOP);

        final JTextArea textArea = new JTextArea();
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textArea.getFont().getSize() - 1));
        textArea.setEditable(false);
        textArea.setCaretPosition(0);
        UI ui = new UI();
        frame.setVisible(true);
        JLabel map = new JLabel(MapBox.getText());
        map.setFont(new Font(Font.MONOSPACED, Font.PLAIN, textArea.getFont().getSize() - 1));;
        mapTab.addTab("MAP", map);
        frame.add(mapTab, BorderLayout.EAST);
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