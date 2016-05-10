package com.projekt;

import java.awt.*;

/**
 * Created by usr on 2016-05-09.
 */
public abstract class Organizm {
    public int GetX(){
        return this.posX;
    }
    public int GetY(){
        return this.posY;
    }
    public Swiat GetSwiat(){
        return this.swiat;
    }
    public Color GetColor(){
        return this.color;
    }
    public void SetColor(Color c){
        this.color = c;
    }
    public void SetID(int id){
        this.id = id;
    }
    public int GetID(){
        return this.id;
    }
    public void SetX(int value){
        if (value > -1 && value < this.swiat.GetRX() - 1) {
            this.last_posX = this.posX;
            this.posX = value;
        }
    }
    public void SetY(int value){
        if (value > -1 && value < this.swiat.GetRY() - 1) {
            this.last_posY = this.posY;
            this.posY = value;
        }
    }
   public void SetSwiat(Swiat swiat){
       this.swiat = swiat;
   }
    public void SetSila(int value){
        this.sila = value;
    }
    public int GetSila(){
        return  this.sila;
    }
    public int GetInicjatywa(){
        return this.inicjatywa;
    }
    public int GetLastX(){
        return this.last_posX;
    }
    public int GetLastY(){
        return this.last_posY;
    }
    abstract void akcja();
    abstract void kolizja();
    public void rysowanie() {

    }

    protected int sila;
    protected int inicjatywa;
    protected Swiat swiat;

    private Color color;
    private int id;
    private char symbol;
    private int posX;
    private int posY;
    private int last_posX;
    private int last_posY;
    public Organizm(){}
}
