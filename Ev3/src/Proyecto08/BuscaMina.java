package Proyecto08;

import java.awt.*;
import java.applet.Applet;
import java.util.ArrayList;

public class BuscaMina extends Applet {
    public static final int DIM = 10;

    Image mina;
    Image imgReverso;
    Casilla casillas[][];

    public void init() {
        imgReverso = getImage(getCodeBase(), "Proyecto08/Img/casilla.png");
        mina = getImage(getCodeBase(), "Proyecto08/Img/mina.png");

        /* Instanciamos un array bidimensional de casillas */
        casillas = new Casilla[DIM][DIM];
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                casillas[i][j] = new Casilla(100 + (i * Casilla.TAM), 100 + (j * Casilla.TAM), imgReverso);
            }
        }

        int resultados[] = obtenerAleatorios();

        /* Colocamos las minas */
        for (int i = 0; i < DIM; i++) {
            casillas[resultados[i] / DIM][resultados[i] % DIM].setMina(mina);
        }

        /* Contamos las bombas */
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                casillas[i][j].setAlrededor(contarBombas(i, j));

            }
        }

    }

    /* Obtener numeros bombas */
    public int contarBombas(int fila, int columna) {
        int contador = 0;
        int inicioX = (fila == 0) ? 0 : fila - 1;
        int finX = (fila == DIM - 1) ? DIM - 1 : fila + 1;
        int inicioY = (columna == 0) ? 0 : columna - 1;
        int finY = (columna == DIM - 1) ? DIM - 1 : columna + 1;

        for (int i = inicioX; i <= finX; i++) {
            for (int j = inicioY; j <= finY; j++) {
                if (casillas[i][j].getMina() != null) {
                    contador++;
                }
            }
        }

        return contador;
    }

    /* Obtener numeros aleatorios */
    public int[] obtenerAleatorios() {
        int vector[] = new int[DIM * DIM];
        int resultados[] = new int[DIM];

        /* Inicializamos el vector */
        for (int i = 0; i < DIM * DIM; i++) {
            vector[i] = i;
        }

        /* Barajamos el vector */
        for (int i = 0; i < DIM; i++) {
            int aleatorio = (int) (Math.random() * (DIM * DIM - i));
            resultados[i] = vector[aleatorio];
            vector[aleatorio] = vector[DIM * DIM - i - 1];
        }
        return resultados;
    }

    public void paint(Graphics g) {
        /* Pintamos las casillas */
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                casillas[i][j].paint(g, this);
            }
        }
    }

    public void update(Graphics g) {

        paint(g);
    }

    public boolean mouseDown(Event evt, int x, int y) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (casillas[i][j].contains(x, y)) {
                    casillas[i][j].destapar();

                }
            }
        }
        repaint();
        return true;
    }
}
