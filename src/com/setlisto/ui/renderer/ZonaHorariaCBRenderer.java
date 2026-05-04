package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.ZonaHoraria;

public class ZonaHorariaCBRenderer extends DefaultListCellRenderer  {

	public ZonaHorariaCBRenderer() {
	}
	
	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		ZonaHoraria zh = (ZonaHoraria) value;
		if (zh != null) {
			this.setText(zh.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}