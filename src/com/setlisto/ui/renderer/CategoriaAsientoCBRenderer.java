package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.CategoriaAsiento;

public class CategoriaAsientoCBRenderer extends DefaultListCellRenderer {
	
	public CategoriaAsientoCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		CategoriaAsiento categoria = (CategoriaAsiento) value;
		if (categoria != null) {
			this.setText(categoria.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}
