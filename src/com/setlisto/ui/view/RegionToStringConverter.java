package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.Region;

public class RegionToStringConverter extends ObjectToStringConverter {

	public RegionToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((Region) item).getNombre();
		}
	}
}
