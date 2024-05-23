package Proyecto11;

import java.awt.*;
import java.applet.*;

public class Eslabon extends Rectangle {
    public static final int SIZE = 15;
    public static final int IZQ = 0;
    public static final int DER = 1;
    public static final int ARR = 2;
    public static final int ABA = 3;
    public static final int PARADO = 4;
    private int direccion = PARADO;
    Image imagen;

    Image casilla;

    public Eslabon(Image imagen, int x, int y, int direccion) {
        super(x, y, SIZE, SIZE);
        this.imagen = imagen;
        this.direccion = direccion;

    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagen, x, y, width, height, a);
    }

    public void update() {
        mover();
    }

    public void mover() {
        switch (direccion) {
            case IZQ:
                x -= SIZE;
                break;
            case DER:
                x += SIZE;
                break;
            case ARR:
                y -= SIZE;
                break;
            case ABA:
                y += SIZE;
                break;
        }
    }

    public Image getImagen(Image img) {
        return imagen = img;
    }

    public Image setImagen(Image img) {
        return imagen = img;
    }
}
