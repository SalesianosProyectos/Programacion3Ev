package Proyecto07;

import java.applet.Applet;
import java.awt.*;

public class Solitario extends Applet {
    public static final int NUM_PALOS = 4;
    public static final int NUMCARTAS = 52;
    public static final int CPP = 13;
    public static final int NUMJUEGOS = 7;

    Graphics noseve;
    Image imagen;

    String nombres[] = { "_of_clubs.png", "_of_diamonds.png", "_of_hearts.png", "_of_spades.png" };
    Image imgReverso;
    Image imagenes[];
    Rectangle reverso;
    Baraja baraja;
    MazoSecundario mSecundario;
    Carta activa;
    MazoPalo mPalos[];
    MazoJuego mJuegos[];

    public void init() {
        imagen = createImage(900, 700);
        noseve = imagen.getGraphics();

        imagenes = new Image[NUMCARTAS];

        for (int i = 0; i < nombres.length; i++)
            for (int j = 0; j < CPP; j++) {
                imagenes[(i * CPP) + j] = getImage(getCodeBase(), "Proyecto07/Cartas/" + (j + 1) + nombres[i]);

            }

        imgReverso = getImage(getCodeBase(), "Proyecto07/Cartas/reverso.png");
        reverso = new Rectangle(20, 20, Carta.ANCHO, Carta.ALTO);
        baraja = new Baraja(imagenes);
        mSecundario = new MazoSecundario();

        mPalos = new MazoPalo[NUM_PALOS];
        for (int i = 0; i < NUM_PALOS; i++) {
            mPalos[i] = new MazoPalo((i * 100) + 400);
        }

        mJuegos = new MazoJuego[NUMJUEGOS];
        for (int i = 0; i < NUMJUEGOS; i++) {
            mJuegos[i] = new MazoJuego(100 + (i * 100));
        }
    } 

    public void paint(Graphics g) {
        noseve.setColor(Color.GREEN);
        noseve.fillRect(0, 0, 900, 800);

        noseve.drawImage(imgReverso, reverso.x, reverso.y, reverso.width, reverso.height, this);
        mSecundario.paint(noseve, this);

        for (int i = 0; i < NUM_PALOS; i++) {
            mPalos[i].paint(noseve, this);
        }

        for (int i = 0; i < NUMJUEGOS; i++)
            mJuegos[i].paint(noseve, this);

        mSecundario.paint(noseve, this);

        g.drawImage(imagen, CPP, CPP, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public boolean mouseDown(Event ev, int x, int y) {
        if (reverso.contains(x, y)) {
            mSecundario.anadir(baraja.sacarCarta());
            repaint();
            return true;
        }
        if (mSecundario.ultimaCarta().contains(x, y))
            activa = mSecundario.ultimaCarta();

        for (int i = 0; i < NUMJUEGOS; i++) {
            if (mJuegos[i].ultimaCarta().contains(x, y)) {
                activa = mJuegos[i].sacarCarta();
                mJuegos[i].recolocar();
                repaint();
            }
        }

        return true;
    }

    public boolean mouseDrag(Event ev, int x, int y) {
        if (activa != null) {
            activa.x = x - (Carta.ANCHO / 2);
            activa.y = y - (Carta.ALTO / 2);
            repaint();
        }

        return true;
    }

    public boolean mouseUp(Event ev, int x, int y) {
        if (activa != null) {
            for (int i = 0; i < NUM_PALOS; i++) {
                if (mPalos[i].intersects(activa) && mPalos[i].anadir(activa)) {
                    mSecundario.sacarCarta();
                    mPalos[i].recolocar();
                    break;
                }
            }

            for (int i = 0; i < NUMJUEGOS; i++) {
                if (mJuegos[i].intersects(activa) && ((mJuegos[i].mazo.isEmpty()) || mJuegos[i].anadir(activa))) {
                    mSecundario.sacarCarta();
                    mJuegos[i].recolocar();
                    break;
                }
            }

            activa = null;
            mSecundario.recolocar();
            for (int i = 0; i < NUMJUEGOS; i++)
                if (!mJuegos[i].mazo.isEmpty())
                    mJuegos[i].recolocar();
            repaint();
        }
        return true;
    }

}
