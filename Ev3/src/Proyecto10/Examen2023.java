package Proyecto10;

import java.awt.*;
import java.applet.Applet;

public class Examen2023 extends Applet implements Runnable {
    public static final int velocidad = 100;
    public static final int NUM_IMG = 5;
    public static final int NUM_RUEDAS = 2;
    public static final int TIEMPO = 100;

    Graphics noseve;
    Thread animacion;
    Image imagen;

    Image imgFondo;
    Image imgRuedas[];

    Fondo fondo;
    Rueda ruedas[];

    Image imgCoche;
    Coche coche;

    Image imgFaro;
    Coche faro;

    Pedal pedal1, pedal2;

    public void init() {
        this.setSize(600, 350);
        imagen = createImage(600, 350);

        noseve = imagen.getGraphics();

        imgFondo = getImage(getCodeBase(), "Proyecto10/imgsCoche/fondo.jpg");
        fondo = new Fondo(imgFondo, 0, this);

        imgRuedas = new Image[NUM_IMG];
        for (int i = 0; i < NUM_IMG; i++) {
            imgRuedas[i] = getImage(getCodeBase(), "Proyecto10/imgRuedas/rueda" + (i + 1) + ".gif");
        }

        ruedas = new Rueda[NUM_RUEDAS];
        for (int i = 0; i < NUM_RUEDAS; i++) {
            ruedas[i] = new Rueda(imgRuedas, 35 + (i * 60), 290);
        }

        imgCoche = getImage(getCodeBase(), "Proyecto10/imgsCoche/mercedes.png");
        coche = new Coche(300, 250, imgCoche, faro);

        imgFaro = getImage(getCodeBase(), "Proyecto10/imgCoche/faro.png");
        faro = new Faro(100, 300, imgFaro);
        coche = new Coche(300, 300, imgCoche, faro);

        imgPedal1 = getImage(getCodeBase(), "Proyecto10/imgCoche/pedal1.png");
        imgPedal2 = getImage(getCodeBase(), "Proyecto10/imgCoche/pedal2.png");
        pedal1 = new Pedal(100, 300, 50, 50, imgPedal1);
        pedal2 = new Pedal(200, 300, 50, 50, imgPedal2);
    }

    public void paint(Graphics g) {
        noseve.fillRect(0, 0, 600, 350);
        fondo.paint(noseve);
        
        coche.paint(noseve, this);
        
        for (int i = 0; i < NUM_RUEDAS; i++) {
            ruedas[i].paint(noseve, this);
        }

        pedal1.paint(noseve, this);
        pedal2.paint(noseve, this);

        faro.paint(noseve, this);


        g.drawImage(imagen, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);

    }

    public boolean mouseDown(Event evt, int x, int y) {
        if(coche.contains(x,y))
        coche.turn();

        if (pedal1.contains(x,y))
            pedal1.acelerar();
        if (pedal2.contains(x,y)) 
            pedal2.frenar(); 
            repaint();
        return true;
    }
    

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void run() {
        while (true) {
            fondo.update(noseve);

            for (int i = 0; i < NUM_RUEDAS; i++) {
                ruedas[i].update();
            }

            repaint();
            try {
                Thread.sleep(TIEMPO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
