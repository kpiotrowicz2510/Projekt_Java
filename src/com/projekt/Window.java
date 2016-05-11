package com.projekt;

import javafx.scene.layout.Border;
import jdk.nashorn.internal.runtime.Debug;

import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.ListView;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Window extends JFrame {
    Swiat swiat;
    public Window(Swiat swiat) {
        super("Hello World");
        this.swiat = swiat;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        JPanel mainPanelX = new JPanel();
        mainPanelX.setLayout(new BoxLayout(mainPanelX, BoxLayout.X_AXIS));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        int sizeX = 30;
        int sizeY = 30;
        for(int aj=0;aj<sizeY;aj++) {
            JPanel jp = new JPanel();
            BoxLayout bl = new BoxLayout(jp, BoxLayout.X_AXIS);
            jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
            jp.setMaximumSize(new Dimension(sizeX*10, 10));
            for (int i = 0; i < sizeX; i++) {
                JButton j = new JButton();
                Random r = new Random(9);
                //j.setText("");

                j.setBackground(Color.green);
                int _id = aj*sizeX+i;
                j.setName(_id+"");
                //j.setText(""+_id);
                j.setBorderPainted(false);
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
            j.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    swiat.UpdateLoop(-1);
                }
            });
            jp2.add(j);

        }
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {

                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        //System.out.println("Got key event! - "+e.getKeyCode());
                        if(e.getID() == KeyEvent.KEY_PRESSED) {
                            swiat.UpdateLoop(e.getKeyCode());

                        }

                        return false;
                    }
                });
        JEditorPane pane = new JEditorPane();
        pane.setMaximumSize(new Dimension(sizeX*10,200));
        pane.setContentType("text/html");
        pane.setText("<ol id='foo'><li>One</li><li>Two</li></ol>");
        HTMLDocument doc = (HTMLDocument) pane.getDocument();
       // add(pane);

        //Get the ref of foo element
        Element ele=doc.getElement("foo");
        ListView view=new ListView(ele);
        System.out.println(ele.getElementCount());
        try{
            doc.insertBeforeEnd(ele.getElement(0), "<ul><li>Test");
        }catch(Exception ex){}
        mainPanel.add(pane);
        //mainPanel.add(jp);
        mainPanelX.add(mainPanel);
        mainPanelX.add(jp2);
        setContentPane(mainPanelX);
        setVisible(true);
    }
}