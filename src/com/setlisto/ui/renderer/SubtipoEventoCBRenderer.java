package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.SubTipoEventoDTO;

public class SubtipoEventoCBRenderer extends DefaultListCellRenderer {

	public SubtipoEventoCBRenderer() {
	}

	public Component getListCellRendererComponent(
	        JList list,
	        Object value,
	        int index,
	        boolean isSelected,
	        boolean cellHasFocus) {
		SubTipoEventoDTO subtipoEvento = (SubTipoEventoDTO) value;		
		if (subtipoEvento != null) {
			this.setText(subtipoEvento.getNombre());			
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}
