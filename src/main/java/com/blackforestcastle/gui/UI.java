package com.blackforestcastle.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

class UI extends JFrame {
    private TextBox textBox;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    Process p = null;

    public UI() {
        startUI();
    }

    public UI(TextBox textBox, JButton button1, JButton button2, JButton button3) {
        this.textBox = textBox;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
    }

    private void startUI() {

        setTitle("Black Forest Castle");
        setSize(768, 800);
        setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.BLUE);

        setLayout(new BorderLayout());

        button1 = new JButton("Start Game");
        button2 = new JButton("End Game");
        button3 = new JButton("Settings");

        textBox = new TextBox();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textBox.add(button1);
                try {
                    p = Runtime.getRuntime().exec(
                            "java -jar team_2_black_forest_castle.jar"
                    );
                    try(BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                        String line;

                        while ((line = input.readLine()) != null) {
                            textBox.append(line + System.lineSeparator());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textBox.add(button2);

                TextBox textBox2 = new TextBox();

                OutputStream stdin = p.getOutputStream();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

                try {
                    writer.write(textBox2.getText());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                textBox.add(button3);
                textBox.append("Test3");
            }
        });

        add(textBox, BorderLayout.CENTER);
        add(button1, BorderLayout.SOUTH);
//        add(button2, BorderLayout.SOUTH);
//        add(button3, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new UI();
            ex.setVisible(true);
        });
    }
}

/*
Main UI class
 */