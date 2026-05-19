package com.setlisto.ui.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.setlisto.ui.view.PlazasConfigView;
/**
 * Controlador para la vista de configuración de asientos. Permite al usuario dibujar zonas en el 
 * plano del evento para configurar la ubicación y cantidad de asientos.
 */

public class PlazasConfigController extends AbstractController implements MouseListener, MouseMotionListener {
	private Point startPoint;
	private PlazasConfigView view;
	private ZonaConfigurada zonaActual; // Para mantener referencia a la zona que se está editando
	private int contador = 0; // Para nombrar secciones automáticamente

	public PlazasConfigController(PlazasConfigView view) {
		this.view = view;
	}

	@Override
	public void doAction() {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// [Inicio del Rectángulo] Captura la coordenada inicial (X, Y)
		startPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	    Point endPoint = e.getPoint();

	    int x = Math.min(startPoint.x, endPoint.x);
	    int y = Math.min(startPoint.y, endPoint.y);

	    int width = Math.abs(startPoint.x - endPoint.x);
	    int height = Math.abs(startPoint.y - endPoint.y);

	    if (width > 5 && height > 5) {

	        Rectangle area = new Rectangle(x, y, width, height);

	        ZonaConfigurada zona = new ZonaConfigurada();

	        zona.setArea(area);

			zona.setSeccion(
	            "Sección " + (contador++)
	        );

	        view.getMapaPanel().agregarZona(zona);

	        zonaActual = zona;

	        // activar panel derecho
	        view.setRightPanelActive(true);
	        view.setZonaActual(zona); // manda la zona recien creada al panel derecho para que se pueda configurar su cantidad y categoria
	    }
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	    Point endPoint = e.getPoint();

	    int x = Math.min(startPoint.x, endPoint.x);
	    int y = Math.min(startPoint.y, endPoint.y);

	    int width = Math.abs(startPoint.x - endPoint.x);
	    int height = Math.abs(startPoint.y - endPoint.y);

	    Rectangle preview = new Rectangle(x, y, width, height);

	    view.getMapaPanel().setRectanguloTemporal(preview);
	}
	
	public void setZonaActual(ZonaConfigurada zona) {
		this.zonaActual = zona; //Esta zona ya viene con los datos cargados desde el panel derecho (categoria y cantidad), solo falta agregarla a la lista de zonas del mapa
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// Si hacemos clic en una zona, la seleccionamos
	    for (ZonaConfigurada zona : view.getMapaPanel().getZonas()) {
	        if (zona.getArea().contains(e.getPoint())) {
	            view.setZonaActual(zona);
	            return;
	        }
	    }
	    // Si hace clic en un espacio vacío, deseleccionamos
	    view.setZonaActual(null);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	

}
