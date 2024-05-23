package Proyecto07;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Carta extends Rectangle{
    public static final int ANCHO=70;
    public static final int ALTO=120;
    public static final int PICAS=0;
    public static final int ROMBOS=0;
    public static final int CORAZONES=0;
    public static final int TREBOLES=0;
    public static final int ROJO=1;
    public static final int NEGRO=2;
    
    Image imagen;
    private int valor;
    private int color;
    private int palo;
    
    public Carta(Image imagen, int valor, int color, int palo){
       super(-200, -200, ANCHO, ALTO);   
        this.imagen=imagen;
        this.valor=valor;
        this.palo=palo;
        this.color=color;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPalo() {
        return palo;
    }

    public void setPalo(int palo) {
        this.palo = palo;
    }
    
    public void paint(Graphics g, Applet a){
        g.drawImage(imagen, x, y, ANCHO, ALTO, a);       
    }
     
    
}
