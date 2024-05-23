package Proyecto01;

import java.applet.Applet;
import java.awt.*;

public class DibujoAnimado extends Rectangle {

    private Image[] imagenes;
    int imgActual = 0;

    public DibujoAnimado(Image imgs[]) {
        super((int)(Math.random() * 250), (int)(Math.random() * 200), 50, 75);
        imagenes = imgs;

    }
    
    public Image[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(Image[] imagenes) {
        this.imagenes = imagenes;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagenes[imgActual], x, y, width, height, a);
    }

    public void update() {
        /* 
        imgActual++;
        if (imgActual == imagenes.length) {
            imgActual = 0;
        */

        imgActual = (imgActual + 1) % imagenes.length;
        
    }
}
