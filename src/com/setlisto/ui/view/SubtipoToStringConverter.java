package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.SubTipoEvento;

public class SubtipoToStringConverter extends ObjectToStringConverter {

	public SubtipoToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((SubTipoEvento) item).getNombre();
		}
	}
}
