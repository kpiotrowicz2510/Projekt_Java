package com.projekt;

import java.awt.*;

/**
 * Created by usr on 2016-05-11.
 */
public class Jagody extends Roslina{
    public Jagody(Swiat swiat){
        this.swiat = swiat;
        this.inicjatywa = 0;
        this.sila = 99;
        this.SetColor(Color.MAGENTA);
    }
    public void kolizja(){
        Organizm org = this.GetSwiat().podajOrganizm(this.GetX(), this.GetY());
        if(org.GetID()!=this.GetID()){
            this.GetSwiat().deleteOrganizm(org.GetID());
            this.GetSwiat().deleteOrganizm(this.GetID());
            String n = "Organizm ("+this.getClass().getSimpleName() + ") zabija ("+org.getClass().getSimpleName()+")";
            this.GetSwiat().info.add(n);
        }
    }
}
