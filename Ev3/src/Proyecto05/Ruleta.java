package Proyecto05;

import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.applet.*;
import java.awt.event.ActionEvent;

public class Ruleta extends Applet {
    
    Casilla casillas[][];
    
    Image imagen;
    Graphics noseve;
    
    List<Ficha> fichasEstaticas = new ArrayList<Ficha>();
    
    List<Ficha> fichas = new ArrayList<Ficha>();
    
    Ficha actual;
    
    Historial historial;
    
    int totalApostado = 0;
    
    int numGenerado = 0;
    
    int gananciatotal = 0;
    
    Image imgficha[];
    
    @Override
    public void init() {
        this.setSize(800, 800);
        imagen = this.createImage(this.getWidth(), this.getHeight());
        noseve = imagen.getGraphics();
        
        casillas = new Casilla[13][3];
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                casillas[i][j] = new Casilla(i * 3 + j + 1, j, i);
            }
        }
        casillas[12][0] = new Casilla(0, 0, 0);
        
        imgficha = new Image[10];
        
        for (int i = 0; i < 10; i++) {
            imgficha[i] = this.getImage(getCodeBase(), "Proyecto05/img/ficha" + (i + 1) + ".png");
        }
        
        fichasEstaticas.add(new Ficha(1, 400, 250, imgficha));
        fichasEstaticas.add(new Ficha(5, 400, 325, imgficha));
        fichasEstaticas.add(new Ficha(10, 400, 400, imgficha));
        fichasEstaticas.add(new Ficha(25, 400, 475, imgficha));
        fichasEstaticas.add(new Ficha(50, 400, 555, imgficha));
        
        repaint();
        this.setLayout(null);
        Button boton = new Button("Girar la Ruleta");
        boton.setBounds(300, 50, 150, 35);
        this.add(boton);
        
        boton.addActionListener((ActionEvent e) -> {
            numGenerado = (int) (Math.random() * 37);
            comprobarNumeros();
            
            repaint();
            
        });
        
        historial = new Historial();
        
        repaint();
    }
    
    public void paint(Graphics g) {
        
        Font fontOriginal = noseve.getFont();
        noseve.drawImage(this.getImage(getCodeBase(), "Proyecto05/img/fondo.jpg"), 0, 0, 800, 800, this);
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                casillas[i][j].paint(noseve, this);
            }
        }
        casillas[12][0].paint(noseve, this);
        
        for (Ficha ficha : fichasEstaticas) {
            ficha.paint(noseve, this);
        }
        noseve.setColor(Color.BLACK);
        noseve.fillRect(165, 25, 75, 75);
        noseve.setColor(Color.WHITE);
        noseve.drawRect(165, 25, 75, 75);
        
        noseve.setColor(Color.white);
        noseve.setFont(new Font("SansSerif", Font.BOLD, 50));
        
        noseve.drawString("" + numGenerado, 165 + (75 - noseve.getFontMetrics().stringWidth("" + numGenerado)) / 2, 25 + (50 + noseve.getFontMetrics().getHeight()) / 2);
        
        noseve.setFont(fontOriginal);
        
        historial.paint(noseve);
        
        noseve.setColor(Color.WHITE);
        
        noseve.setFont(new Font("SansSerif", Font.BOLD, 20));
        noseve.drawString("Apostando: " + totalApostado, 550, 60);
        noseve.drawString("Ganancia Total: " + gananciatotal, 550, 100);
        
        noseve.setFont(fontOriginal);
        for (Ficha ficha : fichas) {
            ficha.paint(noseve, this);
        }
        
        g.drawImage(imagen, 0, 0, this);
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    public void comprobarNumeros() {
        int temp = 0;
        for (Ficha ficha : fichas) {
            temp += ficha.comprobar(numGenerado);
        }
        
        historial.addHistorial(numGenerado, temp - totalApostado);
        gananciatotal += temp - totalApostado;
    }
    
    @Override
    public boolean mouseDown(Event evt, int x, int y) {
        for (Ficha ficha : fichasEstaticas) {
            if (ficha.contains(x, y)) {
                actual = new Ficha(ficha.valor, x, y, imgficha);
                totalApostado += actual.valor;
                fichas.add(actual);
                repaint();
                break;
            }
        }
        for (Ficha ficha : fichas) {
            if (ficha.contains(x, y)) {
                actual = ficha;
                break;
            }
        }
        return true;
        
    }
    
    @Override
    public boolean mouseDrag(Event evt, int x, int y) {
        if (actual != null) {
            actual.update(x, y, casillas);
            repaint();
        }
        return true;
    }
    
    @Override
    public boolean mouseUp(Event evt, int x, int y) {
        if (actual != null) {
            if (actual.numChocando == 0) {
                fichas.remove(actual);
                totalApostado -= actual.valor;
                repaint();
            }
            actual = null;
        }
        return true;
    }
    
}
