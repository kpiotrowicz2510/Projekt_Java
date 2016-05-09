package com.projekt;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {

    public Window() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        JPanel mainPanelX = new JPanel();
        mainPanelX.setLayout(new BoxLayout(mainPanelX, BoxLayout.X_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for(int aj=0;aj<40;aj++) {
            JPanel jp = new JPanel();
            BoxLayout bl = new BoxLayout(jp, BoxLayout.X_AXIS);
            jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
            jp.setMaximumSize(new Dimension(500, 10));
            for (int i = 0; i < 50; i++) {
                JButton j = new JButton();
                j.setText("");
                j.setBackground(Color.green);
                j.setBorderPainted(false);
                j.setPreferredSize(new Dimension(10, 10));
                j.setMaximumSize(new Dimension(10, 10));

                jp.add(j);

            }
            mainPanel.add(jp);
        }
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(0, 1));
        jp2.setMaximumSize(new Dimension(200, 500));
        for (int i=0;i<10;i++) {
            JButton j = new JButton();
            j.setText("Przycisk");
            j.setPreferredSize(new Dimension(2,20));

            jp2.add(j);

        }
        //mainPanel.add(jp);
        mainPanelX.add(mainPanel);
        mainPanelX.add(jp2);
        setContentPane(mainPanelX);
        setVisible(true);
    }
}