package com.projekt;

import java.awt.*;
import java.util.Random;

/**
 * Created by usr on 2016-05-11.
 */
public class Zolw extends Zwierze{
    public Zolw(Swiat swiat){
        this.swiat = swiat;
        this.inicjatywa = 5;
        this.sila = 9;
        this.SetColor(Color.white);
    }
    public void akcja(){
        Random r1 = new Random();
        int x = r1.nextInt(8);
        if (x == 6) {
            this.SetX(this.GetX() - 1);
        }
        if (x == 7) {
            this.SetX(this.GetX() + 1);
        }
        int y = r1.nextInt(8);
        if (y == 6) {
            this.SetY(this.GetY() - 1);
        }
        if (y == 7) {
            this.SetY(this.GetY() + 1);
        }
    }
    public void kolizja(){
        Organizm org = this.GetSwiat().podajOrganizm(this.GetX(), this.GetY());

        if (this.GetSwiat().freeSpace(this.GetX(), this.GetY()) == false) {
            if (this.GetID() != org.GetID()) {
                if (org.GetColor() == this.GetColor()) {
                    try {
                        this.rozmnazanie();
                    } catch (NoSpaceException e) {
                        this.GetSwiat().info.add("Brak miejsca do rozmnażania!");
                    }
                }
                else {
                    if(org.GetSila()<5) {
                        org.SetX(org.GetLastX());
                        org.SetY(org.GetLastY());
                    }else{
                        this.walka(org);
                    }
                }
            }
        }
    }
}
