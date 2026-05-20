package com.setlisto.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import com.setlisto.model.EventoMusicalDTO;
import com.setlisto.ui.main.MainWindow;
import com.setlisto.ui.view.AdminEventoSearchView;
import com.setlisto.ui.view.EventoView;

public class AbrirDetalleEMController extends AbstractController implements MouseListener {
	
	private AdminEventoSearchView view = null;

	public AbrirDetalleEMController(AdminEventoSearchView view) {
		super (""); 
		this.view = view; 
	}

	@Override
	public void doAction() {
	}
 
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { // Doble clic
			
			JTable tabla = view.getTable();
			int selectedRow = tabla.rowAtPoint(e.getPoint());
			
			// Verificar que se ha hecho clic en una celda válida
			if (selectedRow != -1) {
                // Convertimos el índice por si la tabla está filtrada u ordenada
                int modelRow = tabla.convertRowIndexToModel(selectedRow);
                
				EventoMusicalDTO em = (EventoMusicalDTO) tabla.getValueAt(modelRow, 0);
				
				EventoView eventoView = new EventoView();
				eventoView.setModel(em);
				eventoView.setEditable(false);						
				
                // Añadimos la pestaña al MainWindow
				MainWindow.getInstance().addTab(eventoView);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
