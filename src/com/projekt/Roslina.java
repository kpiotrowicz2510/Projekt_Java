package com.projekt;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Created by usr on 2016-05-11.
 */
public class Roslina extends Organizm{
    public void akcja(){
        Random r1 = new Random();
        int x = r1.nextInt(50);
        if(x==20){
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
