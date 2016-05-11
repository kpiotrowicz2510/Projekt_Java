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
    private int pressedKey=-1;
    private int tura_numer;
    private int sRX=30;
    private int sRY=30;
    private int new_id;
    private Czlowiek czlowiek;
    private List<Organizm> organizmy;


    protected List<String> info;
    private Window okienko;

    public Swiat(){
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
    public int GetPressedKey(){ return this.pressedKey; }
    public int GetTura(){return this.tura_numer;}
    public void UpdateLoop(int key){
        this.WykonajTure(key);
        this.UpdateLog();
        this.RysujSwiat();
    }
    private void UpdateLog(){
        JPanel p1 = (JPanel) this.okienko.getContentPane().getComponents()[0];
        JEditorPane jep = (JEditorPane) p1.getComponent(p1.getComponentCount()-1);
        String log = "<div align='center'>";
        for (int i = this.info.size()-1; i >= 0 ; i--) {
            log+="<div>"+this.info.get(i).replace("com.projekt.","")+"</div>";
        }
        log+="</div>";
        jep.setText(log);
    }
    private void WykonajTure(int key){

        switch (key) {
            case 38: //str up
                this.pressedKey = 0;
                break;
            case 40: //str down
                this.pressedKey = 1;
                break;
            case 39: //str prawo
                this.pressedKey = 3;
                break;
            case 37: //str lewo
                this.pressedKey = 2;
                break;
            case 83: //str spacja
                this.pressedKey = 4;
                break;
        }
        for (int i = this.organizmy.size()-1; i >= 0 ; i--){
            this.organizmy.get(i).akcja();
            this.organizmy.get(i).kolizja();
        }
        this.pressedKey=-1;
        this.tura_numer++;
    }
    public void SetWindow(Window a){
        this.okienko = a;
    }
    private void Clear(Color c) {
        JPanel p1 = (JPanel) this.okienko.getContentPane().getComponents()[0];
        for(int i =0;i<this.sRY;i++){
            JPanel x2  = (JPanel) p1.getComponents()[i];
            for(int j=0;j<this.sRX;j++){
                JButton b = (JButton) x2.getComponent(j);

                    b.setBackground(c);

            }
        }
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
        this.Clear(Color.lightGray);
        for (int i = 0; i < this.organizmy.size() ; i++){
           this.ChangeState(this.organizmy.get(i).GetColor(),this.organizmy.get(i).GetX(),this.organizmy.get(i).GetY());
        }
    }

    public void AddOrganizm(Organizm o){
        Random r1 = new Random();
        Random r2 = new Random();

        int x = r1.nextInt(this.sRX);
        int y = r2.nextInt(this.sRY);

        if(this.freeSpace(x,y)){
            o.SetID(this.new_id);
            o.SetX(x);
            o.SetY(y);
            this.new_id++;
            this.organizmy.add(o);
            this.organizmy.sort(Comparator.comparing(Organizm::GetInicjatywa));
            this.info.add("Dodano nowy organizm "+o.getClass().getName());
        }
    }
    public void AddOrganizm(Organizm o, int x, int y){

        if(this.freeSpace(x,y)){
            //this.org_c.split(",")[y*this.sRX+x] = o.GetColor().toString();
            o.SetID(this.new_id);
            this.new_id++;
            this.organizmy.add(o);
            this.organizmy.sort(Comparator.comparing(Organizm::GetInicjatywa));
            this.info.add("Dodano nowy organizm "+o.getClass().getName());
        }
    }
    public Organizm podajOrganizm(int x, int y){
        for (int i = 0; i < this.organizmy.size(); i++) {
            if (this.organizmy.get(i).GetX() == x &&this.organizmy.get(i).GetY() == y) {
                return this.organizmy.get(i);
            }
        }
        return null;
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
    }
    public Dimension freeSpaceP(int x2, int y2){
        int xs = 0;
        int ys = 0;
        if (x2 == 0) {
            xs = 1;
        }
        if (y2 == 0) {
            ys = 1;
        }
        for (int y = -1 + ys; y < 2 + ys; y++) {
            for (int x = -1 + xs; x < 2 + xs; x++) {
                if (this.freeSpace((x2 + x), (y2 + y))) {
                    Dimension d = new Dimension(x2+x,y2+y);
                    return d;
                }
            }
        }
        return new Dimension(-1,-1);
    }
    public void SetCzlowiek(Czlowiek c){
        this.czlowiek = c;
    }
    public Czlowiek GetCzlowiek(){
        return this.czlowiek;
    }
}
