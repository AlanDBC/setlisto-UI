package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.model.Lugar;
import com.setlisto.model.LugarDTO;
import com.setlisto.service.LugarService;
import com.setlisto.service.impl.LugarServiceImpl;
import com.setlisto.ui.view.AdminEventoSearchView;
import com.setlisto.ui.view.EventoCreateView;
import com.setlisto.ui.view.LugarSelectView;

public class LugarCreateController extends AbstractController {

	public LugarSelectView view = null;
	public LugarService service = null;
	public EventoCreateView receptor = null;

	public LugarCreateController(LugarSelectView view) {
		super("Crear", new ImageIcon(EventoCreateView.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		this.view = view;
		service = new LugarServiceImpl();
	}

	@Override
	public void doAction() {
		Lugar lgr = view.getLugar();
		if (lgr.getNombre() == null || lgr.getNombre().trim().isEmpty()
				|| lgr.getDireccion() == null || lgr.getDireccion().trim().isEmpty()
				|| lgr.getCiudadId() == null || lgr.getIdZonaHoraria() == null) {
			JOptionPane.showMessageDialog(
					view,
					"Completa nombre, dirección, ciudad y zona horaria para crear el lugar.",
					"Datos incompletos",
					JOptionPane.WARNING_MESSAGE
					);
			return;
		}
		Lugar creado = null;
		try {
			creado = service.create(lgr);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "No fue posible crear el lugar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (creado != null) {

			// Mostramos el mensaje de éxito
			JOptionPane.showMessageDialog(
					view, 
					"¡Lugar '" + creado.getNombre() + "' creado con éxito!", 
					"Operación Exitosa", 
					JOptionPane.INFORMATION_MESSAGE
					);

			// Limpiamos los campos mientras el usuario cierra el diálogo
			view.limpiarCampos();
			
			try {
				LugarDTO lgrDto = service.findById(creado.getId());
				view.setLugarSeleccionado(lgrDto); // dentro de este metodo se pasa el nombre a el label
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(view, "El lugar fue creado, pero no se pudo recargar: " + ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
			}

		} else {
			// Mostramos el mensaje de error
			JOptionPane.showMessageDialog(
					view, 
					"Error al crear el lugar. Por favor, inténtalo de nuevo.", 
					"Error", 
					JOptionPane.ERROR_MESSAGE
					);
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}



}
