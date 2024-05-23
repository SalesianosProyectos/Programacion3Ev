package Proyecto02;

import java.applet.Applet;
import java.awt.*;

public class Pieza extends Rectangle {

    public static final int DIMENSION = 60;
    Image imagen;

    int posicion;
    private boolean colocada = false;

    public Pieza(Image imagen, int pos) {
        super((int) ((Math.random() * 240) + 400), (int) (Math.random() * 440), DIMENSION, DIMENSION);
        this.imagen = imagen;
        posicion = pos;
    }
    
    public boolean isColocada() {
        return colocada;
    }

    public void setColocada(boolean colocada) {
        this.colocada = colocada;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagen, x, y, a);
    }

    public void update(int x, int y) {
        if (!colocada) {
            this.x = x - (DIMENSION / 2);
            this.y = y - (DIMENSION / 2);
        }
    }
    

    public void moverPosicion(int posx, int posy) {
        x = posx;
        y = posy;
    }
}
