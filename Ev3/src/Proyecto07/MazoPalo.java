package Proyecto07;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class MazoPalo extends Mazo {
    int palo;

    public MazoPalo(int posx) {
        super(posx, MazoSecundario.POSICIONY, Carta.ANCHO, Carta.ALTO);
    }

    public void paint(Graphics g, Applet a) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        for (Carta carta : mazo)
            carta.paint(g, a);
    }

    public boolean anadir(Carta carta) {
        if (mazo.isEmpty()) {
            if (carta.getValor() == 1) {
                mazo.add(carta);
                palo = carta.getPalo();
                return true;
            }
        } else {
            if (carta.getPalo() == palo)
                if (carta.getValor() == mazo.get(mazo.size() - 1).getValor() + 1) {
                    mazo.add(carta);
                    return true;
                }
        }
        return false;
    }

    public void recolocar() {
        mazo.get(mazo.size() - 1).x = x;
        mazo.get(mazo.size() - 1).y = y;
    }

}
