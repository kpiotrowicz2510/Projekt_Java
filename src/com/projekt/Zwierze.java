package com.projekt;

import java.util.Random;

/**
 * Created by usr on 2016-05-10.
 */
public class Zwierze extends Organizm {

    public void kolizja(){
        Organizm org = this.GetSwiat().podajOrganizm(this.GetX(), this.GetY());

        if (this.GetSwiat().freeSpace(this.GetX(), this.GetY()) == false) {
            if (this.GetID() != org.GetID()) {
                if (org.GetColor() == this.GetColor()) {
                    this.rozmnazanie();
                }
                else {
                    this.walka(org);
                }
            }
        }
    }
    public void akcja(){
        Random r1 = new Random();
        int x = r1.nextInt(2);
        int y = r1.nextInt(2);

        if(x==0){
            this.SetX(this.GetX()-1);
        }
        if(x==1){
            this.SetX(this.GetX()+1);
        }
        if(y==0){
            this.SetY(this.GetY()-1);
        }
        if(y==1){
            this.SetY(this.GetY()+1);
        }
    }
    public void rozmnazanie(){

    }
    public void walka(Organizm o){
        if (this.GetSila() > o.GetSila()) {
            this.GetSwiat().deleteOrganizm(o.GetID());
            String n = "Organizm ("+this.getClass().getSimpleName() + ") zabija ("+o.getClass().getSimpleName()+")";
            this.GetSwiat().info.add(n);
        }
        if (this.GetSila() < o.GetSila()) {
            this.GetSwiat().deleteOrganizm(this.GetID());
            String n = "Organizm ("+o.getClass().getSimpleName() + ") zabija ("+this.getClass().getSimpleName()+")";
            this.GetSwiat().info.add(n);
        }
        if (this.GetSila() == o.GetSila()) {
            this.GetSwiat().deleteOrganizm(o.GetID());
            String n = "Organizm ("+this.getClass().getSimpleName() + ") zabija ("+o.getClass().getSimpleName()+")";
            this.GetSwiat().info.add(n);
        }
    }
    public void uciekaj(){
        this.SetX(this.GetLastX());
        this.SetY(this.GetLastY());
    }
}
