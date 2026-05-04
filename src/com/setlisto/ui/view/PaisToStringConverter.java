package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.Pais;

public class PaisToStringConverter extends ObjectToStringConverter {

	public PaisToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((Pais) item).getNombre();
		}
	}
}
