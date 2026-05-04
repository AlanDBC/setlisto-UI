package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.Region;

public class RegionCBRenderer extends DefaultListCellRenderer {

	public RegionCBRenderer() {
	}

	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus) {
		Region region = (Region) value;
		if (region != null) {			
			this.setText(region.getNombre());
		}
		else {
			this.setText("Seleccionar");
		}
		return this;
	}
}