package Proyecto07;

import java.util.ArrayList;
import java.awt.*;
import java.applet.Applet;

public class MazoSecundario {
    ArrayList<Carta> mazo;
    public static final int POSICIONX = 110;
    public static final int POSICIONY = 20;

    public MazoSecundario() {
        mazo = new ArrayList<Carta>();
    }

    public void paint(Graphics g, Applet a) {
        for (Carta carta : mazo) {
            carta.paint(g, a);
        }
    }

    public void anadir(Carta carta) {
        mazo.add(carta);
        recolocar();
    }

    public Carta sacarCarta() {
        return mazo.remove(mazo.size() - 1);
    }

    public Carta ultimaCarta() {
        return mazo.get(mazo.size() - 1);
    }

    public void recolocar() {
        mazo.get(mazo.size() - 1).x = POSICIONX;
        mazo.get(mazo.size() - 1).y = POSICIONY;

    }
}
