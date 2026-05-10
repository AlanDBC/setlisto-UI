package com.setlisto.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.setlisto.model.Organizador;

public class OrganizadorTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_NAMES = {
			"ID", "Nombre comercial", "Nombre", "Apellido 1", "Apellido 2", "Email", "Telefono", "Verificado", "Fecha nacimiento"
	};

	private List<Organizador> organizadores = new ArrayList<Organizador>();

	public OrganizadorTableModel() {
	}

	public OrganizadorTableModel(List<Organizador> organizadores) {
		setOrganizadores(organizadores);
	}

	@Override
	public int getRowCount() {
		return organizadores.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Organizador organizador = getOrganizadorAt(rowIndex);
		if (organizador == null) {
			return null;
		}

		switch (columnIndex) {
		case 0:
			return organizador.getId();
		case 1:
			return organizador.getNombreComercial();
		case 2:
			return organizador.getNombre();
		case 3:
			return organizador.getApellido1();
		case 4:
			return organizador.getApellido2();
		case 5:
			return organizador.getEmail();
		case 6:
			return organizador.getTelefono();
		case 7:
			return Boolean.TRUE.equals(organizador.getVerificado()) ? "Si" : "No";
		case 8:
			return organizador.getFechaNacimiento();
		default:
			return "";
		}
	}

	public void setOrganizadores(List<Organizador> organizadores) {
		if (organizadores == null) {
			this.organizadores = new ArrayList<Organizador>();
		} else {
			this.organizadores = organizadores;
		}
		fireTableDataChanged();
	}

	public Organizador getOrganizadorAt(int row) {
		if (row < 0 || row >= organizadores.size()) {
			return null;
		}
		return organizadores.get(row);
	}
}
