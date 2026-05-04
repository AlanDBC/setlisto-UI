package com.setlisto.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.setlisto.model.EventoMusicalDTO;

public class EventoTableModel extends AbstractTableModel {

	public static final String[] COLUMN_NAMES = new String[] {
			"Id", "Nombre", "Estado", "Organizador", "Inicio", "Fin", "Lugar", "Ciudad", "Capacidad"
	};

	private List<EventoMusicalDTO> eventos = null;

	public EventoTableModel() {
		setEventos(new ArrayList<EventoMusicalDTO>());
	}
	
	public EventoTableModel(List<EventoMusicalDTO> eventos) {
		setEventos(eventos);
	}

	@Override
	public Object getValueAt(int row, int col) {
		return eventos.get(row);
	}

	@Override
	public int getRowCount() {
		return eventos.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	public List<EventoMusicalDTO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoMusicalDTO> eventos) {
		if (eventos == null) {
            this.eventos = new ArrayList<>(); // Si es null, lista vacía
        } else {
            this.eventos = eventos;
        }
        // Notificamos a la JTable que los datos han cambiado para que se refresque
        fireTableDataChanged();
	}

}
