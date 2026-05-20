package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.EstadoEvento;

public class EstadoEventoToStringConverter extends ObjectToStringConverter {

	public EstadoEventoToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((EstadoEvento) item).getNombre();
		}
	}
}
