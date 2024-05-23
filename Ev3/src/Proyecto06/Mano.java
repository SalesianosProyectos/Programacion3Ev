package Proyecto06;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class Mano {
    ArrayList<Carta> cartas;
    int posY;

    public Mano(int y) {
        cartas = new ArrayList<Carta>();
        this.posY = y;
    }

    public void anadirCarta(Carta carta) {
        cartas.add(carta);
    }

    public void paint(Graphics g, Applet a) {
        for (int i = 0; i < cartas.size(); i++) {
            cartas.get(i).setPosY(posY);
            cartas.get(i).setPosX((i * Carta.ANCHO) + 150);
            cartas.get(i).paint(g, a);
        }
    }

    public int puntuacion() {
        int puntuacion = 0;
        boolean as = false;
        for (Carta carta : cartas) {
            puntuacion += carta.getValor();
            if (carta.getValor() == 1) {
                as = true;
            }
        }
        
        if (as && (puntuacion <= 11)) 
            puntuacion += 10;
        
        return puntuacion;
    }

    public boolean tePasaste() {
        
        for (Carta carta : cartas) {
            if (carta.getValor() == 1) {
                return puntuacion() > 21;
            }
        }
        
        return puntuacion() > 21;
    }

    public boolean noAlcanza() {
        return puntuacion() < 17;
    }

    public boolean blackjack() {
        return puntuacion() == 21 && cartas.size() == 2;
    }

    public void limpiar() {
        cartas.clear();
    }

    

}
