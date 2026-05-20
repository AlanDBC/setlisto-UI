package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.ZonaHoraria;

public class ZonaHorariaToStringConverter extends ObjectToStringConverter {

	public ZonaHorariaToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((ZonaHoraria) item).getNombre();
		}
	}
}
