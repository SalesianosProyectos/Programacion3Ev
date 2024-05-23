package Proyecto08;

import java.applet.Applet;
import java.awt.*;
import java.awt.Rectangle;

public class Casilla extends Rectangle {
    public static final int TAM = 17;

    private Image mina;
    Image reverso;
    private boolean tapada = true;
    private int alrededor = 0;

    public Casilla(int x, int y, Image reverso) {
        super(x, y, TAM, TAM);
        this.reverso = reverso;
    }

    public Image getMina() {
        return mina;
    }

    public void setMina(Image mina) {
        this.mina = mina;
    }

    public void destapar() {
        tapada = false;
    }

    public void setAlrededor(int alrededor) {
        this.alrededor = alrededor;
    }

    public void paint(Graphics g, Applet a) {
        if (tapada) {
            g.drawImage(reverso, x, y, a);
        } else {
            if (mina != null) {
                g.drawImage(mina, x, y, a);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x, y, TAM, TAM);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TAM, TAM);
                if (alrededor > 0) {
                    g.drawString((alrededor == 0) ? "" : "" + alrededor, x + 4, y + 13);
                }
            }
        }
    }
}
