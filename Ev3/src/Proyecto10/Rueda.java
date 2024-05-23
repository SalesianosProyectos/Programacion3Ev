package Proyecto10;

import java.awt.*;
import java.applet.*;

public class Rueda {
    public static final int TAM = 20;
    Image imagenes[];
    int x, y;
    int imgActual = 0;

    public Rueda(Image imagenes[], int x, int y) {
        this.imagenes = imagenes;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagenes[imgActual], x, y, TAM, TAM, a);
    }

    public void update() {
        imgActual++;
        if (imgActual == imagenes.length) {
            imgActual = 0;
        }
    }
}
