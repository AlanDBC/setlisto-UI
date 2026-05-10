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

import com.setlisto.criteria.OrganizadorCriteria;
import com.setlisto.model.Organizador;
import com.setlisto.model.Results;
import com.setlisto.service.OrganizadorService;
import com.setlisto.service.impl.OrganizadorServiceImpl;
import com.setlisto.ui.view.OrganizadorFormView;
import com.setlisto.ui.view.OrganizadorView;

public class OrganizadorViewController extends AbstractController implements ActionListener, PropertyChangeListener {

	private OrganizadorService organizadorService;
	private OrganizadorView view;

	public OrganizadorViewController(OrganizadorView view) {
		super("Buscar");
		this.view = view;
		this.organizadorService = new OrganizadorServiceImpl();
	}

	@Override
	public void doAction() {
		OrganizadorCriteria criteria = view.getCriteria();
		Results<Organizador> organizadores = organizadorService.findByCriteria(criteria, 0, 100);
		List<Organizador> list = organizadores == null ? null : organizadores.getPage();
		view.setResults(list);
	}
	
	// Para filtrado en tiempo real
	
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

	public Action getNuevoAction() {
		return new AbstractAction("Nuevo") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Window owner = SwingUtilities.getWindowAncestor(view);
				OrganizadorFormView dialog = new OrganizadorFormView(owner, null, () -> doAction());
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
				Organizador seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				Window owner = SwingUtilities.getWindowAncestor(view);
				OrganizadorFormView dialog = new OrganizadorFormView(owner, seleccionado, () -> doAction());
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
				Organizador seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				int option = JOptionPane.showConfirmDialog(view,
						"Eliminar organizador '" + seleccionado.getEmail() + "'?",
						"Confirmar eliminacion",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					organizadorService.delete(seleccionado.getId());
					doAction();
				}
			}
		};
	}

	public Action getVerificarAction() {
		return new AbstractAction("Verificar/Desverificar") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				Organizador seleccionado = getSelectedOrWarn();
				if (seleccionado == null) {
					return;
				}
				organizadorService.updateVerifiedStatus(seleccionado.getId(), !Boolean.TRUE.equals(seleccionado.getVerificado()));
				doAction();
			}
		};
	}

	private Organizador getSelectedOrWarn() {
		Organizador seleccionado = view.getSelectedOrganizador();
		if (seleccionado == null) {
			JOptionPane.showMessageDialog(view, "Seleccione un organizador.", "Sin seleccion", JOptionPane.WARNING_MESSAGE);
		}
		return seleccionado;
	}
}
