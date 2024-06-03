package ExamenV2;

import java.awt.*;
import java.applet.Applet;

public class PersonajeV2 extends Rectangle {
    public static final int DIM = 60;
    public static final int VEL = 15;

    private boolean corriendoDer = false;
    private boolean corriendoIzq = false;
    private boolean muerto = false;

    int actual = 0;
    int imgMuerte = 0;

    Image muerte[];
    Image personajeDer[];
    Image personajeIzq[];
    Image parado;

    public PersonajeV2(Image[] muerte, Image[] personajeIzq, Image[] personajeDer, Image parado) {
        super(300, 550, DIM, DIM);
        this.muerte = muerte;
        this.parado = parado;
        this.personajeIzq = personajeIzq;
        this.personajeDer = personajeDer;
    }

    public boolean corriendoDer() {
        return corriendoDer;
    }

    public void corriendoDer(boolean corriendoDer) {
        this.corriendoDer = corriendoDer;
    }

    public boolean corriendoIzq() {
        return corriendoIzq;
    }

    public void corriendoIzq(boolean corriendoIzq) {
        this.corriendoIzq = corriendoIzq;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public void paint(Graphics g, Applet a) {
        if (corriendoDer) {
            g.drawImage(personajeDer[actual], x, y, DIM, DIM, a);
        } else if (corriendoIzq) {
            g.drawImage(personajeIzq[actual], x, y, DIM, DIM, a);
        } else if (muerto) {
            g.drawImage(muerte[imgMuerte], x, y, DIM, DIM, a);
        } else {
            g.drawImage(parado, x, y, width, height, a);
        }
    }

    public void update() {
        if (corriendoDer || corriendoIzq) {
            actual = (actual + 1) % personajeDer.length;
            x += (corriendoDer ? VEL : -VEL);
            muerto = false;
        } else if (muerto) {
            imgMuerte = (imgMuerte + 1) % muerte.length;
        }
    }
}
