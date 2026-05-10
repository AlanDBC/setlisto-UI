package com.setlisto.ui.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.setlisto.model.Cliente;

public class ClienteTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	private static final String[] COLUMN_NAMES = {
			"ID", "Nombre", "Apellido", "Email", "Telefono", "Activo", "Verificado", "Fecha creacion"
	};

	private List<Cliente> clientes = new ArrayList<Cliente>();

	public ClienteTableModel() {
	}

	public ClienteTableModel(List<Cliente> clientes) {
		setClientes(clientes);
	}

	@Override
	public int getRowCount() {
		return clientes.size();
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
		Cliente cliente = getClienteAt(rowIndex);
		if (cliente == null) {
			return null;
		}

		switch (columnIndex) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNombre();
		case 2:
			return cliente.getApellido();
		case 3:
			return cliente.getEmail();
		case 4:
			return cliente.getTelefono();
		case 5:
			return Boolean.TRUE.equals(cliente.getActivo()) ? "Si" : "No";
		case 6:
			return Boolean.TRUE.equals(cliente.getVerificado()) ? "Si" : "No";
		case 7:
			return cliente.getFechaCreacion() == null ? "" : DATE_TIME_FORMATTER.format(cliente.getFechaCreacion());
		default:
			return "";
		}
	}

	public void setClientes(List<Cliente> clientes) {
		if (clientes == null) {
			this.clientes = new ArrayList<Cliente>();
		} else {
			this.clientes = clientes;
		}
		fireTableDataChanged();
	}

	public Cliente getClienteAt(int row) {
		if (row < 0 || row >= clientes.size()) {
			return null;
		}
		return clientes.get(row);
	}
}
