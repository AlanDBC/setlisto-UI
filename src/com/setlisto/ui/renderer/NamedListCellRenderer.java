package com.setlisto.ui.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import com.setlisto.model.Artista;
import com.setlisto.model.GeneroMusical;
import com.setlisto.model.SubGeneroMusical;

public class NamedListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		return super.getListCellRendererComponent(list, getNombre(value), index, isSelected, cellHasFocus);
	}

	private String getNombre(Object value) {
		if (value instanceof GeneroMusical) {
			return ((GeneroMusical) value).getNombre();
		}
		if (value instanceof SubGeneroMusical) {
			return ((SubGeneroMusical) value).getNombre();
		}
		if (value instanceof Artista) {
			return ((Artista) value).getNombre();
		}
		return value != null ? value.toString() : "";
	}
}
