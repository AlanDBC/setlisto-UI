package com.setlisto.ui.controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.setlisto.criteria.ClienteCriteria;
import com.setlisto.model.Cliente;
import com.setlisto.model.Results;
import com.setlisto.service.ClienteService;
import com.setlisto.service.impl.ClienteServiceImpl;
import com.setlisto.ui.view.ClienteFormView;
import com.setlisto.ui.view.ClienteView;

public class ClienteViewController extends AbstractController implements ActionListener, PropertyChangeListener {

	private ClienteService clienteService = null;
	private ClienteView view = null;

	public ClienteViewController(ClienteView view) {
		super("Buscar");
		clienteService = new ClienteServiceImpl();
		this.view = view;
	}
	
	// filtrado en tiempo real
	
	@Override // ComboBoxes
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}
	
	@Override // TextFields y FormattedTextFields
	public void keyReleased(KeyEvent e) {
		doAction();
	}
	
	@Override // DateChoosers
	public void propertyChange(PropertyChangeEvent evt) {
		if ("date".equals(evt.getPropertyName())) {
			doAction();
		}
	}

	@Override
	public void doAction() {
		ClienteCriteria criteria = view.getCriteria();
		Results<Cliente> clientes = clienteService.findByCriteria(criteria, 0, 100);
		List<Cliente> list = clientes == null ? null : clientes.getPage();
		view.setResults(list);
	}

	public Action getNuevoAction() {
		return new AbstractAction("Nuevo") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Window owner = SwingUtilities.getWindowAncestor(view);
				ClienteFormView dialog = new ClienteFormView(owner, null, () -> doAction());
				dialog.setLocationRelativeTo(view);
				dialog.setVisible(true);
			}
		};
	}

	public Action getEditarAction() {
		return new AbstractAction("Editar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				Window owner = SwingUtilities.getWindowAncestor(view);
				ClienteFormView dialog = new ClienteFormView(owner, seleccionado, () -> doAction());
				dialog.setLocationRelativeTo(view);
				dialog.setVisible(true);
			}
		};
	}

	public Action getEliminarAction() {
		return new AbstractAction("Eliminar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				int option = JOptionPane.showConfirmDialog(view,
						"Eliminar cliente '" + seleccionado.getEmail() + "'?",
						"Confirmar eliminacion",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					clienteService.delete(seleccionado.getId());
					doAction();
				}
			}
		};
	}

	public Action getActivarAction() {
		return new AbstractAction("Activar/Desactivar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				clienteService.setActive(!Boolean.TRUE.equals(seleccionado.getActivo()), seleccionado.getId());
				doAction();
			}
		};
	}

	public Action getVerificarAction() {
		return new AbstractAction("Verificar/Desverificar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				clienteService.setVerify(!Boolean.TRUE.equals(seleccionado.getVerificado()), seleccionado.getId());
				doAction();
			}
		};
	}

	private Cliente getSelectedOrWarn() {
		Cliente seleccionado = view.getSelectedCliente();
		if (seleccionado == null) {
			JOptionPane.showMessageDialog(view, "Seleccione un cliente.", "Sin seleccion", JOptionPane.WARNING_MESSAGE);
		}
		return seleccionado;
	}
}
