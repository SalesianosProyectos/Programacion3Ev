package Proyecto10;

import java.applet.Applet;
import java.awt.*;

public class Coche extends Rectangle {
    public static final int TAM = 120;
    int x, y;
    Image coche;

    Faro faro;
    boolean encendida;

    public Coche(int x, int y, Image coche, Faro faro) {
        this.x = x;
        this.y = y;
        this.coche = coche;
        this.faro = faro;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(coche, 15, 230, TAM, TAM, a);
        faro.paint(nsv, a);
    }

    public boolean mouseDown(Event evt, int x, int y) {
        if (x > this.x && x < this.x + TAM && y > this.y && y < this.y + TAM) {
            faro.encendida = !faro.encendida;
            return true;
        }
        return false;
    }
}
