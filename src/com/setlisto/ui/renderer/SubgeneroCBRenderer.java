package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.SubGeneroMusicalDTO;

public class SubgeneroCBRenderer extends DefaultListCellRenderer {

	public SubgeneroCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		SubGeneroMusicalDTO subgenero = (SubGeneroMusicalDTO) value;
		if (subgenero != null) {			
			this.setText(subgenero.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}