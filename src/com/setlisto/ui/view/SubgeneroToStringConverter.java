package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.SubGeneroMusical;

public class SubgeneroToStringConverter extends ObjectToStringConverter {

	public SubgeneroToStringConverter() {
	}
	
	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((SubGeneroMusical) item).getNombre();
		}
	}
}
