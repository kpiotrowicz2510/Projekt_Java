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
    private Swiat swiat;
    private int sizeX;
    private int sizeY;
    public void addOrganizm(int id){
        Object[] possibilities = {"Antylopa", "Guarana", "Jagody", "Lis", "Mlecz","Owca","Trawa","Zolw"};
        String s1 = (String)JOptionPane.showInputDialog(
                this,
                "Podaj nazwe organizmu:",
                "Dodawanie",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "Wilk");
        int ytemp = id/this.sizeX;
        int xtemp = id - ytemp*sizeX;
        int x=xtemp;
        int y=ytemp;
        if(s1==null){return;}
        switch (s1){
            case "Antylopa" :
                Antylopa a1 = new Antylopa(this.swiat);
                this.swiat.AddOrganizm(a1,x,y);
                break;
            case "Guarana" :
                Guarana g1 = new Guarana(this.swiat);
                this.swiat.AddOrganizm(g1,x,y);
                break;
            case "Jagody" :
                Jagody j1 = new Jagody(this.swiat);
                this.swiat.AddOrganizm(j1,x,y);
                break;
            case "Lis" :
                Lis l1 = new Lis(this.swiat);
                this.swiat.AddOrganizm(l1,x,y);
                break;
            case "Mlecz" :
                Mlecz m1 = new Mlecz(this.swiat);
                this.swiat.AddOrganizm(m1,x,y);
                break;
            case "Owca" :
                Owca o1 = new Owca(this.swiat);
                this.swiat.AddOrganizm(o1,x,y);
                break;
            case "Trawa" :
                Trawa t1 = new Trawa(this.swiat);
                this.swiat.AddOrganizm(t1,x,y);
                break;
            case "Zolw" :
                Zolw z1 = new Zolw(this.swiat);
                this.swiat.AddOrganizm(z1,x,y);
                break;
        }
        this.swiat.RysujSwiat();
    }
    public Window(Swiat swiat) {
        super("Krzysztof Piotrowicz 160873");
        this.swiat = swiat;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        String s1 = (String)JOptionPane.showInputDialog(
                this,
                "Podaj wymiar X:",
                "Wymiary",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "30");
        String s2 = (String)JOptionPane.showInputDialog(
                this,
                "Podaj wymiar Y:",
                "Wymiary",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "30");
        this.sizeX = Integer.parseInt(s1);
        this.sizeY = Integer.parseInt(s2);
        this.swiat.SetR(new Dimension(sizeX,sizeY));
        JPanel mainPanelX = new JPanel();
        mainPanelX.setLayout(new BorderLayout());
        mainPanelX.setMinimumSize(new Dimension(300,500));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //int sizeX = 30;
        //int sizeY = 30;
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
                //j.setBorderPainted(false);
                j.setPreferredSize(new Dimension(10, 10));
                j.setMaximumSize(new Dimension(10, 10));
                j.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e){
                        //JOptionPane.showMessageDialog(null,((JButton)e.getSource()).getName().+"");
                        Window.this.addOrganizm(Integer.parseInt(((JButton)e.getSource()).getName()));
                    }
                });
                jp.add(j);

            }
            mainPanel.add(jp);
        }
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1, 0));
        jp2.setMaximumSize(new Dimension(500, 100));
        JButton j = new JButton();
        j.setText("Nowa tura");
        j.setPreferredSize(new Dimension(2,20));
        j.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                swiat.UpdateLoop(-1);
            }
        });
        jp2.add(j);
        j = new JButton();
        j.setText("Wczytaj");
        j.setPreferredSize(new Dimension(2,20));
        j.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){

            }
        });
        jp2.add(j);
        j = new JButton();
        j.setText("Zapisz");
        j.setPreferredSize(new Dimension(2,20));
        j.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){

            }
        });
        jp2.add(j);
        j = new JButton();
        j.setText("Zakoncz");
        j.setPreferredSize(new Dimension(2,20));
        j.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        jp2.add(j);
        /*for (int i=0;i<1;i++) {
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

        }*/
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
        //mainPanel.add(jp);
        mainPanelX.add(mainPanel,BorderLayout.CENTER);
        mainPanelX.add(jp2,BorderLayout.NORTH);
        mainPanelX.add(pane,BorderLayout.EAST);
        setContentPane(mainPanelX);
       // pack();
        setVisible(true);
    }
}