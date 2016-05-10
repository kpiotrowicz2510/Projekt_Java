package com.projekt;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by usr on 2016-05-09.
 */
public class Swiat {
    private int tura_numer;
    private int sRX=10;
    private int sRY=10;
    private int new_id;
    private Organizm czlowiek;
    private List<Organizm> organizmy;

    protected List<String> info;
    private Window okienko;

    public Swiat(Window okienko){
        this.okienko = okienko;
        this.organizmy = new ArrayList<Organizm>();
        this.info = new ArrayList<String>();
       // Window.getFrames().getClass().;
    }
    public int GetRX(){
        return this.sRX;
    }
    public int GetRY(){
        return this.sRY;
    }
    public void UpdateLoop(){

    }

    private void WykonajTure(){

    }
    private void ChangeState(Color c, int x, int y){
        JPanel p1 = (JPanel) this.okienko.getContentPane().getComponents()[0];
        for(int i =0;i<this.sRY;i++){
            JPanel x2  = (JPanel) p1.getComponents()[i];
            for(int j=0;j<this.sRX;j++){
                JButton b = (JButton) x2.getComponent(j);
                if(x==j&&i==y){
                    b.setBackground(c);
                }
            }
        }
    }
    public void RysujSwiat(){
        for(int i =0; i<this.organizmy.size();i++){
           this.ChangeState(this.organizmy.get(i).GetColor(),this.organizmy.get(i).GetX(),this.organizmy.get(i).GetY());
        }
    }

    public void AddOrganizm(Organizm o){
        Random r1 = new Random(this.sRX);
        Random r2 = new Random(this.sRY);

        int x = r1.nextInt();
        int y = r2.nextInt();

        if(this.freeSpace(x,y)){
            //this.org_c.split(",")[y*this.sRX+x] = o.GetColor().toString();
            o.SetID(this.new_id);
            this.new_id++;
            this.organizmy.add(o);
            this.organizmy.sort(new Comparator<Organizm>() {
                @Override
                public int compare(Organizm o1, Organizm o2) {
                    if(o1.inicjatywa>o2.inicjatywa) {
                        return 0;
                    }else{
                        return 1;
                    }
                }
            });
            this.info.add("Dodano nowy organizm "+o.getClass().getName());
        }
    }
    public void AddOrganizm(Organizm o, int x, int y){

        if(this.freeSpace(x,y)){
            //this.org_c.split(",")[y*this.sRX+x] = o.GetColor().toString();
            o.SetID(this.new_id);
            this.new_id++;
            this.organizmy.add(o);
            this.organizmy.sort(new Comparator<Organizm>() {
                @Override
                public int compare(Organizm o1, Organizm o2) {
                    if(o1.inicjatywa>o2.inicjatywa) {
                        return 0;
                    }else{
                        return 1;
                    }
                }
            });
            this.info.add("Dodano nowy organizm "+o.getClass().getName());
        }
    }
    public void deleteOrganizm(int id){
        int del_id = -1;
        for (int i = 0; i < this.organizmy.size(); i++) {
            if (this.organizmy.get(i).GetID() == id) {
                del_id = i;
            }
        }
        if(del_id>-1){
            this.organizmy.remove(del_id);
            this.info.add("Usunieto organizm o id "+del_id);
        }
    }
    public boolean freeSpace(int x, int y){
        boolean ret = true;
        for(int i=0;i<this.organizmy.size();i++){
            if(this.organizmy.get(i).GetX()==x&&this.organizmy.get(i).GetY()==y){
                ret = false;
            }
        }
        return ret;
       // if(this.org_c.split(",")[y*this.sRX+x]!=""){
       //     return false;
       // }else{
       //     return true;
       // }
    }
    public void SetCzlowiek(Organizm c){
        this.czlowiek = c;
    }
    public Organizm GetCzlowiek(){
        return this.czlowiek;
    }
}
