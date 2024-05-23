package Proyecto03;

import java.applet.Applet;
import java.awt.*;

public class Arquero {

    Image imgArquero;
    private int y;

    public Arquero(Image imgArquero) {
        this.imgArquero = imgArquero;
        y = 200;
    }

    public void paint(Graphics noseve, Applet a) {
        noseve.drawImage(imgArquero, 30, y, a);
    }

    public void setY(int y) {
        this.y = y - 40;
    }

    public int getY() {
        return y;
    }

}
