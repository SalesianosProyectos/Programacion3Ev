package Proyecto09;

import java.awt.*;
import java.applet.Applet;

public class Casilla extends Rectangle {

    public static final int DIM = 100;

    private Image imagen;
    Image reverso;
    private boolean descubierta;

    public Casilla(int x, int y, Image reverso, Image imagen) {
        super(x, y, DIM, DIM);
        this.imagen = imagen;
        this.reverso = reverso;
        descubierta = false;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public boolean isDescubierta() {
        return descubierta;
    }

    public void setDescubierta(boolean descubierta) {
        this.descubierta = descubierta;
    }

    public void paint(Graphics g, Applet a) {
        if (descubierta) {
            g.drawImage(imagen, x, y, DIM, DIM, a);
        } else {
            g.drawImage(reverso, x, y, DIM, DIM, a);
            g.drawRect(x, y, width, height);
        }

    }

}
