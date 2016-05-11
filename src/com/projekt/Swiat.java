package com.projekt;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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
    }
    public int GetRX(){
        return this.sRX;
    }
    public int GetRY(){
        return this.sRY;
    }
    public int GetPressedKey(){ return this.pressedKey; }
    public int GetTura(){return this.tura_numer;}
    public void SetR(Dimension a){
        this.sRX = a.width;
        this.sRY = a.height;
    }
    public void UpdateLoop(int key){
        this.WykonajTure(key);
        this.UpdateLog();
        this.RysujSwiat();
    }
    public void Save(){
        try(  PrintWriter out = new PrintWriter( "world.txt" )  ){
            String dane="";
            dane+=this.GetRX()+"\t"+this.GetRY()+"\t";
            out.print( dane );
            out.print(this.new_id+"\t"+this.tura_numer+"\t");
            out.print(this.organizmy.size()+"\t");
            for (int i = 0; i < this.organizmy.size(); i++) {
                dane = "";
                dane += this.organizmy.get(i).GetID() + "\t" + this.organizmy.get(i).getClass().getSimpleName() +"\t" + this.organizmy.get(i).GetColor().getRGB() + "\t" + this.organizmy.get(i).GetInicjatywa() + "\t" + this.organizmy.get(i).GetSila() + "\t" + this.organizmy.get(i).GetX() + "\t" + this.organizmy.get(i).GetY() + "\t";
                out.print(dane);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void Load(){
        try(BufferedReader br = new BufferedReader(new FileReader("world.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String[] values = everything.split("\\t");
            this.organizmy.clear();
            this.info.clear();
            int rx = Integer.parseInt(values[0]);
            int ry = Integer.parseInt(values[1]);
            this.SetR(new Dimension(rx,ry));
            this.okienko.SetR(new Dimension(rx,ry));
            this.okienko.init(this);
            this.UpdateLog();
            int nid = Integer.parseInt(values[2]);
            int tn = Integer.parseInt(values[3]);
            int os = Integer.parseInt(values[4]);
            int start = 5;
            for(int i=0; i<os;i++){

                int id = Integer.parseInt(values[start]);
                String name = values[start+1];
                Color color = Color.decode(values[start+2]);
                int inicjatywa = Integer.parseInt(values[start+3]);
                int sila = Integer.parseInt(values[start+4]);
                int x = Integer.parseInt(values[start+5]);
                int y = Integer.parseInt(values[start+6]);
                switch (name){
                    case "Antylopa" :
                        Antylopa a1 = new Antylopa(this);
                        this.AddOrganizm(a1,x,y);
                        break;
                    case "Czlowiek" :
                        Czlowiek c1 = new Czlowiek(this);
                        this.AddOrganizm(c1,x,y);
                        this.SetCzlowiek(c1);
                        break;
                    case "Guarana" :
                        Guarana g1 = new Guarana(this);
                        this.AddOrganizm(g1,x,y);
                        break;
                    case "Jagody" :
                        Jagody j1 = new Jagody(this);
                        this.AddOrganizm(j1,x,y);
                        break;
                    case "Lis" :
                        Lis l1 = new Lis(this);
                        this.AddOrganizm(l1,x,y);
                        break;
                    case "Mlecz" :
                        Mlecz m1 = new Mlecz(this);
                        this.AddOrganizm(m1,x,y);
                        break;
                    case "Owca" :
                        Owca o1 = new Owca(this);
                        this.AddOrganizm(o1,x,y);
                        break;
                    case "Trawa" :
                        Trawa t1 = new Trawa(this);
                        this.AddOrganizm(t1,x,y);
                        break;
                    case "Zolw" :
                        Zolw z1 = new Zolw(this);
                        this.AddOrganizm(z1,x,y);
                        break;
                }
                start+=7;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void UpdateLog(){
        //JPanel p1 = (JPanel) this.okienko.getContentPane().getComponents()[2];
        JEditorPane jep = (JEditorPane)  this.okienko.getContentPane().getComponents()[2];
        String log = "<div align='center' style='width:200px'>";
        log += "<div align='left'> Tura: <b>"+this.tura_numer +"</b></div>";
        if(this.GetCzlowiek().isSpecial()) {
            log += "<div align='left'> Umiejetnosc: <b>Aktywna</b></div>";
        }
        log += "<br><hr><br>";
        for (int i = this.info.size()-1; i >= 0; i--) {
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
            o.SetID(this.new_id);
            o.SetX(x);
            o.SetY(y);
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
