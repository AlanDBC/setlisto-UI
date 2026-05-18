package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.setlisto.criteria.EventoMusicalCriteria;
import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.model.Results;
import com.setlisto.service.EventoMusicalService;
import com.setlisto.service.impl.EventoMusicalServiceImpl;
import com.setlisto.ui.view.AdminEventoSearchView;

public class EventoSearchController extends AbstractController implements KeyListener, ItemListener, PropertyChangeListener {

	private EventoMusicalService eventoMusicalService = null;
	private AdminEventoSearchView view = null;

	public EventoSearchController(AdminEventoSearchView view) {
		super("Buscar", new ImageIcon(AdminEventoSearchView.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		eventoMusicalService = new EventoMusicalServiceImpl();
		this.view = view;
	}

	public void doAction() {
		try {
			EventoMusicalCriteria criteria = view.getCriteria();
			Results<EventoMusicalDTO> resultados = eventoMusicalService.findByCriteria(criteria, 0, 20);
			List<EventoMusicalDTO> eventos = resultados.getPage();
			view.setModel(eventos);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, "No fue posible buscar eventos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		doAction();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("date".equals(evt.getPropertyName())) {
			doAction();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		doAction();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
