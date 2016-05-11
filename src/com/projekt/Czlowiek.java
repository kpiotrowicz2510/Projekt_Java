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
        int key = this.GetSwiat().GetPressedKey();
        if(key==4&&this.special==false&&cooldown==0){
            this.GetSwiat().info.add("Tarcza Alzura aktywowana!");
            this.special=true;
            this.tura_aktywacji = this.GetSwiat().GetTura();
        }
        if (this.GetSwiat().GetTura() - this.tura_aktywacji > 5 && this.special == true) {
            this.special = false;
            this.cooldown = 5;
            this.GetSwiat().info.add("Tarcza Alzura dezaktywowana!");
        }
        if (this.cooldown > 0) {
            this.cooldown--;
        }
        int x = this.GetX();
        int y = this.GetY();
        int gx = this.GetX();
        int gy = this.GetY();

        if (x - 1 >= 0) {
            if (key == 2) {
                this.SetX(gx - 1);
                return;
            }
        }
        if (x + 1 <= this.GetSwiat().GetRX()) {
            if (key == 3) {
                this.SetX(gx + 1);
                return;
            }
        }
        if (y - 1 >= 0) {
            if (key == 0) {
                this.SetY(gy - 1);
                return;
            }
        }
        if (y + 1 <= this.GetSwiat().GetRY()) {
            if (key == 1) {
                this.SetY(gy + 1);
                return;
            }
        }
    }

    public boolean isSpecial(){
        return this.special;
    }
}
