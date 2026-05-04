package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.setlisto.model.Lugar;
import com.setlisto.service.LugarService;
import com.setlisto.service.impl.LugarServiceImpl;
import com.setlisto.ui.view.LugarSelectView;

public class LugarCreateController extends AbstractController {

	public LugarSelectView view = null;
	public LugarService service = null;

	public LugarCreateController(LugarSelectView view) {
		this.view = view;
		service = new LugarServiceImpl();
	}

	@Override
	public void doAction() {
		Lugar lgr = view.getLugar();
		Lugar creado = service.create(lgr);

		if (creado != null) {

			// Mostramos el mensaje de éxito
			JOptionPane.showMessageDialog(
					view, 
					"¡LugaR '" + creado.getNombre() + "' creado con éxito!", 
					"Operación Exitosa", 
					JOptionPane.INFORMATION_MESSAGE
					);

			// Limpiamos los campos mientras el usuario cierra el diálogo
			view.limpiarCampos();

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}



}
