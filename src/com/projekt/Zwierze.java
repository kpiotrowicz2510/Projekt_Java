package com.projekt;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
                    if(this.GetSwiat().GetCzlowiek().isSpecial()){
                        if(this.getClass().getSimpleName().equalsIgnoreCase("Czlowiek")){}else {
                            this.uciekaj();
                        }
                    }else {
                        this.walka(org);
                    }
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
        Dimension a = this.GetSwiat().freeSpaceP(this.GetX(),this.GetY());
        Organizm d=null;
        //Mlecz g = new Mlecz(this.GetSwiat());
        try {
            Class<?> theClass = Class.forName(this.getClass().getName());
            Constructor ctor = theClass.getDeclaredConstructor(Swiat.class);
            ctor.setAccessible(true);
            d = (Organizm)ctor.newInstance(this.GetSwiat());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        d.SetColor(this.GetColor());
        d.SetX(a.width);
        d.SetY(a.height);
        if (a.width > -1 && a.height > -1) {
            this.GetSwiat().AddOrganizm(d, a.width, a.height);
        }
    }
    public void walka(Organizm o){
        System.out.println(o.getClass().getSimpleName());
        if (this.GetSila() > o.GetSila()) {
            if(o.getClass().getSimpleName().equalsIgnoreCase("Mlecz")||o.getClass().getSimpleName().equalsIgnoreCase("Trawa")){
                String n = "Organizm (" + this.getClass().getSimpleName() + ") zjada (" + o.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }else {
                String n = "Organizm (" + this.getClass().getSimpleName() + ") zabija (" + o.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }
            this.GetSwiat().deleteOrganizm(o.GetID());
        }
        if (this.GetSila() < o.GetSila()) {
            if(o.getClass().getSimpleName().equalsIgnoreCase("Mlecz")||o.getClass().getSimpleName().equalsIgnoreCase("Trawa")){
                String n = "Organizm (" + o.getClass().getSimpleName() + ") zjada (" + this.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }else {
                String n = "Organizm (" + o.getClass().getSimpleName() + ") zabija (" + this.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }
            this.GetSwiat().deleteOrganizm(this.GetID());
        }
        if (this.GetSila() == o.GetSila()) {
            if(o.getClass().getSimpleName().equalsIgnoreCase("Mlecz")||o.getClass().getSimpleName().equalsIgnoreCase("Trawa")){
                String n = "Organizm (" + this.getClass().getSimpleName() + ") zjada (" + o.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }else {
                String n = "Organizm (" + this.getClass().getSimpleName() + ") zabija (" + o.getClass().getSimpleName() + ")";
                this.GetSwiat().info.add(n);
            }
            this.GetSwiat().deleteOrganizm(o.GetID());
        }
    }
    public void uciekaj(){
        this.SetX(this.GetLastX());
        this.SetY(this.GetLastY());
    }
}
