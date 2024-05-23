package Proyecto05;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import java.util.List;
import java.util.ArrayList;

/**
 *
 */
public class Ficha extends Rectangle {

    public static final int DIM = 40;

    int valor;

    int numChocando = 0;

    List<Integer> casillasChocando = new ArrayList<>();

    Image img;

    public Ficha(int valor, int x, int y, Image img[]) {
        super(x, y, DIM, DIM);
        this.valor = valor;

        switch (valor) {
            case 1:
                this.img = img[0];
                break;
            case 5:
                this.img = img[1];

                break;
            case 10:
                this.img = img[2];

                break;
            case 25:
                this.img = img[3];

                break;
            case 50:
                this.img = img[4];

                break;

        }

    }

    public void paint(Graphics g, Applet a) {
        g.drawImage(img, x, y, width, height, a);
    }

    public void update(int x, int y, Casilla casillas[][]) {
        this.x = x - width / 2;
        this.y = y - height / 2;

        comprobarCasillas(casillas);

    }

    private void comprobarCasillas(Casilla[][] casillas) {
        int tempcasillasChocando = 0;
        casillasChocando.clear();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                if (casillas[i][j].intersects(this)) {
                    tempcasillasChocando++;
                    casillasChocando.add(casillas[i][j].numero);
                }
            }
        }

        if (casillas[12][0].intersects(this)) {
            tempcasillasChocando++;
            casillasChocando.add(casillas[12][0].numero);
        }

        numChocando = tempcasillasChocando;
    }

    public int comprobar(int num) {
        int temp = 0;
        for (Integer casilla : casillasChocando) {
            if (casilla == num) {
                int valorCorrespondiente = (int) Math.floor(36.0 / numChocando);
                temp += (valor * valorCorrespondiente) + valor;
            }
        }
        return temp;
    }

}
