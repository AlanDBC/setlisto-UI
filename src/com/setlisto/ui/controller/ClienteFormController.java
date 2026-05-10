package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.setlisto.model.Cliente;
import com.setlisto.service.ClienteService;
import com.setlisto.service.impl.ClienteServiceImpl;
import com.setlisto.ui.view.ClienteFormView;

public class ClienteFormController extends AbstractController {

	private static final long serialVersionUID = 1L;

	private ClienteFormView view;
	private ClienteService clienteService;
	private Runnable onSaved;

	public ClienteFormController(ClienteFormView view, Runnable onSaved) {
		super("Guardar");
		this.view = view;
		this.onSaved = onSaved;
		this.clienteService = new ClienteServiceImpl();
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

		Cliente cliente = view.getClienteFromFields();
		if (!validar(cliente)) {
			return;
		}

		try {
			boolean ok;
			if (view.isEditMode()) {
				ok = clienteService.update(cliente);
			} else {
				ok = clienteService.register(cliente) != null;
			}

			if (ok) {
				JOptionPane.showMessageDialog(view, "Cliente guardado correctamente.", "Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
				if (onSaved != null) {
					onSaved.run();
				}
				view.dispose();
			} else {
				JOptionPane.showMessageDialog(view, "No se pudo guardar el cliente. Revise si el email ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al guardar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	private boolean validar(Cliente cliente) {
		if (isEmpty(cliente.getEmail()) || isEmpty(cliente.getNombre()) || isEmpty(cliente.getApellido()) || cliente.getFechaNacimiento() == null) {
			JOptionPane.showMessageDialog(view,
					"Email, nombre, apellido y fecha de nacimiento son obligatorios.",
					"Datos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (!view.isEditMode() && isEmpty(cliente.getContrasena())) {
			JOptionPane.showMessageDialog(view, "La contrasena es obligatoria al crear un cliente.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}
}
