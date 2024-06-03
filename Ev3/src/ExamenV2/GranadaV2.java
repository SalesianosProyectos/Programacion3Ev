package ExamenV2;

import java.awt.*;
import java.applet.Applet;

public class GranadaV2 extends Rectangle {
    public static final int VEL = 10;
    public static final int SIZE = 10;

    Image imgGranada;

    public GranadaV2(Image imgGranada, int x, int y) {
        super(x, y, SIZE, SIZE);
        this.imgGranada = imgGranada;
    }

    public void paint(Graphics nsv, Applet a) {

        nsv.drawImage(imgGranada, x, y, width, height, a);
    }

    public void update() {
        y += VEL;
    }
}
