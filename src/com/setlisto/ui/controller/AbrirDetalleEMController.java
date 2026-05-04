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

	
	// Este se usa 
	@Override
	public void mouseClicked(MouseEvent e) {
		/*if (e.getClickCount() == 2) { // Doble clic
			
			JTable tabla = view.getTable();
			int selectedRow = tabla.rowAtPoint(e.getPoint());
			int selectedColumn = tabla.columnAtPoint(e.getPoint());
			// Verificar que se ha hecho clic en una celda válida
			if (selectedRow != -1 && selectedColumn != -1) {						
				EventoMusicalDTO em = (EventoMusicalDTO) tabla.getValueAt(selectedRow, selectedColumn);
				EventoView eventoView = EventoView.getInstance();
				eventoView.setModel(em);
				eventoView.setEditable(false);						
				eventoView.setGuardarController(new EventoSetEditableController(eventoView)); // Cambia el boton de modificar a guardar y TODO implementar
				
				MainWindow.getInstance().addTab(em.getNombre(), tabla);
			}
		}
		*/	
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
