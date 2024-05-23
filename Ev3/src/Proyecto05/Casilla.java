package Proyecto05;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 */
public class Casilla extends Rectangle {

    public static final int DIMX = 100;
    public static final int DIMY = 50;

    Color color;
    int numero;

    int total;

    public Casilla(int num, int x, int y) {

        super((x * DIMX) + DIMX / 2, (y * DIMY) + 180, DIMX, DIMY);

        if (num == 0) {
            color = new Color(40, 115, 40);

            width = DIMX * 3;
            this.y = 130;

        } else if ((num >= 1 && num <= 10) || (num >= 19 && num <= 28)) {
            if (num % 2 == 0) {
                color = Color.BLACK;
            } else {
                color = Color.RED;
            }
        } else {
            if (num % 2 == 0) {
                color = Color.RED;
            } else {
                color = Color.BLACK;
            }
        }
        numero = num;
    }

    public void paint(Graphics g, Applet a) {

        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.drawString("" + numero, x + (width - g.getFontMetrics().stringWidth("" + numero)) / 2,
                y + ((height - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent());
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);

    }

}
