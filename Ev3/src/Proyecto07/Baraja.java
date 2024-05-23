package Proyecto07;

import java.awt.Image;
import java.util.ArrayList;
import static java.util.Collections.shuffle;

public class Baraja {
    ArrayList<Carta> cartas;

    public Baraja(Image imagenes[]) {
        cartas = new ArrayList<Carta>();
        for (int i = 0; i < imagenes.length; i++)
            cartas.add(new Carta(imagenes[i], (i % Solitario.CPP) + 1,
                    ((i / Solitario.CPP == 0) || (i / Solitario.CPP == 3)) ? Carta.NEGRO : Carta.ROJO, (i / Solitario.CPP)));
        shuffle(cartas);
    }

    public Carta sacarCarta() {
        return cartas.remove(0);

    }
}
