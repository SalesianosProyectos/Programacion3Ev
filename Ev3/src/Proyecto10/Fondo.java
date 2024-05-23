package Proyecto10;

import java.awt.*;
import java.applet.Applet;

public class Fondo {
    Image imagen;
    int x1, x2;
    Applet applet;

    public Fondo(Image imagen, int x, Applet a) {
        this.imagen = imagen;
        this.x1 = 0;
        this.x2 = a.getWidth();
        applet = a;
    }

    public void paint(Graphics nsv) {
        nsv.drawImage(imagen, x1, 0, applet.getWidth(), applet.getHeight(), applet);

        nsv.drawImage(imagen, x2, 0, applet.getWidth(), applet.getHeight(), applet);
    }

    public void update(Graphics nsv) {
        x1 -= 10;
        x2 -= 10;
        if (x1 <= -applet.getWidth()) {
            x1 = 0;
            x2 = applet.getWidth();
        }

        paint(nsv);
    }
}
