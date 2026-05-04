package com.setlisto.ui.utils;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class UIUtils {

	public UIUtils() {
	}
	
	/**
	 * Este método acepta una lista de "Cualquier cosa" (T)
	 * lo utilizaremos para fabricar el modelo con la lista proporcionada
	 * y el placeholder  
	 * @param datos Lista de datos para crear el modelo correspondiente
	 * @param placeholder Objeto con id null y nombre "Seleccionar"
	 * @return modelo fabricado con el Objeto proporcionado
	 */
	public static <T> DefaultComboBoxModel<T> crearModelo(List<T> datos, T placeholder) {
	    DefaultComboBoxModel<T> model = new DefaultComboBoxModel<>();
	    // Añadimos el objeto vacío que creaste ("Seleccionar")
	    model.addElement(placeholder);
	    if (datos != null) {
	        for (T elemento : datos) {
	            model.addElement(elemento);
	        }
	    }
	    return model;
	}
}
