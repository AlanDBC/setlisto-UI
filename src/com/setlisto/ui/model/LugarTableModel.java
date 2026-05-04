package com.setlisto.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.setlisto.model.LugarDTO;

public class LugarTableModel extends AbstractTableModel  {

	public static final String[] COLUMN_NAMES = new String[] {
			"Id", "Nombre", "Direccion", "Ciudad"
	};
	
	private List<LugarDTO> lugares = null;
	
	public LugarTableModel() {
		setLugares(new ArrayList<LugarDTO>());
	}
	
	public LugarTableModel (List<LugarDTO> lugares) {
		setLugares(lugares);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return lugares.get(row);
	}
	
	@Override
	public int getRowCount() {
		return lugares.size();
	}
	
	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}
	
	public List<LugarDTO> getLugares() {
		return lugares;
	}
	
	public void setLugares(List<LugarDTO> lugares) {
		if (lugares == null) {
            this.lugares = new ArrayList<>(); // Si es null, lista vacía
        } else {
            this.lugares = lugares;
        }
        // Notificamos a la JTable que los datos han cambiado para que se refresque
        fireTableDataChanged();
	}
}
