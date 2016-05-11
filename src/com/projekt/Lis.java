package com.projekt;

import java.awt.*;
import java.util.Random;

/**
 * Created by usr on 2016-05-10.
 */
public class Lis extends Zwierze {
    public Lis(Swiat swiat){
        this.swiat =swiat;
        this.inicjatywa = 7;
        this.sila = 3;
        this.SetColor(Color.BLUE);
    }
    public void akcja(){
        Random r1 = new Random();
        int mx = 0;
        int my = 0;
        int x = r1.nextInt(2);
        if (x == 0) {
            mx = this.GetX() - 1;
        }
        if (x == 1) {
            mx = this.GetX() + 1;
        }
        int y = r1.nextInt(2);
        if (y == 0) {
            my = this.GetY() - 1;
        }
        if (y == 1) {
            my = this.GetY() + 1;
        }
        Organizm org = this.GetSwiat().podajOrganizm(mx, my);
        if (org == null) {
            this.SetX(mx);
            this.SetY(my);
        }
        else {
            if (org.GetSila() > this.GetSila()) {
                this.akcja();
            }
            else {
                this.SetX(mx);
                this.SetY(my);
            }
        }
    }
}
