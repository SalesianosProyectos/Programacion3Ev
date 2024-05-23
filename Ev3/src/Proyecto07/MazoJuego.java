package Proyecto07;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MazoJuego extends Rectangle {
    java.util.ArrayList<Carta>mazo;
    public static final int POSICIONY = 200;

    public MazoJuego(int posx) {
        super(posx, POSICIONY, Carta.ANCHO, Carta.ALTO);
        mazo = new java.util.ArrayList<Carta>();
    }

    public void paint(Graphics g, Applet a) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        for (Carta carta : mazo)
            carta.paint(g, a);
    }

    public boolean anadir(Carta carta) {
        if (mazo.isEmpty()) {
            mazo.add(carta);
            recolocar();
            return true;
        } else {
            if (carta.getColor() != mazo.get(mazo.size() - 1).getColor()) // Si el color de la carta es distinto al
                                                                          // color de la ultima carta del mazo
                if ((carta.getValor() + 1) == mazo.get(mazo.size() - 1).getValor()) {
                    mazo.add(carta);
                    recolocar();
                    return true;
                }
        }
        return false;
    }

    public void recolocar() {
        mazo.get(mazo.size() - 1).x = x;
        mazo.get(mazo.size() - 1).y = y + ((mazo.size() - 1) * 30);
    }

    public Carta sacarCarta() {
        return mazo.remove(mazo.size() - 1);
    }

    public Carta ultimaCarta() {
        return mazo.get(mazo.size() - 1);
    }

}
