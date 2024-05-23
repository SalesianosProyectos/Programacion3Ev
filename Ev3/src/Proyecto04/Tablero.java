package Proyecto04;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Tablero extends Applet {
    public static final int TAM = 5;
    Image imagenes[][];
    AudioClip error, acierto, exito;
    Lugar lugares[][];
    Point hueco;
    Button boton;
    
    @Override
    public void init() {

            try {
                error = getAudioClip(new URL(getCodeBase(), "Proyecto04/sonidos/error.wav"));
                acierto = getAudioClip(new URL(getCodeBase(), "Proyecto04/sonidos/correct.mp3"));
                exito = getAudioClip(new URL(getCodeBase(), "Proyecto04/sonidos/exito.wav"));
            } catch (MalformedURLException ex) {
            }
    
            lugares = new Lugar[TAM][TAM];
            imagenes = new Image[TAM][TAM];
            for (int i = 0; i < TAM; i++)
                for (int j = 0; j < TAM; j++) {
                    imagenes[i][j] = getImage(getCodeBase(), "Proyecto04/botones/" + (i * TAM + j + 1) + ".gif");
                    lugares[i][j] = new Lugar(imagenes[i][j], (i * TAM) + j);
                }
            hueco = new Point(TAM - 1, TAM - 1);

        Panel panel = new Panel();
        boton = new Button("Empezar");
        panel.add(boton);
        this.setLayout(new BorderLayout());
        this.add("south", panel);
    }

    @Override
    public void paint(Graphics g) {

        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++)
                lugares[i][j].paint(g, this, j * Lugar.DIM, i * Lugar.DIM);
    }

    public boolean mover(Point click) {
        Point desplazamiento, hasta;
        desplazamiento = new Point(delta(click.x, hueco.x), delta(click.y, hueco.y));
        if (desplazamiento.x == 0 && desplazamiento.y == 0)
            return false;
        if (desplazamiento.x != 0 && desplazamiento.y != 0)
            return false;

        hasta = new Point(click.x + desplazamiento.x, click.y + desplazamiento.y);
        if (hasta.x == hueco.x && hasta.y == hueco.y) {
            lugares[hueco.x][hueco.y].imagen = lugares[click.x][click.y].imagen;
            lugares[click.x][click.y].imagen = null;

            lugares[hueco.x][hueco.y].valor = lugares[click.x][click.y].valor;
            lugares[click.x][click.y].valor = 24;

            hueco = click;

            return true;
        }

        return false;
    }

    public int delta(int a, int b) {
        if (a == b)
            return 0;
        else
            return ((b - a) / Math.abs(b - a));

    }

    public boolean mouseDown(Event evt, int x, int y) {
        Point click;
        click = new Point(y / Lugar.DIM, x / Lugar.DIM);
        if (mover(click)) {
            acierto.play();
            comprobar();
            repaint();

        } else {
            error.play();

        }

        return true;
    }

    private boolean comprobar() {
        boolean correcto = true;

        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++)
                if (lugares[i][j].valor != (i * TAM) + j)
                    correcto = false;
        if (correcto)
            exito.play();
        return correcto;
    }

    @Override
    public boolean action(Event evt, Object obj) {
        if (evt.target instanceof Button) {
            for (int i = 0; i < 200; i++) {
                mover(new Point((int) (Math.random() * TAM), (int) (Math.random() * TAM)));
                repaint();
            }
        }
        return false;
    }
}
