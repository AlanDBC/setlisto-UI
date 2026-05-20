package com.setlisto.ui.view;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.setlisto.model.GeneroMusical;

public class GeneroToStringConverter extends ObjectToStringConverter {
	

	public GeneroToStringConverter() {
	}

	public String getPreferredStringForItem(Object item) {
		if (item == null) {
			return "Seleccionar";
		}
		else {
			return ((GeneroMusical) item).getNombre();
		}
	}

}
