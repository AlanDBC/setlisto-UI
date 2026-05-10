package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.setlisto.model.Organizador;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.impl.OrganizadorServiceImpl;
import com.setlisto.ui.view.OrganizadorFormView;

public class OrganizadorFormController extends AbstractController {

	private static final long serialVersionUID = 1L;

	private OrganizadorFormView view;
	private OrganizadorService organizadorService;
	private Runnable onSaved;

	public OrganizadorFormController(OrganizadorFormView view, Runnable onSaved) {
		super("Guardar");
		this.view = view;
		this.onSaved = onSaved;
		this.organizadorService = new OrganizadorServiceImpl();
	}

	@Override
	public void doAction() {
		if (view.isEditMode() && view.hasPasswordText()) {
			JOptionPane.showMessageDialog(view,
					"La contrasena no se modifica desde este formulario.",
					"Contrasena no actualizada",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Organizador organizador = view.getOrganizadorFromFields();
		if (!validar(organizador)) {
			return;
		}

		try {
			boolean ok;
			if (view.isEditMode()) {
				ok = organizadorService.update(organizador);
			} else {
				ok = organizadorService.register(organizador) != null;
			}

			if (ok) {
				JOptionPane.showMessageDialog(view, "Organizador guardado correctamente.", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
				if (onSaved != null) {
					onSaved.run();
				}
				view.dispose();
			} else {
				JOptionPane.showMessageDialog(view, "No se pudo guardar el organizador. Revise si el email ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al guardar el organizador.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	private boolean validar(Organizador organizador) {
		if (isEmpty(organizador.getNombreComercial()) || isEmpty(organizador.getEmail())
				|| isEmpty(organizador.getNombre()) || isEmpty(organizador.getApellido1())
				|| isEmpty(organizador.getApellido2()) || organizador.getFechaNacimiento() == null) {
			JOptionPane.showMessageDialog(view,
					"Nombre comercial, email, nombre, apellidos y fecha de nacimiento son obligatorios.",
					"Datos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!view.isEditMode() && isEmpty(organizador.getContrasena())) {
			JOptionPane.showMessageDialog(view, "La contrasena es obligatoria al crear un organizador.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
