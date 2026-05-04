package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.GeneroMusical;

public class GeneroCBRenderer extends DefaultListCellRenderer {

	public GeneroCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		GeneroMusical genero = (GeneroMusical) value;		
		if (genero != null) {
			this.setText(genero.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}