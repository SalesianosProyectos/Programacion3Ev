package ExamenV2;

import java.awt.*;
import java.applet.Applet;

public class ExamenV2 extends Applet implements Runnable {

    public static final int TIEMPO = 120;
    public static final int NUM_NAVES = 10;
    public static final int NUM_GRANADAS = 10;
    public static final int CORRER = 8;

    Thread animacion;
    Image imagen;
    Graphics noseve;

    NaveV2 imgNave[];

    Image granada;

    NaveV2 granadas[];

    PersonajeV2 corredor;
    Image correrDerecha[];
    Image correrIzquierda[];
    Image muerte[];

    Image parado;

    public void init() {
        this.setSize(600, 600);
        imagen = this.createImage(600, 600);
        noseve = imagen.getGraphics();

        correrDerecha = new Image[CORRER];
        for (int i = 0; i < correrDerecha.length; i++) {
            correrDerecha[i] = getImage(getCodeBase(), "ExamenV2/Imagenes/2_Char_Run_00" + i + ".png");
        }

        correrIzquierda = new Image[CORRER];
        for (int i = 0; i < correrIzquierda.length; i++) {
            correrIzquierda[i] = getImage(getCodeBase(), "ExamenV2/Imagenes/2_Char_Left_00" + i + ".png");
        }

        granada = getImage(getCodeBase(), "ExamenV2/Imagenes/Grenade.png");

        granadas = new NaveV2[NUM_GRANADAS];
        for (int i = 0; i < granadas.length; i++) {
            granadas[i] = new NaveV2(granada, granada);
        }

        imgNave = new NaveV2[NUM_NAVES];
        for (int i = 0; i < imgNave.length; i++) {
            imgNave[i] = new NaveV2(getImage(getCodeBase(), "ExamenV2/Imagenes/Ship" + i + ".png"), granada);
        }

        parado = getImage(getCodeBase(), "ExamenV2/Imagenes/2_Char_Parado.png");

        muerte = new Image[10];
        for (int i = 0; i < muerte.length; i++) {
            muerte[i] = getImage(getCodeBase(), "ExamenV2/Imagenes/2_Char_Dead_00" + i + ".png");
        }

        corredor = new PersonajeV2(muerte, correrIzquierda, correrDerecha, parado);

    }

    public void start() {
        animacion = new Thread(this);
        animacion.start();
    }

    public void paint(Graphics g) {
        noseve.setColor(Color.WHITE);
        noseve.fillRect(0, 0, 600, 600);

        for (int i = 0; i < NUM_NAVES; i++) {
            imgNave[i].paint(noseve, this);
        }

        corredor.paint(noseve, this);

        g.drawImage(imagen, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        do {
            for (int i = 0; i < NUM_NAVES; i++) {
                imgNave[i].x += imgNave[i].vel;
                if (imgNave[i].x <= 1 || imgNave[i].x >= 599 - imgNave[i].width) {
                    imgNave[i].vel = -imgNave[i].vel;
                }
            }

            for (int i = 0; i < NUM_NAVES; i++) {
                imgNave[i].update(corredor);
            }

            corredor.update();

            repaint();
            try {
                Thread.sleep(TIEMPO);
            } catch (InterruptedException e) {
            }
        } while (true);
    }

    public boolean keyDown(Event ev, int tecla) {
        switch (tecla) {
            case 1006:
                corredor.corriendoIzq(true);
                break;
            case 1007:
                corredor.corriendoDer(true);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(Event ev, int tecla) {
        switch (tecla) {
            case 1006:
                corredor.corriendoIzq(false);
                break;
            case 1007:
                corredor.corriendoDer(false);
            default:
                break;
        }
        return true;
    }

}
