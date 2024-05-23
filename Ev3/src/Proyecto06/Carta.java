package Proyecto06;

import java.applet.Applet;
import java.awt.*;

public class Carta {
    public static final int ANCHO = 100;
    public static final int ALTO = 200;
    Image imagen;
    private int valor;
    private int posX, posY;

    public Carta(Image imagen, int valor) {
        this.imagen = imagen;
        this.valor = (valor > 10) ? 10 : valor;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public int getValor() {
        return valor;
    }

    public void paint(Graphics g, Applet a) {

        g.drawImage(imagen, posX, posY, ANCHO, ALTO, a);

    }
}
