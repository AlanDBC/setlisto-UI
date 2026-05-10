package com.setlisto.ui.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.setlisto.ui.controller.ZonaConfigurada;

/**
 * Tendra una lista de ZonaConfigurada, sabra pintarlas y contendra los rectangulos (Zonas) temporales 
 */

public class PlazasMapaPanel extends AbstractView {

	private List<ZonaConfigurada> zonas = new ArrayList<>();
    private Rectangle rectanguloTemporal;
    private BufferedImage backgroundImage; // Imagen de plano
    private ZonaConfigurada zonaSeleccionada; // Feedback visual

    

    public void agregarZona(ZonaConfigurada zona) {
        zonas.add(zona);
        rectanguloTemporal = null;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Dibujar Imagen
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }

        // Dibujar zonas definitivas
        for (ZonaConfigurada zona : zonas) {
            Rectangle r = zona.getArea();
            
            if (zona.equals(zonaSeleccionada)) {
                g2.setColor(new Color(0, 255, 0, 100)); // Verde semi-transparente
                g2.fillRect(r.x, r.y, r.width, r.height);
                g2.setColor(Color.GREEN);
            } else {
                g2.setColor(new Color(255, 0, 0, 100)); // Rojo semi-transparente
                g2.fillRect(r.x, r.y, r.width, r.height);
                g2.setColor(Color.RED);
            }
            
            g2.drawRect(r.x, r.y, r.width, r.height);
            g2.setColor(Color.WHITE); // Texto visible
            g2.drawString(zona.getSeccion(), r.x + 5, r.y + 15);
        }

        // Dibujar rectángulo temporal
        if (rectanguloTemporal != null) {
            g2.setColor(Color.BLUE);
            g2.drawRect(rectanguloTemporal.x, rectanguloTemporal.y, rectanguloTemporal.width, rectanguloTemporal.height);
        }
    }

	public List<ZonaConfigurada> getZonas() {
		return zonas;
	}

	public void setRectanguloTemporal(Rectangle rect) {
        this.rectanguloTemporal = rect;
        repaint();
    }
	
	public Rectangle getRectanguloTemporal() {
		return rectanguloTemporal;
	}

	public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	public ZonaConfigurada getZonaSeleccionada() {
		return zonaSeleccionada;
	}

	public void setZonas(List<ZonaConfigurada> zonas) {
		this.zonas = zonas;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
		repaint();
	}

	public void setZonaSeleccionada(ZonaConfigurada zonaSeleccionada) {
		this.zonaSeleccionada = zonaSeleccionada;
		repaint();
	}
}
