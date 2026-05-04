package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.EstadoEvento;

public class EstadoCBRenderer extends DefaultListCellRenderer {

	public EstadoCBRenderer() {
	}
	
	public Component getListCellRendererComponent(
	        JList list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) {
		EstadoEvento estado = (EstadoEvento) value;
		if (estado != null) {
			this.setText(estado.getNombre());			
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}

}
