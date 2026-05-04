package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.Pais;

public class PaisCBRenderer extends DefaultListCellRenderer {

	public PaisCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		Pais pais = (Pais) value;
		if (pais != null) {
			this.setText(pais.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}