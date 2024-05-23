package Proyecto03;

import java.applet.Applet;
import java.awt.*;

public class Flecha extends Point {

    public static final int ANCHURA = 60;
    public static final int ALTURA = 15; 

    Image imagen;
    
    public Flecha(Image img, int y) {
        super(170, y);    
        imagen = img;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagen, x - ANCHURA, y - (ANCHURA/2), ANCHURA, ALTURA, a);
    }

    // Metodo update para que avance hacia la derecha
    public void update() {
        x += 10;        
    }

    public boolean intersects(Globo globo) {
        return (globo.x < x && globo.x + globo.width > x && globo.y < y && globo.y + globo.height > y);
        
    }

}
