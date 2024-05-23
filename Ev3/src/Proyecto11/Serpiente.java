package Proyecto11;

import java.awt.*;
import java.applet.*;
import java.util.ArrayList;

public class Serpiente {
    ArrayList<Eslabon> eslabones;

    public Serpiente(Image img) {
        eslabones = new ArrayList<Eslabon>();
        eslabones.add(new Eslabon(img, 200, 100, Eslabon.DER));
        eslabones.add(new Eslabon(img, 200 - Eslabon.SIZE, 100, Eslabon.DER));
        eslabones.add(new Eslabon(img, 200 - (Eslabon.SIZE * 2), 100, Eslabon.DER));
        eslabones.add(new Eslabon(img, 200 - (Eslabon.SIZE * 3), 100, Eslabon.DER));
        eslabones.add(new Eslabon(img, 200 - (Eslabon.SIZE * 4), 100, Eslabon.DER));

    }

    public void paint(Graphics nsv, Applet a) {
        for (Eslabon eslabon : eslabones) {
            eslabon.paint(nsv, a);
        }
    }

    public void update() {
        for (Eslabon eslabon : eslabones) {
            eslabon.update();
        }
        for (int i = eslabones.size() - 1; i > 0; i--) {
            eslabones.get(i).setDireccion(eslabones.get(i - 1).getDireccion());
        }
    }

    public void cambiarDireccion(int direccion) {
        eslabones.get(0).setDireccion(direccion);
    }

    public Eslabon primero() {
        return eslabones.get(0);
    }

    public Eslabon ultimo() {
        return eslabones.get(eslabones.size() - 1);
    }

    public void crecer(Eslabon esl, Image imgCasilla) {
        esl.setImagen(imgCasilla);
        esl.setDireccion(ultimo().getDireccion());

        switch (esl.getDireccion()) {
            case Eslabon.IZQ:
                esl.x = ultimo().x + Eslabon.SIZE;
                esl.y = ultimo().y;
                break;
            case Eslabon.DER:
                esl.x = ultimo().x - Eslabon.SIZE;
                esl.y = ultimo().y;
                break;
            case Eslabon.ARR:
                esl.x = ultimo().x;
                esl.y = ultimo().y + Eslabon.SIZE;
                break;
            case Eslabon.ABA:
                esl.x = ultimo().x;
                esl.y = ultimo().y - Eslabon.SIZE;
                break;
        }
        eslabones.add(esl);
    }
}
