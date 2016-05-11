package com.projekt;

import jdk.nashorn.internal.objects.annotations.Constructor;

import java.awt.EventQueue;


public class Main {
    public static void main(String[] args) {
                Swiat s = new Swiat();
                Window a = new Window(s);
                s.SetWindow(a);
                Wilk w = new Wilk(s);
                Wilk w2 = new Wilk(s);
                Lis l1 = new Lis(s);
                Mlecz m1 = new Mlecz(s);
                s.AddOrganizm(m1);
                s.AddOrganizm(l1);
                s.AddOrganizm(w);
                s.AddOrganizm(w2);
               // s.AddOrganizm(w);
                s.RysujSwiat();

    }
}
