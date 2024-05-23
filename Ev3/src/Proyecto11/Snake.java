package Proyecto11;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class Snake extends Applet implements Runnable {

    Thread aniamcion;
    Image imagen;
    Graphics noseve;

    // Variables de la serpiente
    Image imgCasilla;
    Serpiente serpiente;

    ArrayList<Eslabon> eslabonesSueltos;
    Image imgManzana;

    private boolean juegoEnMarcha = true;

    public void init() {
        this.setSize(600, 350);
        imagen = createImage(600, 350);
        noseve = imagen.getGraphics();
        imgCasilla = getImage(getCodeBase(), "Proyecto11/img/casilla.png");
        serpiente = new Serpiente(imgCasilla);

        imgManzana = getImage(getCodeBase(), "Proyecto11/img/manzana.png");
        eslabonesSueltos = new ArrayList<Eslabon>();
        for (int i = 0; i < 5; i++) {
            eslabonesSueltos.add(
                    new Eslabon(imgManzana, (int) (Math.random() * 550), (int) (Math.random() * 300), Eslabon.PARADO));
        }

    }

    public void paint(Graphics g) {
        if (juegoEnMarcha) {    
            noseve.fillRect(0, 0, 600, 350);
            serpiente.paint(noseve, this);

            for (Eslabon eslabon : eslabonesSueltos) {
                eslabon.paint(noseve, this);
            }
        } else {
            noseve.setFont(new Font("Arial", Font.BOLD, 30));
            noseve.setColor(Color.RED);
            noseve.drawString("GAME OVER", this.getWidth() / 2 - 100, this.getHeight() / 2 - 50);
        }

        g.drawImage(imagen, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void start() {
        aniamcion = new Thread(this);
        aniamcion.start();
    }

    @Override
    public void run() {
        while (juegoEnMarcha) {
            serpiente.update();
            for (Eslabon eslabon : eslabonesSueltos) {
                if (eslabon.intersects(serpiente.primero())) {
                    serpiente.crecer(eslabon, imgCasilla);
                    eslabonesSueltos.remove(eslabon);
                    eslabonesSueltos.add(new Eslabon(imgManzana, (int) (Math.random() * 550),
                            (int) (Math.random() * 300), Eslabon.PARADO));
                    break;
                }
            }

            Eslabon cabeza = serpiente.primero();
            if (cabeza.x < 0 || cabeza.y < 0 || cabeza.x > this.getWidth() || cabeza.y > this.getHeight()) {
                juegoEnMarcha = false;
            }
            
            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean keyDown(Event ev, int tecla) {
        switch (tecla) {
            case Event.LEFT:
                serpiente.cambiarDireccion(Eslabon.IZQ);
                break;
            case Event.RIGHT:
                serpiente.cambiarDireccion(Eslabon.DER);
                break;
            case Event.UP:
                serpiente.cambiarDireccion(Eslabon.ARR);
                break;
            case Event.DOWN:
                serpiente.cambiarDireccion(Eslabon.ABA);
                break;
        }
        return true;
    }

}
