package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

/**
 * Controlador para cancelar la selección. 
 * Asegura que no se pase ningún dato al receptor y cierra la ventana.
 */
public class CancelarLugarSeleccionadoController extends AbstractController implements ActionListener {

	private LugarSelectView view;
	private EventoCreateView receptor;

	public CancelarLugarSeleccionadoController(LugarSelectView view, EventoCreateView receptor) {
		// icon: 1250_delete_delete.png
		super("Cancelar", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1250_delete_delete.png")));
		this.view = view;
		this.receptor = receptor;
	}
	
	@Override
	public void doAction() {
		receptor.setLugarSeleccionado(null);
		view.setLugarSeleccionado(null);
		view.dispose(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	
}