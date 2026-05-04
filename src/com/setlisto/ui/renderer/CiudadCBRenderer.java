package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.Ciudad;

public class CiudadCBRenderer extends DefaultListCellRenderer {

	public CiudadCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		Ciudad ciudad = (Ciudad) value;		
		if (ciudad != null) {
			this.setText(ciudad.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}
