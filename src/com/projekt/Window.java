package com.projekt;

import javafx.scene.layout.Border;
import jdk.nashorn.internal.runtime.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;


public class Window extends JFrame {

    public Window() {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        JPanel mainPanelX = new JPanel();
        mainPanelX.setLayout(new BoxLayout(mainPanelX, BoxLayout.X_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        int sizeX = 10;
        int sizeY = 10;
        for(int aj=0;aj<sizeY;aj++) {
            JPanel jp = new JPanel();
            BoxLayout bl = new BoxLayout(jp, BoxLayout.X_AXIS);
            jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
            jp.setMaximumSize(new Dimension(sizeX*10, 10));
            for (int i = 0; i < sizeX; i++) {
                JButton j = new JButton();
                Random r = new Random(9);
                j.setText("");
                //j.setText(""+r.nextInt());
                j.setBackground(Color.green);
                int _id = aj*sizeX+i;
                j.setName(_id+"");
               // j.setBorderPainted(false);
                j.setPreferredSize(new Dimension(10, 10));
                j.setMaximumSize(new Dimension(10, 10));
                j.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(null,((JButton)e.getSource()).getName()+"");
                    }
                });
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