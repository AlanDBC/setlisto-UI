package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.model.LugarDTO;
import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

/**
 * Desde esta clase se cogera el lugar seleccionado en el dialogo (LugarSelectView) y se pasara al label del formulario en EventoCreateView
 * y a su atributo lugarSeleccionado para que se pueda usar posteriormente en el proceso de creación del evento.
 */

public class AceptarLugarSeleccionadoController extends AbstractController implements ActionListener {

	private LugarSelectView view;
	private EventoCreateView receptor;

	public AceptarLugarSeleccionadoController(LugarSelectView view, EventoCreateView receptor) {
		super("Aceptar", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1710_ok_yes_accept_green_ok_green_accept_yes.png")));
		this.view = view;
		this.receptor = receptor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	@Override
	public void doAction() {
		LugarDTO lugarSeleccionado = view.getLugarSeleccionado();

		if (lugarSeleccionado != null) {

			receptor.setLugarSeleccionado(lugarSeleccionado);
			view.dispose(); 

		} else {
			JOptionPane.showMessageDialog(view, 
					"Por favor, selecciona un lugar de la tabla antes de continuar.", 
					"Ningún lugar seleccionado", 
					JOptionPane.WARNING_MESSAGE);
		}
	}
}