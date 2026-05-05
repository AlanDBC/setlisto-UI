package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.setlisto.model.LugarDTO;

public class LugarTableRenderer implements TableCellRenderer{

	public LugarTableRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		LugarDTO l = (LugarDTO) value;
		JLabel label = new JLabel();

		if (l != null) {
			switch (column) {
			case 0: label.setText(l.getId().toString()); break;
			case 1: label.setText(l.getNombre()); break;
			case 2: label.setText(l.getDireccion()); break;
			case 3: label.setText(l.getCiudadNombre()); break;
			default: label.setText("N/A");
			}
		}

		if (isSelected) {
			label.setBackground(table.getSelectionBackground());
			label.setForeground(table.getSelectionForeground());
			label.setOpaque(true);
		}
		return label;
	}
}
