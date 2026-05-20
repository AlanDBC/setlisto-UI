package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.TipoEvento;

public class TipoToStringConverter extends ObjectToStringConverter {

	public TipoToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((TipoEvento) item).getNombre();
		}
	}
}
