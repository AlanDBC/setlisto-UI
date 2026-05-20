package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.Ciudad;

public class CiudadToStringConverter extends ObjectToStringConverter {

	public CiudadToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((Ciudad) item).getNombre();
		}
	}
}
