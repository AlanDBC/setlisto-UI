package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.TipoEvento;

public class TipoEventoCBRenderer extends DefaultListCellRenderer {

	public TipoEventoCBRenderer() {
	}

	public Component getListCellRendererComponent(
	        JList list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) {
		TipoEvento tipoEvento = (TipoEvento) value;
		if (tipoEvento != null) {			
			this.setText(tipoEvento.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}
