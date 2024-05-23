package Proyecto01;

import java.applet.Applet;
import java.awt.*;

import java.util.ArrayList;

public class Caminando extends Applet implements Runnable {
    public static final int TIEMPO = 200;
    Thread animacion;
    Image imagen;
    Graphics noseve;
    String rutas[] = { "Guerrillero/g", "Hampon/h", "Vaquero/v" };

    Image dibujos[][];
    DibujoAnimado dAnimado;

    ArrayList<DibujoAnimado> dAnimados;

    @Override
    public void init() {
        this.setSize(300, 300);
        imagen = this.createImage(300, 300);
        noseve = imagen.getGraphics();

        
        dibujos = new Image[3][4];
        for (int i = 0; i < dibujos.length; i++)
        for (int j = 0; j < dibujos[i].length; j++)
        dibujos[i][j] = getImage(getCodeBase(), "Proyecto01/Sprites/" + rutas[i] + (j + 1) +
        ".gif"); 

        dAnimados = new ArrayList<DibujoAnimado>();
        
        for(int i = 0; i<10; i++)
        dAnimados.add(new DibujoAnimado(dibujos[0]));
    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void paint(Graphics g) {
        noseve.setColor(Color.BLACK);
        noseve.fillRect(0, 0, 300, 300);

        for (DibujoAnimado da : dAnimados)
        da.paint(noseve, this);

        g.drawImage(imagen, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void run() {
        do {
            for (DibujoAnimado da : dAnimados)
            da.update();
            repaint();
            try {
                Thread.sleep(TIEMPO);
            } catch (InterruptedException e) {
            }
        } while (true);
    }

    public boolean keyDown(Event e, int key) {
        
        switch (key) {
            case 103:
            case 71:
            for (DibujoAnimado da : dAnimados)
                da.setImagenes(dibujos[0]); // Key G
                break;
            case 104:
            case 72:
            for (DibujoAnimado da : dAnimados)
                da.setImagenes(dibujos[1]); // Key H
                break;
            case 118:
            case 86:
            for (DibujoAnimado da : dAnimados)
                da.setImagenes(dibujos[2]); // Key V
                break;
        }
        return true;
    }

    public boolean mouseDown(Event e, int x, int y) {
        /*
         * Añade un nuevo dibujo al hacer click
        dAnimados.add(new DibujoAnimado(dibujos[0]));
        return true;
        */

        /*
         * Elimina el dibujo al hacer click
         */

        for (DibujoAnimado da : dAnimados)
        if (da.contains(x, y)) {
            dAnimados.remove(da);
            break;
        }
        return true;
    }
}
