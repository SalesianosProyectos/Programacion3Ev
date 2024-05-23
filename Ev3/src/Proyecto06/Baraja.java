package Proyecto06;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

public class Baraja extends Rectangle {
    private ArrayList<Carta> cartas;
    private int cartaActual;

    public Baraja(Image[] imagenes) {
        cartas = new ArrayList<>();
        cartaActual = 0;

        // Create the deck of cards
        for (int i = 0; i < imagenes.length; i++) {
            for (int j = 1; j <= 13; j++) {
                Carta carta = new Carta(imagenes[i], j);
                cartas.add(carta);
            }
        }

        // Shuffle the deck
        Collections.shuffle(cartas);
    }

    public Carta sacarCarta() {
        if (cartaActual < cartas.size()) {
            Carta carta = cartas.get(cartaActual);
            cartaActual++;
            return carta;
        } else {
            // If all cards have been dealt, return null
            return null;
        }
    }
}
