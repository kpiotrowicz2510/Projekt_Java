package com.projekt;

import java.awt.*;

/**
 * Created by usr on 2016-05-11.
 */
public class Guarana extends Roslina{
    public Guarana(Swiat swiat){
        this.swiat = swiat;
        this.inicjatywa = 0;
        this.sila = 0;
        this.SetColor(Color.cyan);
    }
    public void kolizja(){
        Organizm org = this.GetSwiat().podajOrganizm(this.GetX(), this.GetY());
        if(org!=null&&org.GetID()!=this.GetID()&&org.GetColor()!=this.GetColor()){
            org.SetSila(org.GetSila()+3);
            this.GetSwiat().deleteOrganizm(this.GetID());
        }
    }
}
