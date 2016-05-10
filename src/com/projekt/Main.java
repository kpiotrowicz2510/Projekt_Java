package com.projekt;

import java.awt.EventQueue;


public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window a = new Window();
                Swiat s = new Swiat(a);
                Wilk w = new Wilk(s);
                Wilk w2 = new Wilk(s);
                s.AddOrganizm(w);
                s.AddOrganizm(w2);
               // s.AddOrganizm(w);
                s.RysujSwiat();
            }
        });
    }
}
