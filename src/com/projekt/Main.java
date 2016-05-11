package com.projekt;
import java.lang.reflect.Constructor;

import java.awt.EventQueue;


public class Main {
    public static void main(String[] args) {
                Swiat s = new Swiat();
                Window a = new Window(s);
                s.SetWindow(a);
                spawnObjects(s);
                s.RysujSwiat();

    }

    private static void spawnObjects(Swiat s) {
        Czlowiek c = new Czlowiek(s);
        Wilk w = new Wilk(s);
        Wilk w2 = new Wilk(s);
        Lis l1 = new Lis(s);
        Lis l2 = new Lis(s);
        Mlecz m1 = new Mlecz(s);
        Mlecz m2 = new Mlecz(s);
        Antylopa a1 = new Antylopa(s);
        Antylopa a2 = new Antylopa(s);
        Jagody j1 = new Jagody(s);
        Jagody j2 = new Jagody(s);
        Owca o1 = new Owca(s);
        Owca o2 = new Owca(s);
        Trawa t1 = new Trawa(s);
        Trawa t2 = new Trawa(s);
        Zolw z1 = new Zolw(s);
        Zolw z2 = new Zolw(s);

        s.AddOrganizm(c);
        s.AddOrganizm(w);
        s.AddOrganizm(w2);
        s.AddOrganizm(l1);
        s.AddOrganizm(l2);
        s.AddOrganizm(m1);
        s.AddOrganizm(m2);
        s.AddOrganizm(a1);
        s.AddOrganizm(a2);
        s.AddOrganizm(j1);
        s.AddOrganizm(j2);
        s.AddOrganizm(o1);
        s.AddOrganizm(o2);
        s.AddOrganizm(t1);
        s.AddOrganizm(t2);
        s.AddOrganizm(z1);
        s.AddOrganizm(z2);
    }
}
