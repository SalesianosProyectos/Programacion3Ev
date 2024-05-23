package Proyecto04;

import java.applet.Applet;
import java.awt.*;


public class Lugar {
    public static final int DIM = 48;
    Image imagen;
    int valor;

    public Lugar(Image img, int valor) {
        imagen = img;
        this.valor = valor;
    }

    public void paint(Graphics g, Applet a, int x, int y) {
        g.drawImage(imagen, x, y, a);

    }

    public Image getValor() {
        return imagen;

    }

    public void setValor(Image imagen) {
        this.imagen = imagen;
    }
/*
    public int getValorNumerico() {
        return valor;
    }

    public void setValorNumerico(int valor) {
        this.valor = valor;
    }

    public boolean equals(Lugar l) {
        return this.valor == l.valor;
    }

    public void intercambiar(Lugar l) {
        Image img = this.imagen;
        this.imagen = l.imagen;
        l.imagen = img;
        int v = this.valor;
        this.valor = l.valor;
        l.valor = v;
    }

    public boolean estaVacio() {
        return this.valor == 24;
    }
   */  
}
