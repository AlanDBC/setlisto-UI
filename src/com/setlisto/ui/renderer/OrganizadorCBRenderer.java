package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.Organizador;

public class OrganizadorCBRenderer extends DefaultListCellRenderer {
	
	public OrganizadorCBRenderer() {
	}
	
	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		Organizador organizador = (Organizador) value;
		if (organizador != null) {
			this.setText(organizador.getNombreComercial());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}