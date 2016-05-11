package com.projekt;

import java.awt.*;
import java.util.Random;

/**
 * Created by usr on 2016-05-11.
 */
public class Mlecz extends Roslina{
    public Mlecz(Swiat swiat){
        this.SetSwiat(swiat);
        this.SetColor(Color.yellow);
        this.sila = 0;
        this.inicjatywa = 0;
    }
    public void akcja() {
        for (int i = 0; i < 3; i++) {
            Random r1 = new Random();
            int x = r1.nextInt(30);
            if (x == 20) {
                Dimension a = this.GetSwiat().freeSpaceP(this.GetX(),this.GetY());
                Mlecz g = new Mlecz(this.GetSwiat());
                g.SetColor(this.GetColor());
                g.SetX(a.width);
                g.SetY(a.height);
                if (a.width > -1 && a.height > -1) {
                    this.GetSwiat().AddOrganizm(g, a.width, a.height);
                }
            }
        }
    }
}
