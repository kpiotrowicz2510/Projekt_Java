package com.projekt;

import java.awt.*;
import java.util.Random;

/**
 * Created by usr on 2016-05-11.
 */
public class Antylopa extends Zwierze{
    public Antylopa(Swiat swiat){
        this.swiat = swiat;
        this.inicjatywa = 4;
        this.sila = 4;
        this.SetColor(Color.orange);
    }
    public void akcja(){
        Random r1 = new Random();
        int x = r1.nextInt(2);
        int y = r1.nextInt(2);

        if(x==0){
            this.SetX(this.GetX()-2);
        }
        if(x==1){
            this.SetX(this.GetX()+2);
        }
        if(y==0){
            this.SetY(this.GetY()-2);
        }
        if(y==1){
            this.SetY(this.GetY()+2);
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
                    Random r1 = new Random();
                    if(r1.nextInt(2)==1) {
                        this.walka(org);
                    }else{
                        Dimension d = this.GetSwiat().freeSpaceP(this.GetX(),this.GetY());
                        this.SetX(d.width);
                        this.SetY(d.height);
                    }
                }
            }
        }
    }
}
