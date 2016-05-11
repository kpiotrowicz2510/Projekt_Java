package com.projekt;

import java.awt.*;
import java.util.Random;

/**
 * Created by usr on 2016-05-11.
 */
public class Roslina extends Organizm{
    public void akcja(){
        Random r1 = new Random();
        int x = r1.nextInt(100);
        if(x==20){
            Dimension a = this.GetSwiat().freeSpaceP(this.GetX(),this.GetY());
            Roslina g = new Roslina();
            g.SetColor(this.GetColor());
            g.SetX(a.width);
            g.SetY(a.height);
            if (a.width > -1 && a.height > -1) {
                this.GetSwiat().AddOrganizm(g, a.width, a.height);
            }
        }
    }
    public void kolizja(){
        Organizm org = this.GetSwiat().podajOrganizm(this.GetX(), this.GetY());
        if (this.GetID() != org.GetID()&&this.GetColor()!=org.GetColor()) {
            this.GetSwiat().deleteOrganizm(this.GetID());
            String n = "Organizm ("+org.getClass().getSimpleName() + ") zjada ("+this.getClass().getSimpleName()+")";
            this.GetSwiat().info.add(n);
        }

    }
}
