package Proyecto07;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public abstract class Mazo extends Rectangle {
    ArrayList<Carta> mazo;

    public Mazo(int x, int y, int anchura, int altura) {
        super(x, y, anchura, altura);
        mazo = new ArrayList<Carta>();
    }

    public abstract void paint(Graphics g, Applet a);
}
