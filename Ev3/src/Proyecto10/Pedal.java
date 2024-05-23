package Proyecto10;

import java.applet.Applet;
import java.awt.*;

public class Pedal extends Rectangle {
    Image imagen;

    public Pedal(int x, int y, int width, int heinght, Image imagen) {
        super(x, y, width, heinght);
        this.imagen = imagen;
    }

    public void paint(Graphics nsv, Applet a) {
        nsv.drawImage(imagen, x, y, width, height, a);
    }

    public void acelerar() {
        Examen2023.velocidad -= 10;
    }

    public void frenar() {
        Examen2023.velocidad += 10;
    }
    }
}
