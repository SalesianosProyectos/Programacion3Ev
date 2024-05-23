package Proyecto03;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Robin extends Applet implements Runnable {

    public static final int TIEMPO = 200;

    Graphics noseve;
    Image imagen;
    Image arquero, imgFlecha;
    Arquero player;
    Thread animacion;
    Image imgGlobos[];

    ArrayList<Flecha> flechas;
    ArrayList<Globo> globos;
    int acumTiempo = 0;

    public void init() {

        this.setSize(700, 500);
        imagen = this.createImage(700, 500);
        noseve = imagen.getGraphics();
    
        arquero = getImage(getDocumentBase(), "Imgs/arquero.gif");
        imgFlecha = getImage(getDocumentBase(), "Imgs/flecha.gif");
        imgGlobos = new Image[3];
        for (int i = 0; i < imgGlobos.length; i++)
            imgGlobos[i] = getImage(getDocumentBase(), "Proyecto03/Imgs/globo" + (i + 1) + ".gif");
        player = new Arquero(arquero);
        flechas = new ArrayList<Flecha>();
        globos = new ArrayList<Globo>();
        

    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void paint(Graphics g) {
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 700, 500);
    
        player.paint(noseve, this);
    
        for (Flecha flecha : flechas) {
            flecha.paint(noseve, this);
        }
    
        for (Globo globo : globos) {
            globo.paint(noseve, this);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        while (Thread.currentThread() == animacion) {
            actualizarFlecha();
            incorporarGlobo();
            repaint();

            try {
                globos.removeIf(Globo::update);
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }

            for (Flecha flecha : flechas) {
                globos.removeIf(globo -> globo.contains(flecha));
                repaint();
                try {
                    Thread.sleep(TIEMPO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void incorporarGlobo() {
        acumTiempo += TIEMPO;
        if (acumTiempo >= 1500) {
            globos.add(new Globo(imgGlobos[(int) (Math.random() * 3)]));
            acumTiempo = 0;
        }
    }

    public void actualizarFlecha() {
        for (Flecha flecha : flechas) {
            flecha.update();

            if ((!flechas.isEmpty()) && (flechas.get(0).x >= 1000)) {
                flechas.remove(0);
            }
        }
    }

    public boolean mouseDown(Event evt, int x, int y) {
        flechas.add(new Flecha(imgFlecha, player.getY()));
        return true;
    }

    public boolean mouseMove(Event evt, int x, int y) {
        player.setY(y);
        repaint();
        return true;
    }

}
