package Proyecto06;

import java.applet.Applet;
import java.awt.*;

public class Blackjack extends Applet {

    public static final int NUMCARTAS = 52;
    public static final int CPP = 13;
    String nombres[] = { "_of_clubs", "_of_diamonds", "_of_hearts", "_of_spades" };
    Graphics noseve;
    Image imagenes[];
    Baraja baraja;
    Carta carta;
    Mano crouppier, jugador;
    Button boton1, boton2;
    TextField apuesta;

    public void init() {
        imagenes = new Image[NUMCARTAS];
        for (int i = 0; i < nombres.length; i++)
            for (int j = 0; j < CPP; j++)
                imagenes[i * CPP + j] = getImage(getCodeBase(), "Proyecto06/Cartas/" + (j + 1) + nombres[i] + ".png");
        baraja = new Baraja(imagenes);

        crouppier = new Mano(50);
        jugador = new Mano(400);

        insertarObjetos();

    }

    public void insertarObjetos() throws HeadlessException {
        Panel panel1 = new Panel();
        boton1 = new Button("Pedir!");
        boton2 = new Button("Plantarse!");
        panel1.add(boton1);
        panel1.add(boton2);
        this.setLayout(new BorderLayout());
        this.add("South", panel1);

        Panel panel2 = new Panel();
        apuesta = new TextField("", 10);
        panel2.add(apuesta);
        this.add("North", panel2);
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 700, 800);

        crouppier.paint(g, this);
        jugador.paint(g, this);

    }

    public void update(Graphics g) {
        paint(g);

    }

    public boolean action(Event ev, Object obj) {
        if (ev.target instanceof TextField) {
            apuesta.setEditable(false);

            // Dar dos cartas al crupier y al jugador
            crouppier.anadirCarta(baraja.sacarCarta());
            jugador.anadirCarta(baraja.sacarCarta());
            jugador.anadirCarta(baraja.sacarCarta());
            repaint();
        } else if (ev.target instanceof Button) {
            if (ev.arg.equals("Pedir!")) {
                jugador.anadirCarta(baraja.sacarCarta());
                if (jugador.tePasaste()) {
                    this.juegaCrouppier();
                }
                repaint();
            } else if (ev.arg.equals("Plantarse!")) {
                this.juegaCrouppier();
                repaint();
            }
        }

        return true;
    }

    public void juegaCrouppier() {
        while (crouppier.noAlcanza()) {
            crouppier.anadirCarta(baraja.sacarCarta());
            repaint();

        }
    }
}
