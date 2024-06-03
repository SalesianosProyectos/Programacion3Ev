package ExamenV2;

import java.awt.*;
import java.applet.Applet;
import java.util.ArrayList;

public class NaveV2 {

    public static final int DIM = 75;
    public static final int VEL = 10;
    int x, y, width, height, vel;
    Image imgNave;

    Image imgGranada;
    ArrayList<GranadaV2> granadas;

    public NaveV2(Image imgNave, Image imgGranada) {
        this.x = (int) (Math.random() * 600);
        this.y = (int) (Math.random() * 200);
        this.width = DIM;
        this.height = DIM;
        this.vel = VEL;
        this.imgNave = imgNave;
        this.imgGranada = imgGranada;

        granadas = new ArrayList<GranadaV2>();
    }

    public void paint(Graphics nsv, Applet a) {
        for (int i = 0; i < granadas.size(); i++) {
            granadas.get(i).paint(nsv, a);
        }
        nsv.drawImage(imgNave, x, y, width, height, a);
    }

    public void update(PersonajeV2 corredor) {
        lanzarGranada();

        for (int i = 0; i < granadas.size(); i++) {
            if (granadas.get(i).intersects(corredor)) {
                corredor.setMuerto(true);
            }
        }
    }

    public void lanzarGranada() {
        if (Math.random() <= 0.01) {
            granadas.add(new GranadaV2(imgGranada, x, y));
        }

        for (int i = 0; i < granadas.size(); i++) {
            granadas.get(i).update();
        }
    }
}
