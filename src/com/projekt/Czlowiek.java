package com.projekt;

import java.awt.*;

/**
 * Created by usr on 2016-05-11.
 */
public class Czlowiek extends Zwierze{
    private int key=0;
    private int tura_aktywacji = 0;
    private int cooldown = 0;
    private boolean special = false;
    public Czlowiek(Swiat swiat){
        this.swiat = swiat;
        this.inicjatywa = 4;
        this.sila = 5;
        this.SetColor(Color.red);
    }
    public void akcja(){

    }
    public void kolizja(){

    }
    public boolean isSpecial(){
        return this.special;
    }
}
