package Proyecto02;

import java.applet.Applet;
import java.awt.*;

public class Puzzle extends Applet {

    public static final int PIEZAS = 25;
    public static final int TIEMPO = 200;
    public static final int TABLAS = 5;

    
    Graphics noseve;
    Thread animacion;
    Image imagen;
    Image imagenes[];
    Pieza piezas[];
    Pieza actual;
    Rectangle tablero[][];

    public void init() {
        this.setSize(700, 500);
        imagen = this.createImage(700, 500);
        noseve = imagen.getGraphics();

        imagenes = new Image[PIEZAS];
        piezas = new Pieza[PIEZAS];
        for (int i = 0; i < PIEZAS; i++) {
            imagenes[i] = getImage(getCodeBase(), "Proyecto02/Imagenes/" + i + ".png");
            piezas[i] = new Pieza(imagenes[i], i);
        }

        tablero = new Rectangle[TABLAS][TABLAS];
        for (int i = 0; i < TABLAS; i++) {
            for (int j = 0; j < TABLAS; j++) {
                tablero[i][j] = new Rectangle(i * Pieza.DIMENSION + 50, j * Pieza.DIMENSION + 85, Pieza.DIMENSION,
                        Pieza.DIMENSION);
            }
        }

    }

    public void paint(Graphics g) {
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 700, 500);
        noseve.setColor(Color.WHITE);

        for (int i = 0; i < PIEZAS; i++) {
            piezas[i].paint(noseve, this);
        }

        for (int i = 0; i < TABLAS; i++) {
            for (int j = 0; j < TABLAS; j++) {
                noseve.drawRect(tablero[i][j].x, tablero[i][j].y, tablero[i][j].width, tablero[i][j].height);
            }
        }

        g.drawImage(imagen, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public boolean mouseDown(Event ev, int x, int y) {

        for (int i = 0; i < PIEZAS; i++) {
            if (piezas[i].contains(x, y)) {
                actual = piezas[i];
            }
        }
        return true;
    }

    public boolean mouseDrag(Event ev, int x, int y) {
        if (actual != null) {
            actual.update(x, y);
            repaint();
        }
        return true;
    }
    

    public boolean mouseUp(Event ev, int x, int y) {
    if (actual != null) { // Verificar si actual no es nulo
        for (int i = 0; i < TABLAS; i++) {
            for (int j = 0; j < TABLAS; j++) {
                if (tablero[i][j].intersects(actual)) {
                    if (actual.posicion == (i * TABLAS) + j) {
                        actual.moverPosicion(tablero[i][j].x, tablero[i][j].y);
                        actual.setColocada(true);
                    }
                }
            }
        }
        repaint();
    }
    actual = null; // Resetear actual después de usarlo
    return true;
}


}
