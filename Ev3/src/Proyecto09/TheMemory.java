package Proyecto09;

import java.awt.*;
import java.applet.Applet;

public class TheMemory extends Applet implements Runnable {
    public static final int NUM_IMG = 8;
    public static final int TIEMPO = 3000;
    Thread animacion;
    Image imgReverso;
    Image imagenes[];
    Casilla casillas[][];
    Casilla cas1;
    Casilla cas2;
    Graphics globalGraphics;

    public void init() {
        imgReverso = getImage(getCodeBase(), "Proyecto09/donutsMatch/reverso.png");
        imagenes = new Image[NUM_IMG];
        for (int i = 0; i < NUM_IMG; i++) {
            imagenes[i] = getImage(getCodeBase(), "Proyecto09/donutsMatch/img" + (i + 1) + ".png");
        }
        casillas = new Casilla[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                casillas[i][j] = new Casilla(i * Casilla.DIM + 100, j * Casilla.DIM + 100, imgReverso,
                        imagenes[((i * 4) + j) % NUM_IMG]);
            }
        }
        desordenar();

        
    }

    public void desordenar() {
        for (int i = 0; i < 50; i++) {
            int numAleatorio1 = (int) (Math.random() * 16);
            int numAleatorio2 = (int) (Math.random() * 16);
            Image auxiliar = casillas[numAleatorio1 / (NUM_IMG / 2)][numAleatorio1 % (NUM_IMG / 2)].getImagen();
            casillas[numAleatorio1 / (NUM_IMG / 2)][numAleatorio1 % (NUM_IMG / 2)]
                    .setImagen(casillas[numAleatorio2 / (NUM_IMG / 2)][numAleatorio2 % (NUM_IMG / 2)].getImagen());
            casillas[numAleatorio2 / (NUM_IMG / 2)][numAleatorio2 % (NUM_IMG / 2)].setImagen(auxiliar);
        }
    }

    public void paint(Graphics g) {
        globalGraphics = g;
        for (int i = 0; i < NUM_IMG / 2; i++) {
            for (int j = 0; j < NUM_IMG / 2; j++) {
                casillas[i][j].paint(g, this);
            }
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public boolean mouseDown(Event evt, int x, int y) {
        for (int i = 0; i < NUM_IMG / 2; i++) {
            for (int j = 0; j < NUM_IMG / 2; j++) {
                if (casillas[i][j].contains(x, y)) {
                    casillas[i][j].setDescubierta(true);
                    if (cas1 == null) {
                        cas1 = casillas[i][j];
                    } else {
                        cas2 = casillas[i][j];
                    }
                }
            }
        }
        repaint();
        return true;
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void run() {
        while (true) {
            contador();
            repaint();
            try {
                Thread.sleep(TIEMPO);
            } catch (InterruptedException e) {
            }
        }
    }

    public void contador() {
        if (cas1 != null && cas2 != null) {
            if (cas1.getImagen() != cas2.getImagen()) {
                cas1.setDescubierta(false);
                cas2.setDescubierta(false);
            }
            cas1 = null;
            cas2 = null;
        }
    }

}
