package Proyecto05;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class Historial {

    List<Integer> numeros;
    List<Integer> dinero;

    public Historial() {

        numeros = new ArrayList<Integer>();
        dinero = new ArrayList<Integer>();
    }

    public void addHistorial(int numero, int dinero) {
        numeros.add(numero);
        this.dinero.add(dinero);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(500, 125, 275, 660);
        g.setColor(Color.WHITE);
        g.drawRect(500, 125, 275, 660);

        g.drawRect(500, 125, 275, 50);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Historial", 500 + (275 - g.getFontMetrics().stringWidth("Historial")) / 2,
                125 + ((50 - g.getFontMetrics().getHeight()) / 2) + g.getFontMetrics().getAscent());

        g.setFont(new Font("Arial", Font.BOLD, 20));

        for (int i = numeros.size() - 1; i >= Math.max(0, numeros.size() - 12); i--) {
            int drawIndex = numeros.size() - 1 - i;

            g.setColor(Color.WHITE);
            g.drawString("" + numeros.get(i), 550, 215 + (drawIndex * 50));

            int tempdinero = dinero.get(i);

            if (tempdinero >= 0) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.RED);
            }

            g.drawString("" + tempdinero + " €", 685, 215 + (drawIndex * 50));

        }

    }

}
