package Proyecto03;

import java.applet.Applet;
import java.awt.*;

public class Globo extends Rectangle {

    public static final int DIMENSION = 50;
    public static final int POSICION = 600;

    Image imagen;

    public Globo(Image img) {
        super((int) (Math.random() * 150) + 500, POSICION, DIMENSION, DIMENSION);
        imagen = img;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagen, x, y, width, height, a);
    }

    public boolean update() {
        y -= 5;
        if (y < -100)
            return true;
        return false;
    }

}
